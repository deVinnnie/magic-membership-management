package be.mira.jongeren.administration.rest;

import be.mira.jongeren.administration.domain.PartakingType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PartakingDto {

    private UUID id;
    private PartakingType type;

    public PartakingDto() {
    }

    @JsonCreator
    public PartakingDto(
            @JsonProperty("id") UUID personId,
            @JsonProperty("type") PartakingType partakingType
    ) {
        this.id = personId;
        this.type = partakingType;
    }

    public UUID getId() {
        return id;
    }

    public PartakingType getType() {
        return type;
    }
}
