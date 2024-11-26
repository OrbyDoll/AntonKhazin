package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Article.ArticleRepository;
import org.example.Article.ArticleService;
import org.example.Article.MemoryArticleRepository;
import org.example.Comment.CommentRepository;
import org.example.Comment.MemoryCommentRepository;
import org.example.Controllers.ArticleController;
import org.example.Controllers.ArticleFreemarkerController;
import org.example.Controllers.Controller;
import spark.Service;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    ObjectMapper objectMapper = new ObjectMapper();
    Service service = Service.ignite();
    CommentRepository memoryCommentRepository = new MemoryCommentRepository();
    ArticleRepository memoryArticleRepository = new MemoryArticleRepository();
    ArticleService articleService = new ArticleService(memoryCommentRepository, memoryArticleRepository);
    Controller articleController = new ArticleController(objectMapper, articleService, service);
    Controller articleFreemarkerController = new ArticleFreemarkerController(service, articleService, TemplateFactory.freeMarkerEngine());
    Application application = new Application(List.of(articleController, articleFreemarkerController));
    application.start();
  }
}
