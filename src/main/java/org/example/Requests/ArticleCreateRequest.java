package org.example.Requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record ArticleCreateRequest(String articleTitle, Set<String> tags) {
  @JsonCreator
  public ArticleCreateRequest(
      @JsonProperty("title") String articleTitle,
      @JsonProperty("tags") Set<String> tags
  ) {
    this.articleTitle = articleTitle;
    this.tags = tags;
  }
}
