package be.mira.jongeren.administration.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder( value = { "postcode", "name", "count" } )
public class GeographicDistribution {

    @JsonProperty
    private String postcode;

    @JsonProperty
    private String name;

    @JsonProperty
    private Long count;

    public GeographicDistribution() {
    }

    public GeographicDistribution(String postcode, String name, Long count) {
        this.postcode = postcode;
        this.name = name;
        this.count = count;
    }
}
