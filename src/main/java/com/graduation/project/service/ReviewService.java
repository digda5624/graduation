package com.graduation.project.service;

import com.graduation.project.domain.Review;
import com.graduation.project.domain.Room;
import com.graduation.project.domain.User;
import com.graduation.project.dto.ReviewSaveDTO;
import com.graduation.project.repository.ReviewRepository;
import com.graduation.project.repository.RoomRepository;
import com.graduation.project.repository.UserRepository;
import com.graduation.project.repository.dto.RoomReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Transactional(readOnly = true)
    public Map<String, Object> getRoomReviews(Long roomId){

        Map<String, Object> resultMap = new HashMap<>();
        Map<Integer, Integer> scoreMap = Map.of(1, 0, 2, 0, 3, 0, 4, 0, 5, 0);

        List<RoomReviewDTO> roomReviews = reviewRepository.getRoomReviews(roomId);
        roomReviews.stream().forEach(roomReviewDTO -> {
            Integer score = roomReviewDTO.getScore();
            scoreMap.put(score, scoreMap.get(score) + 1);
        });
        resultMap.put("reviewData", roomReviews);
        resultMap.put("scores", scoreMap);

        return resultMap;
    }

    @Transactional
    public Long register(ReviewSaveDTO dto){
        Long userId = dto.getUserId();
        Long roomId = dto.getRoomId();
        User user = userRepository.getById(userId);
        Room room = roomRepository.getById(roomId);

        Review review = Review.builder()
                .content(dto.getContent())
                .score(dto.getScore())
                .room(room)
                .user(user)
                .build();

        return reviewRepository.save(review).getId();
    }

}
