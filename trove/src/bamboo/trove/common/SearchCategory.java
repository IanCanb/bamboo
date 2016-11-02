/**
 * Copyright 2016 National Library of Australia
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

public enum SearchCategory {
  NONE("none"),
  HTML("html"),
  PDF("pdf"),
  PRESENTATION("presentation"),
  SPREADSHEET("spreadsheet"),
  DOCUMENT("document");

  private String value;
  SearchCategory(String value) {
    this.value = value;
  }
  public String toString() {
    return value;
  }
}