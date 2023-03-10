package com.example.winku.repository.comment;

import com.example.winku.domain.comment.Comment;
import com.example.winku.dto.comment.CreateCommentDto;
import com.example.winku.dto.comment.DeleteCommentDto;
import com.example.winku.repository.feed.FeedRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryCommentRepository implements CommentRepository{
    public static final Map<Long,Comment> store = new HashMap<>();
    private final FeedRepository feedRepository;

    public MemoryCommentRepository(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    private static long sequence = 0L;
    private Date date = new Date();



    @Override
    public Comment save(Comment comment) {
        comment.setId(++sequence);
        comment.setDate(date);
        store.put(comment.getId(), comment);

        return comment;
    }

    @Override
    public List<Comment> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Comment> findAllbyFeedId(long feedId) {
        List<Comment> commentStoreList = new ArrayList<>(store.values());
        List<Comment> commentList = new ArrayList<>();
        for (Comment comment : commentStoreList) {
            if (comment.getFeedId() == feedId) {
                commentList.add(comment);
            }
        }
        return commentList;
    }

    @Override
    public Comment createComment(CreateCommentDto commentDto) {
        Comment comment = new Comment(commentDto.getFeedId(),commentDto.getLoginId(), commentDto.getUserName(), commentDto.getContent(), commentDto.getProfile());
        comment.setId(++sequence);
        comment.setDate(date);
        store.put(comment.getId(), comment);
        return  comment;
    }

    @Override
    public void deleteComment(DeleteCommentDto commentDto) {
        store.remove(commentDto.getId());
    }

    @Override
    public Comment findCommentId(Long commentId) {
        Comment comment = store.get(commentId);
        return comment;
    }

}
