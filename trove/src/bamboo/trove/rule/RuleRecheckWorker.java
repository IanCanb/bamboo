/*
 * Copyright 2016-2017 National Library of Australia
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package bamboo.trove.rule;

import bamboo.trove.common.SearchCategory;
import bamboo.trove.common.SolrEnum;
import bamboo.trove.common.cdx.CdxRule;
import bamboo.trove.services.CdxRestrictionService;
import bamboo.trove.workers.TransformWorker;
import com.codahale.metrics.Timer;
import org.apache.solr.common.SolrInputDocument;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class RuleRecheckWorker implements Runnable {
  //  private static final Logger log = LoggerFactory.getLogger(RuleRecheckWorker.class);
  private static final Map<String, Object> partialUpdateNull = new HashMap<>();
  private static final Map<String, Object> partialUpdateFalse = new HashMap<>();

  static {
    partialUpdateNull.put("set", null); // set to null to remove
    partialUpdateFalse.put("set", false);
  }

  private RuleChangeUpdateManager manager;
  private CdxRestrictionService service;
  private String id;
  private String url;
  private Date capture;
  private String site;
  private SearchCategory searchCategory;
  private long existingRuleId;
  private RuleChangeUpdateManager.WorkLog workLog;
  private float boost = 1.0f;

  RuleRecheckWorker(String id, String url, Date capture, String site, SearchCategory searchCategory,
                    long existingRuleId, RuleChangeUpdateManager.WorkLog workLog, RuleChangeUpdateManager manager,
                    CdxRestrictionService service) {
    this.manager = manager;
    this.service = service;
    this.id = id;
    this.url = url;
    this.capture = capture;
    this.site = site;
    this.searchCategory = searchCategory;
    this.existingRuleId = existingRuleId;
    this.workLog = workLog;
  }

  @Override
  public void run() {
    Timer.Context context = manager.getTimer(manager.getName() + ".worker").time();
    SolrInputDocument update = processResultsRecheckRule();
    if (update != null) {
      manager.update(update);
      workLog.wroteDocument();
    }
    context.stop();
  }

  private SolrInputDocument processResultsRecheckRule() {
    CdxRule rule = service.filterDocument(url, capture);
    // Cutout if the rule hasn't changed (and we are not writing all objects back just to touch their timestamp)
    if (manager.isMinimizingWriteTraffic() && existingRuleId == rule.getId()) {
      return null;
    }

    SolrInputDocument update = new SolrInputDocument();
    update.addField(SolrEnum.ID.toString(), id);
    Map<String, Object> partialUpdate = new HashMap<>();
    partialUpdate.put("set", rule.getId());
    update.addField(SolrEnum.RULE.toString(), partialUpdate);

    switch (rule.getIndexerPolicy()) {
      case RESTRICTED_FOR_BOTH:
        update.addField(SolrEnum.DELIVERABLE.toString(), partialUpdateFalse);
        update.addField(SolrEnum.DISCOVERABLE.toString(), partialUpdateFalse);
        modifyBoost(TransformWorker.MALUS_UNDELIVERABLE);
        break;
      case RESTRICTED_FOR_DELIVERY:
        update.addField(SolrEnum.DISCOVERABLE.toString(), partialUpdateNull);
        update.addField(SolrEnum.DELIVERABLE.toString(), partialUpdateFalse);
        modifyBoost(TransformWorker.MALUS_UNDELIVERABLE);
        break;
      case RESTRICTED_FOR_DISCOVERY:
        update.addField(SolrEnum.DELIVERABLE.toString(), partialUpdateFalse);
        update.addField(SolrEnum.DISCOVERABLE.toString(), partialUpdateNull);
        break;

      default:
        update.addField(SolrEnum.DELIVERABLE.toString(), partialUpdateNull);
        update.addField(SolrEnum.DISCOVERABLE.toString(), partialUpdateNull);
        break;
    }

    if (searchCategory == null) {
      modifyBoost(TransformWorker.MALUS_SEARCH_CATEGORY);
    } else {
      switch (searchCategory) {
        case NONE:
        case DOCUMENT:
        case PRESENTATION:
        case SPREADSHEET:
          modifyBoost(TransformWorker.MALUS_SEARCH_CATEGORY);
          break;

        default:
          break;
      }
    }

    if (site != null) {
      if (site.endsWith(".gov.au")) {
        modifyBoost(TransformWorker.BONUS_GOV_SITE);
      } else if (site.endsWith(".edu.au")) {
        modifyBoost(TransformWorker.BONUS_EDU_SITE);
      }
    }

    partialUpdate = new HashMap<>();
    partialUpdate.put("set", new Date());
    update.addField(SolrEnum.LAST_INDEXED.toString(), partialUpdate);
    update.setDocumentBoost(boost);
    return update;
  }

  private float modifyBoost(float modifier) {
    boost *= modifier;
    return boost;
  }
}
