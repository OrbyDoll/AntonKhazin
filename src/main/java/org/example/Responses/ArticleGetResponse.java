package org.example.Responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.Comment.Comment;
import java.util.List;
import java.util.Set;

public record ArticleGetResponse(String articleTitle, Set<String> tags, List<Comment> comments) {
  @JsonCreator
  public ArticleGetResponse(
      @JsonProperty("title") String articleTitle,
      @JsonProperty("tags") Set<String> tags,
      @JsonProperty("comments") List<Comment> comments
  ) {
    this.articleTitle = articleTitle;
    this.tags = tags;
    this.comments = comments;
  }
}
