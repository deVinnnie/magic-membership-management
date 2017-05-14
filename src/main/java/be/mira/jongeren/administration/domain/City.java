package be.mira.jongeren.administration.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;

@Entity
public class City extends AbstractEntity<Long>{

    @NotBlank
    private String postcode;

    @NotBlank
    private String name;

    @NotBlank
    private String region;

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "postcode='" + postcode + '\'' +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
