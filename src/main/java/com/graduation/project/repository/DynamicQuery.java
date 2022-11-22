package com.graduation.project.repository;

import com.graduation.project.domain.enumtype.DealType;
import com.graduation.project.domain.enumtype.RoomType;
import com.graduation.project.dto.RoomSearchRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.graduation.project.domain.QRoom.room;

@Repository
@RequiredArgsConstructor
public class DynamicQuery {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Long> searchRoom(RoomSearchRequest request) {
        return jpaQueryFactory.select(room.id)
                .from(room)
                .where(
                        searchContentLike(request.getSearchContent()),
                        feeBetween(request.getSManagementFees(), request.getEManagementFees()),
                        distanceBetween(request.getSDistance(), request.getEDistance()),
                        roomTypeEquals(request.getRoomType()),
                        dealTypeEquals(request.getDealType())
                )
                .fetch();
    }

    private BooleanExpression searchContentLike(String content){
        return content == null || content.equals("") ?
                null : room.address.like("%" + content + "%");
    }

    private BooleanExpression feeBetween(Integer s, Integer e){
        return s == null || e == null ?
                null : room.managementFees.between(s, e);
    }

    private BooleanExpression distanceBetween(Double s, Double e){
        return s == null || e == null ?
                null : room.distance.between(s, e);
    }

    private BooleanExpression roomTypeEquals(RoomType roomType){
        return roomType == null ?
                null : room.roomType.eq(roomType);
    }

    private BooleanExpression dealTypeEquals(DealType dealType){
        return dealType == null ?
                null : room.dealType.eq(dealType);
    }



}
