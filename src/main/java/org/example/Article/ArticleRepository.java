package org.example.Article;

import org.example.Article.Exceptions.*;
import org.example.Comment.Exceptions.CommentNotFoundException;

import java.util.List;

public interface ArticleRepository {
  ArticleId generateId();

  List<Article> findAll();

  /**
   * @throws ArticleNotFoundException
   */
  Article findById(ArticleId articleId) throws ArticleNotFoundException;

  /**
   * @throws ArticleIdDuplicatedException
   */
  void create(Article article) throws ArticleIdDuplicatedException;

  /**
   * @throws ArticleNotFoundException
   */
  void update(Article article) throws ArticleNotFoundException;

  /**
   * @throws ArticleNotFoundException
   */
  void delete(ArticleId articleId) throws ArticleNotFoundException;
}
