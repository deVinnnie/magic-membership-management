package be.mira.jongeren.administration.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder( value = { "postcode", "count" } )
public class GeographicDistribution {

    @JsonProperty
    private String postcode;

    @JsonProperty
    private Long count;

    public GeographicDistribution() {
    }

    public GeographicDistribution(String postcode, Long count) {
        this.postcode = postcode;
        this.count = count;
    }
}
