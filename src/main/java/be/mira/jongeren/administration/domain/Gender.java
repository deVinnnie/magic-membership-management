package be.mira.jongeren.administration.domain;

public enum Gender {
    F("female"),
    M("male"),
    X("unkown");

    private String title;

    Gender(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String toString(){
        return this.name();
    }
}
