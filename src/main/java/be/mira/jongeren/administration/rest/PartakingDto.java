package be.mira.jongeren.administration.rest;

import be.mira.jongeren.administration.domain.PartakingType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PartakingDto {

    private Long id;
    private PartakingType type;

    public PartakingDto() {
    }

    @JsonCreator
    public PartakingDto(
            @JsonProperty("id") Long personId,
            @JsonProperty("type") PartakingType partakingType
    ) {
        this.id = personId;
        this.type = partakingType;
    }

    public Long getId() {
        return id;
    }

    public PartakingType getType() {
        return type;
    }
}
