package com.graduation.project.init;

import com.graduation.project.domain.Review;
import com.graduation.project.domain.Room;
import com.graduation.project.domain.User;
import com.graduation.project.domain.enumType.DealType;
import com.graduation.project.domain.enumType.RoomType;
import com.graduation.project.repository.ReviewRepository;
import com.graduation.project.repository.RoomRepository;
import com.graduation.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInit {

    private final RoomRepository roomRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        log.info("Repository Data 초기화 중");
        initData();
    }

    @Transactional
    public void initData() {

        User user = new User();
        Room room1 = Room.builder()
                .address("address")
                .roomType(RoomType.ONE_ROOM)
                .dealType(DealType.MONTH)
                .distance(10D)
                .price(10000)
                .sigungu("sigungu")
                .managementFees(100)
                .build();

        Room room2 = Room.builder()
                .address("address")
                .roomType(RoomType.ONE_ROOM)
                .dealType(DealType.MONTH)
                .distance(10D)
                .price(10000)
                .sigungu("sigungu")
                .managementFees(100)
                .build();

        Room room3 = Room.builder()
                .address("address")
                .roomType(RoomType.ONE_ROOM)
                .dealType(DealType.MONTH)
                .distance(10D)
                .price(10000)
                .sigungu("sigungu")
                .managementFees(100)
                .build();

        Review review1 = Review.builder()
                .user(user)
                .room(room1)
                .score(4)
                .content("리뷰 1")
                .build();

        Review review2 = Review.builder()
                .user(user)
                .room(room2)
                .score(3)
                .content("리뷰 2")
                .build();

        Review review3 = Review.builder()
                .user(user)
                .room(room1)
                .score(3)
                .content("리뷰 3")
                .build();

        userRepository.save(user);
        roomRepository.save(room1);
        roomRepository.save(room2);
        roomRepository.save(room3);
        reviewRepository.save(review1);
        reviewRepository.save(review2);
        reviewRepository.save(review3);
    }

}
