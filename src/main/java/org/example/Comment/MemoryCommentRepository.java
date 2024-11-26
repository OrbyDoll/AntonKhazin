package org.example.Comment;

import org.example.Comment.Exceptions.CommentIdDuplicatedException;
import org.example.Comment.Exceptions.CommentNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryCommentRepository implements CommentRepository{
  private AtomicLong currentId = new AtomicLong(0);
  private Map<Long, Comment> commentContainer = new ConcurrentHashMap<>();

  @Override
  public CommentId generateId() {
    return new CommentId(currentId.incrementAndGet());
  }

  @Override
  public List<Comment> findAll() {
    return commentContainer.values().stream().toList();
  }

  @Override
  public Comment findById(CommentId commentId) throws CommentNotFoundException {
    Comment desiredComment = commentContainer.get(commentId.getId());
    if (desiredComment == null) {
      throw new CommentNotFoundException("Comment with id: " + commentId.getId() + " does not exist");
    }
    return desiredComment;
  }

  @Override
  public synchronized void create(Comment comment) throws CommentIdDuplicatedException {
    if (commentContainer.get(comment.getCommentId().getId()) != null) {
      throw new CommentIdDuplicatedException("Comment with id: " + comment.getCommentId() + " already exist");
    } else {
      commentContainer.put(comment.getCommentId().getId(), comment);
    }
  }

  @Override
  public synchronized void update(Comment comment) throws CommentNotFoundException {
    if (commentContainer.get(comment.getCommentId().getId()) == null) {
      throw new CommentNotFoundException("Comment with id: " + comment.getCommentId() + " does not exist");
    } else {
      commentContainer.put(comment.getCommentId().getId(), comment);
    }
  }

  @Override
  public synchronized void delete(CommentId commentId) throws CommentNotFoundException {
    Comment desiredComment = commentContainer.get(commentId.getId());
    if (desiredComment == null) {
      throw new CommentNotFoundException("Comment with id: " + commentId + " does not exist");
    } else {
      commentContainer.remove(commentId.getId());
    }
  }
}
