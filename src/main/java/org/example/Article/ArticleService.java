package org.example.Article;

import org.example.Article.Exceptions.ArticleIdDuplicatedException;
import org.example.Article.Exceptions.ArticleNotFoundException;
import org.example.Comment.Comment;
import org.example.Comment.CommentId;
import org.example.Comment.CommentRepository;
import org.example.Comment.Exceptions.CommentIdDuplicatedException;
import org.example.Comment.Exceptions.CommentNotFoundException;

import java.util.List;
import java.util.Set;

public class ArticleService {
  private final CommentRepository commentRepository;
  private final ArticleRepository articleRepository;

  public ArticleService(CommentRepository commentRepository, ArticleRepository articleRepository) {
    this.commentRepository = commentRepository;
    this.articleRepository = articleRepository;
  }

  public List<Article> findAll() {
    return articleRepository.findAll();
  }

  public Article findArticleById(ArticleId articleId) throws ArticleNotFoundException {
    return articleRepository.findById(articleId);
  }

  public ArticleId createArticle(String articleName, Set<String> tags) throws ArticleIdDuplicatedException {
    try {
      ArticleId articleId = articleRepository.generateId();
      Article newArticle = new Article(articleId, articleName, tags, null);
      articleRepository.create(newArticle);
      return articleId;
    } catch (ArticleIdDuplicatedException e) {
      throw e;
    }
  }

  public void updateArticle(ArticleId id, String articleName, Set<String> tags) throws ArticleNotFoundException {
    try {
      Article desiredArticle = articleRepository.findById(id);
      Article updatedArticle = desiredArticle.withName(articleName).withTags(tags);
      articleRepository.update(updatedArticle);
    } catch (ArticleNotFoundException e) {
      throw e;
    }
  }

  public void deleteArticle(ArticleId articleId) throws ArticleNotFoundException, CommentNotFoundException {
    try {
      Article desiredArticle = articleRepository.findById(articleId);
      for (Comment articleComment : desiredArticle.getComments()) {
        commentRepository.delete(articleComment.getCommentId());
      }
      articleRepository.delete(articleId);
    } catch (ArticleNotFoundException | CommentNotFoundException e) {
      throw e;
    }
  }

  public CommentId createComment(String commentText, ArticleId articleId) throws CommentIdDuplicatedException, ArticleNotFoundException {
    CommentId commentId = commentRepository.generateId();
    Comment comment = new Comment(commentId, articleId, commentText);
    try {
      commentRepository.create(comment);
      Article desiredArticle = articleRepository.findById(comment.getArticleId());
      List<Comment> articleComments = desiredArticle.getComments();
      articleComments.add(comment);
      articleRepository.update(desiredArticle.withComments(articleComments));
      return commentId;
    } catch (ArticleNotFoundException | CommentIdDuplicatedException e) {
      throw e;
    }
  }

  public void deleteComment(CommentId commentId) throws CommentNotFoundException, ArticleNotFoundException {
    try {
      Comment desiredComment = commentRepository.findById(commentId);
      Article desiredArticle = articleRepository.findById(desiredComment.getArticleId());
      List<Comment> articleComments = desiredArticle.getComments();
      articleComments.remove(desiredComment);
      articleRepository.update(desiredArticle.withComments(articleComments));
      commentRepository.delete(commentId);
    } catch (CommentNotFoundException | ArticleNotFoundException e) {
      throw e;
    }
  }
}
