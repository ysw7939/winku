package com.example.winku.Service.recomment;

import com.example.winku.domain.comment.Recomment;
import com.example.winku.dto.recomment.CreateRecommentDto;
import com.example.winku.dto.recomment.DeleteRecommentDto;
import com.example.winku.repository.Recomment.RecommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecommentServiceImpl implements RecommentService {
    private final RecommentRepository recommentRepository;

    @Autowired
    public RecommentServiceImpl(RecommentRepository recommentRepository) {
        this.recommentRepository = recommentRepository;
    }

    @Override
    public Recomment saveRecomment(Recomment recomment) {
        return recommentRepository.save(recomment);
    }

    @Override
    public List<Recomment> findAllbyCommentId(Long commentId) {
        return recommentRepository.findAllbyCommentId(commentId);
    }

    @Override
    public Recomment createRecomment(CreateRecommentDto recommentDto) {
        return recommentRepository.createRecomment(recommentDto);
    }

    @Override
    public void deleteRecommentList(List<Recomment> recommentList) {
        for (Recomment recomment : recommentList) {
            DeleteRecommentDto recommentDto = new DeleteRecommentDto(recomment.getId());
            recommentRepository.deleteRecomment(recommentDto);

        }
    }

    @Override
    public void deleteRecomment(DeleteRecommentDto recommentDto) {
        recommentRepository.deleteRecomment(recommentDto);
    }

    @Override
    public Recomment findbyRecommentId(long recommentId) {
        return recommentRepository.recommentId(recommentId);
    }
}
