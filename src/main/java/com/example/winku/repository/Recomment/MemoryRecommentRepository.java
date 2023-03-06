package com.example.winku.repository.Recomment;

import com.example.winku.domain.comment.Recomment;
import com.example.winku.dto.recomment.CreateRecommentDto;
import com.example.winku.dto.recomment.DeleteRecommentDto;
import com.example.winku.repository.comment.CommentRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;
@Repository
public class MemoryRecommentRepository implements RecommentRepository{

    public static final Map<Long, Recomment> store = new HashMap<>();
    private final CommentRepository commentRepository;

    public MemoryRecommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    private static long sequence = 0L;
    private Date date = new Date();

    @Override
    public Recomment save(Recomment recomment) {
        recomment.setId(++sequence);
        recomment.setDate(date);
        store.put(recomment.getId(), recomment);

        return recomment;
    }



    @Override
    public List<Recomment> findAllbyCommentId(long commentId) {
        List<Recomment> recommentStoreList = new ArrayList<>(store.values());
        List<Recomment> recommentList = new ArrayList<>();
        for (Recomment recomment : recommentStoreList) {
            if (recomment.getCommentId() == commentId) {
                recommentList.add(recomment);
            }
        }
        return recommentList;
    }

    @Override
    public Recomment createRecomment(CreateRecommentDto recommentDto) {
        Recomment recomment = new Recomment(recommentDto.getCommentId(),recommentDto.getLoginId(), recommentDto.getContent(), recommentDto.getUserName(), recommentDto.getProfile());
        recomment.setId(++sequence);
        recomment.setDate(date);
        store.put(recomment.getId(), recomment);

        return recomment;
    }

    @Override
    public void deleteRecomment(DeleteRecommentDto recommentDto) {
        store.remove(recommentDto.getId());
    }

    @Override
    public Recomment recommentId(long recommentId) {
        Recomment recomment = store.get(recommentId);
        return recomment;
    }


}
