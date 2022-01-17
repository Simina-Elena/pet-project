package com.acc.petproject.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ActivityDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("capacity")
    private int capacity;

    @JsonProperty("type")
    private ActivityType activityType;

    @JsonProperty("shelterId")
    private Long shelterId;
}
