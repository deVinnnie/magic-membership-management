package be.mira.jongeren.administration.domain;

public enum PartakingType {
    DEELNEMER("participant"),
    LEIDING("assistant"),
    EXTERN("external");

    private String representation;

    PartakingType(String representation){
        this.representation = representation;
    }

    @Override
    public String toString() {
        return this.representation;
    }
}
