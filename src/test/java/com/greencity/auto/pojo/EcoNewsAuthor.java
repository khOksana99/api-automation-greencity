package com.greencity.auto.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EcoNewsAuthor {
    private int id;
    private String name;
    private String userProfilePicturePath;
}
