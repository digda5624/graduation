package com.graduation.project.domain.enumtype;

public enum PostType {
    FREE("자유 게시판"), MARKET("장터 게시판"), RECRUIT("룸메 구인 게시판");

    private final String name;

    PostType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
