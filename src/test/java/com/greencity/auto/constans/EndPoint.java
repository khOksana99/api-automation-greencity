package com.greencity.auto.constans;

public enum EndPoint {
    LOGIN("/ownSecurity/signIn"),
    ECONEWS("/econews"),
    LIKE_ECONEWS("/econews/like"),
    ECONEWS_CURRENT_USER("/econews/byUser"),
    IS_LIKED_BY_USER("/econews/isLikedByUser"),
    COUNT_LIKES("/econews/countLikes/"),
    GET_THREE_LATEST_NEWS("/econews/newest"),
    GET_NEWS_BY_TAG("/econews/tags"),
    GET_ALL_TAGS("/econews/tags/all"),
    EDIT_NEWS("/econews/update"),
    COMMENT_NEWS("/econews/comments/"),
    ACTIVE_COMMENTS_NEWS("/econews/comments/active"),
    COUNT_COMMENTS_NEWS("/econews/comments/count/comments/"),
    LIKE_COMMENT_NEWS("/econews/comments/like");

    private final String endpoint;

    EndPoint(String endpoint){this.endpoint = endpoint;}

    public String getEndpoint() {return endpoint;}
}
