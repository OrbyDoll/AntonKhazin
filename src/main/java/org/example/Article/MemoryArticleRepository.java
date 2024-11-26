package org.example.Article;

import org.example.Article.Exceptions.ArticleIdDuplicatedException;
import org.example.Article.Exceptions.ArticleNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryArticleRepository implements ArticleRepository {
  private AtomicLong currentId = new AtomicLong(0);
  private Map<Long, Article> articleContainer = new ConcurrentHashMap<>();

  @Override
  public ArticleId generateId() {
    return new ArticleId(currentId.incrementAndGet());
  }

  @Override
  public List<Article> findAll() {
    return articleContainer.values().stream().toList();
  }

  @Override
  public Article findById(ArticleId articleId) throws ArticleNotFoundException {
    Article desiredArticle = articleContainer.get(articleId.getId());
    if (desiredArticle == null) {
      throw new ArticleNotFoundException("Article with id: " + articleId + " does not exist");
    }
    return desiredArticle;
  }

  @Override
  public synchronized void create(Article article) throws ArticleIdDuplicatedException {
    if (articleContainer.get(article.getId()) != null) {
      throw new ArticleIdDuplicatedException("Article with id: " + article.getId() + " already exist");
    } else {
      articleContainer.put(article.getId(), article);
    }
  }

  public synchronized void update(Article article) throws ArticleNotFoundException {
    Article desiredArticle = articleContainer.get(article.getId());
    if (desiredArticle == null) {
      throw new ArticleNotFoundException("Article with id: " + article.getId() + " does not exist");
    } else {
      articleContainer.put(article.getId(), article);
    }
  }

  @Override
  public synchronized void delete(ArticleId articleId) throws ArticleNotFoundException {
    Article desiredArticle = articleContainer.get(articleId.getId());
    if (desiredArticle == null) {
      throw new ArticleNotFoundException("Article with id: " + articleId + " does not exist");
    } else {
      articleContainer.remove(articleId.getId());
    }
  }
}
