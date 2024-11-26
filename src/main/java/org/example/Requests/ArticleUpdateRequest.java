package org.example.Requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public record ArticleUpdateRequest(String articleTitle, Set<String> tags) {
  @JsonCreator
  public ArticleUpdateRequest(
      @JsonProperty("title") String articleTitle,
      @JsonProperty("tags") Set<String> tags
  ) {
    this.articleTitle = articleTitle;
    this.tags = tags;
  }
}
