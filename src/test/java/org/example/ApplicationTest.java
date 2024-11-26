package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Article.Article;
import org.example.Article.ArticleId;
import org.example.Article.ArticleService;
import org.example.Article.Exceptions.ArticleNotFoundException;
import org.example.Article.MemoryArticleRepository;
import org.example.Comment.MemoryCommentRepository;
import org.example.Controllers.ArticleController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {
  private Service service;

  @BeforeEach
  void beforeEach() {
    service = Service.ignite();
  }

  @AfterEach
  void afterEach() {
    service.stop();
    service.awaitStop();
  }

  @Test
  void EndToEndApplicationTest() throws IOException, InterruptedException, ArticleNotFoundException {
    ObjectMapper objectMapper = new ObjectMapper();
    ArticleService articleService = new ArticleService(new MemoryCommentRepository(), new MemoryArticleRepository());
    Application application = new Application(List.of(new ArticleController(objectMapper, articleService, service)));
    application.start();
    service.awaitInitialization();
    HttpResponse<String> createArticleResponse = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .POST(
                    HttpRequest.BodyPublishers.ofString(
                        """
                                {
                                  "title": "Something",
                                  "tags": ["nothing"]
                                }
                            """
                    )
                )
                .uri(URI.create("http://localhost:4567/api/articles"))
                .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
          );
    assertEquals(201, createArticleResponse.statusCode());

    HttpResponse<String> createCommentResponse = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .POST(
                    HttpRequest.BodyPublishers.ofString(
                      """
                            {
                              "articleId": "1",
                              "text": "Something"
                            }
                            """
                    )
                )
                .uri(URI.create("http://localhost:4567/api/comments"))
                .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
        );
    assertEquals(201, createCommentResponse.statusCode());

    HttpResponse<String> articleUpdateRequest = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .PUT(
                    HttpRequest.BodyPublishers.ofString(
                      """
                            {
                              "title": "Something updated",
                              "tags": ["Nothing updated"]
                            }
                            """
                    )
                )
                .uri(URI.create("http://localhost:4567/api/articles/1"))
                .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
        );
    assertEquals(201, articleUpdateRequest.statusCode());

    HttpResponse<String> deleteCommentResponse = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create("http://localhost:4567/api/comments/1"))
                .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
        );
    assertEquals(201, deleteCommentResponse.statusCode());

    HttpResponse<String> getArticleResponse = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:4567/api/articles/1"))
                .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
        );
    assertEquals(201, getArticleResponse.statusCode());

    Article article = articleService.findArticleById(new ArticleId(1));
    assertEquals(article.getArticleName(), "Something updated");
    assertEquals(article.getTags(), Set.of("Nothing updated"));
    assertEquals(article.getComments(), List.of());

    HttpResponse<String> deleteArticleRequest = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create("http://localhost:4567/api/articles/1"))
                .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
        );
    assertEquals(201, deleteArticleRequest.statusCode());

    HttpResponse<String> badDeleteArticleRequest = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create("http://localhost:4567/api/articles/1"))
                .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
        );
    assertEquals(400, badDeleteArticleRequest.statusCode());

    HttpResponse<String> badGetArticleResponse = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:4567/api/articles/1"))
                .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
        );
    assertEquals(400, badGetArticleResponse.statusCode());

    HttpResponse<String> badDeleteCommentResponse = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create("http://localhost:4567/api/comments/1"))
                .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
        );
    assertEquals(400, badDeleteCommentResponse.statusCode());

    HttpResponse<String> badArticleUpdateRequest = HttpClient.newHttpClient()
        .send(
            HttpRequest.newBuilder()
                .PUT(
                    HttpRequest.BodyPublishers.ofString(
                        """
                              {
                                "title": "Something updated",
                                "tags": ["Nothing updated"]
                              }
                              """
                    )
                )
                .uri(URI.create("http://localhost:4567/api/articles/1"))
                .build(),
            HttpResponse.BodyHandlers.ofString(UTF_8)
        );
    assertEquals(400, badArticleUpdateRequest.statusCode());
  }
}
