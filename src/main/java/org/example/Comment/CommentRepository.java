package org.example.Comment;
import org.example.Article.Exceptions.ArticleNotFoundException;
import org.example.Comment.Exceptions.*;

import java.util.List;

public interface CommentRepository {
  CommentId generateId();

  List<Comment> findAll();

  /**
   * @throws CommentNotFoundException
   */
  Comment findById(CommentId commentId) throws CommentNotFoundException;

  /**
   * @throws CommentIdDuplicatedException
   * @throws ArticleNotFoundException
   */
  void create(Comment comment) throws CommentIdDuplicatedException, ArticleNotFoundException;

  /**
   * @throws CommentNotFoundException
   */
  void update(Comment comment) throws CommentNotFoundException;

  /**
   * @throws CommentNotFoundException
   * @throws ArticleNotFoundException
   */
  void delete(CommentId commentId) throws CommentNotFoundException, ArticleNotFoundException;
}
