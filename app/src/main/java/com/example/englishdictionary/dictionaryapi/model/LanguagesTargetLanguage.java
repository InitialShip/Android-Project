/**
 * 
 * No descripton provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.6.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.example.englishdictionary.dictionaryapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;


/**
 * Translation language of the results
 */
public class LanguagesTargetLanguage   {
  @SerializedName("id")
  private String id = null;

  @SerializedName("language")
  private String language = null;

  public LanguagesTargetLanguage id(String id) {
    this.id = id;
    return this;
  }

   /**
   * IANA language code
   * @return id
  **/
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LanguagesTargetLanguage language(String language) {
    this.language = language;
    return this;
  }

   /**
   * Language label.
   * @return language
  **/
  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LanguagesTargetLanguage languagesTargetLanguage = (LanguagesTargetLanguage) o;
    return Objects.equals(this.id, languagesTargetLanguage.id) &&
        Objects.equals(this.language, languagesTargetLanguage.language);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, language);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LanguagesTargetLanguage {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    language: ").append(toIndentedString(language)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

