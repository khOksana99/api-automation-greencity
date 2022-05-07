package com.greencity.auto.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.greencity.auto.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EcoNews {
    @Builder.Default
    private List<String> tags = Collections.singletonList("Events");
    @Builder.Default
    private String content = "undefined text for news";
    @Builder.Default
    private String title = "Title" + Util.getCurrentDateTime();
    private Integer id;
    private String creationDate;
    private EcoNewsAuthor author;
    private String imagePath;
    private String shortInfo;
    private Integer likes;
    private Integer countComments;
}
