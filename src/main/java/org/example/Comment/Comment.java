package org.example.Comment;

import org.example.Article.ArticleId;

public class Comment {
  private CommentId commentId;
  private ArticleId articleId;
  private String commentText;

  public Comment(CommentId id, ArticleId articleId, String commentText) {
    this.commentId = id;
    this.articleId = articleId;
    this.commentText = commentText;
  }

  public CommentId getCommentId() {
    return commentId;
  }

  public ArticleId getArticleId() {
    return articleId;
  }

  public String getCommentText() {
    return commentText;
  }

  public Comment withArticleId(ArticleId articleId) {
    return new Comment(this.commentId, articleId, this.commentText);
  }

  public Comment withCommentText(String commentText) {
    return new Comment(this.commentId, this.articleId, commentText);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comment comment = (Comment) o;
    return commentId.equals(comment.commentId);
  }

  @Override
  public int hashCode() {
    return commentId.hashCode();
  }

  @Override
  public String toString() {
    return commentText;
  }
}
