package com.greencity.auto.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
    private Integer id;
    private String modifiedDate;
    private EcoNewsAuthor author;
    private String text;
    private Integer replies;
    private Integer likes;
    private Boolean currentUserLiked;
    private String status;
}
