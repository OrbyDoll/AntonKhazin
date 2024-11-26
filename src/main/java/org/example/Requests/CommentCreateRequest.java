package org.example.Requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.Article.ArticleId;

public record CommentCreateRequest(ArticleId articleId, String commentText) {
  @JsonCreator
  public CommentCreateRequest(
      @JsonProperty("articleId") ArticleId articleId,
      @JsonProperty("text") String commentText
  ) {
    this.articleId = articleId;
    this.commentText = commentText;
  }
}
