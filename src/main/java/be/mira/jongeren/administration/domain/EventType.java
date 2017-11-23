package be.mira.jongeren.administration.domain;

public enum EventType {
    MAIN_SEQUENCE,
    SUPERNOVA,
    OFF_TRACK;

    @Override
    public String toString() {
        return this.name();
    }
}
