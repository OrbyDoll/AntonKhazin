package org.example.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Article.Article;
import org.example.Article.ArticleId;
import org.example.Article.ArticleService;
import org.example.Article.Exceptions.ArticleIdDuplicatedException;
import org.example.Article.Exceptions.ArticleNotFoundException;
import org.example.Comment.CommentId;
import org.example.Comment.Exceptions.CommentNotFoundException;
import org.example.Requests.ArticleCreateRequest;
import org.example.Requests.ArticleUpdateRequest;
import org.example.Requests.CommentCreateRequest;
import org.example.Responses.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Service;

public class ArticleController implements Controller {
  private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);
  private final Service service;
  private final ArticleService articleService;
  private final ObjectMapper objectMapper;

  public ArticleController(ObjectMapper objectMapper, ArticleService articleService, Service service) {
    this.objectMapper = objectMapper;
    this.articleService = articleService;
    this.service = service;
  }

  @Override
  public void initializeEndpoints() {
    getArticle();
    createArticle();
    updateArticle();
    deleteArticle();
    createComment();
    deleteComment();
  }

  private void getArticle() {
    service.get(
        "/api/articles/:articleId",
        (Request request, Response response) -> {
          response.type("application/json");
          ArticleId articleId = new ArticleId(Long.parseLong(request.params("articleId")));
          try {
            Article article = articleService.findArticleById(articleId);
            LOG.debug("Article with id {} sent", articleId);
            response.status(201);
            return objectMapper.writeValueAsString(
                new ArticleGetResponse(article.getArticleName(), article.getTags(), article.getComments()));
          } catch (ArticleNotFoundException e) {
            LOG.warn("Cannot find article {}", articleId);
            response.status(400);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        }
    );
  }

  private void createArticle() {
    service.post(
        "/api/articles",
        (Request request, Response response) -> {
          response.type("application/json");
          String body = request.body();
          ArticleCreateRequest articleCreateRequest = objectMapper.readValue(body, ArticleCreateRequest.class);
          try {
            ArticleId articleId =
                articleService.createArticle(articleCreateRequest.articleTitle(), articleCreateRequest.tags());
            LOG.debug("Article with id {} successfully created", articleId);
            response.status(201);
            return objectMapper.writeValueAsString(new ArticleCreateResponse(articleId));
          } catch (ArticleIdDuplicatedException e) {
            LOG.warn("Cannot create article", e);
            response.status(400);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        }
    );
  }

  private void updateArticle() {
    service.put(
        "/api/articles/:articleId",
        (Request request, Response response) -> {
          response.type("application/json");
          String body = request.body();
          ArticleId articleId = new ArticleId(Long.parseLong(request.params("articleId")));
          ArticleUpdateRequest articleUpdateRequest = objectMapper.readValue(body, ArticleUpdateRequest.class);
          try {
            articleService.updateArticle(articleId, articleUpdateRequest.articleTitle(), articleUpdateRequest.tags());
            LOG.debug("Article with id {} successfully updated", articleId);
            response.status(201);
            return objectMapper.writeValueAsString(new ArticleUpdateResponse(articleId));
          } catch (ArticleNotFoundException e) {
            LOG.warn("Cannot update article {}, article does not exist", articleId);
            response.status(400);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        }
    );
  }

  private void deleteArticle() {
    service.delete(
        "/api/articles/:articleId",
        (Request request, Response response) -> {
          response.type("application/json");
          ArticleId articleId = new ArticleId(Long.parseLong(request.params("articleId")));
          try {
            articleService.deleteArticle(articleId);
            LOG.debug("Article with id {} successfully deleted", articleId);
            response.status(201);
            return objectMapper.writeValueAsString(new ArticleDeleteResponse(articleId));
          } catch (ArticleNotFoundException e) {
            LOG.warn("Cannot delete article {}, article does not exist", articleId);
            response.status(400);
            return objectMapper.writeValueAsString(new ArticleDeleteResponse(articleId));
          }
        }
    );
  }

  private void createComment() {
    service.post(
        "/api/comments",
        (Request request, Response response) -> {
          response.type("application/json");
          String body = request.body();
          CommentCreateRequest commentCreateRequest = objectMapper.readValue(body, CommentCreateRequest.class);
          try {
            CommentId commentId = articleService.createComment(commentCreateRequest.commentText(), commentCreateRequest.articleId());
            response.status(201);
            return objectMapper.writeValueAsString(new CommentCreateResponse(commentId));
          } catch (ArticleNotFoundException e) {
            LOG.warn("Article with id {} does not exist", commentCreateRequest.articleId().getId());
            response.status(400);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        }
    );
  }

  private void deleteComment() {
    service.delete(
        "/api/comments/:commentId",
        (Request request, Response response) -> {
          response.type("application/json");
          CommentId commentId = new CommentId(Long.parseLong(request.params("commentId")));
          try {
            articleService.deleteComment(commentId);
            response.status(201);
            return objectMapper.writeValueAsString(new CommentDeleteResponse(commentId));
          } catch (CommentNotFoundException e) {
            LOG.warn("Comment with id {} does not exist", commentId);
            response.status(400);
            return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
          }
        }
    );
  }
}
