package org.example.Article;

import org.example.Comment.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Article {
  private final ArticleId id;
  private final String articleName;
  private final Set<String> tags;
  private final List<Comment> comments;

  public Article(ArticleId id, String articleName, Set<String> tags, List<Comment> comments) {
    this.id = id;
    this.articleName = articleName;
    this.tags = tags;
    this.comments = comments;
  }

  public Article withComments(List<Comment> newComments) {
    return new Article(id, articleName, tags, newComments);
  }

  public Article withName(String articleName) {
    return new Article(id, articleName, tags, comments);
  }

  public Article withTags(Set<String> tags) {
    return new Article(id, articleName, tags, comments);
  }

  public String getArticleName() {
    return articleName;
  }

  public Set<String> getTags() {
    return tags;
  }

  public List<Comment> getComments() {
    return comments == null ? new ArrayList<>() : comments;
  }

  public Long getId() {
    return id.getId();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Article article = (Article) o;
    return id.equals(article.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    String sb = "ID = " + id.toString() + ", " +
        "Title = " + articleName + ", " +
        "Tags = " + tags.toString() + ", " +
        "Comments = " + comments.toString();
    return sb;
  }
}
