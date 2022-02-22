package com.acc.petproject.adoption;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdoptionDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("visitorId")
    private Long visitorId;

    @JsonProperty("petId")
    private Long petId;

}
