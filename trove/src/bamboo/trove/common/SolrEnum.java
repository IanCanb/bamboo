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
package bamboo.trove.common;

public enum SolrEnum {
  // Keeping them in alphabetical order just
  // to help readability as the schema grows
  AU_GOV("auGov"),
  BOOST("boost"),
  PAGEBOOST("pageBoost"),
  PAGERANK("pageRank"),
  DATE("date"),
  DECADE("decade"),
  DELIVERABLE("deliverable"),
  DELIVERY_URL("deliveryUrl"),
  DISCOVERABLE("discoverable"),
  DISPLAY_URL("url"),
  FILENAME("filename"),
  FULL_TEXT("fulltext"),
  HOST("host"),
  HOST_REVERSED("hostReversed"),
  ID("id"),
  LAST_INDEXED("lastIndexed"),
  METADATA("metadata"),
  PANDORA_URL("pandoraUrl"),
  PANDORA("pandora"),
  RULE("ruleId"),
  SEARCH_CATEGORY("searchCategory"),
  SITE("site"),
  OG_SITE("ogSite"),
  TEXT_ERROR("textError"),
  TITLE("title"),
  OG_TITLE("ogTitle"),
  H1("h1"),
  TITLE_COMBINED("titleCombined"),
  TITLE_H1_COMBINED("titleH1Combined"),
  LINK_TEXT("linkText"),
  TITLE_LINK_COMBINED("titleLinkCombined"),
  TITLE_H1_LINK_COMBINED("titleH1LinkCombined"),
  URL_TOKENIZED("urlTokenized"),
  YEAR("year"),
  QUEUED_FOR_CLASSIFICATION("queuedForClassification"),
  CLASSIFICATION_FAILED("classificationFailed"),
  IMAGE_HUMAN_SAFE("imageHumanSafe"),
  IMAGE_AUTO_SAFE("imageAutoSafe"),
  IMAGE_AUTO_MAYBE_SAFE("imageAutoMaybeSafe"),
  IMAGE_AUTO_MAYBE_UNSAFE("imageAutoMaybeUnsafe"),
  IMAGE_AUTO_UNSAFE("imageAutoUnsafe"),
  IMAGE_HUMAN_UNSAFE("imageHumanUnsafe"),
  TEXT_HUMAN_SAFE("textHumanSafe"),
  TEXT_AUTO_SAFE("textAutoSafe"),
  TEXT_AUTO_MAYBE_SAFE("textAutoMaybeSafe"),
  TEXT_AUTO_MAYBE_UNSAFE("textAutoMaybeUnsafe"),
  TEXT_AUTO_UNSAFE("textAutoUnsafe"),
  TEXT_CONTENTKEEPER_UNSAFE("textContentkeeperUnsafe"),
  TEXT_HUMAN_UNSAFE("textHumanUnsafe");
  
  private String value;
  SolrEnum(String value) {
    this.value = value;
  }
  public String toString() {
    return value;
  }
}
