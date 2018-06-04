package be.mira.jongeren.administration.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PartakingType {

//    @JsonProperty("participant")
    DEELNEMER("participant"),
//    @JsonProperty("assistant")
    LEIDING("assistant"),
//    @JsonProperty("external")
    EXTERN("external");

    private String representation;

    PartakingType(String representation){
        this.representation = representation;
    }

    @Override
    public String toString() {
        return this.representation;
    }

    @JsonValue
    public String getRepresentation(){
        return this.representation;
    }
}
