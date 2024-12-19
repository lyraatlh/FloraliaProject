package com.example.floraliaproject;

public class Flower {
    private String name;
    private String funFact;
    private String scientificName;
    private String description;
    private String imageUrl;

    public Flower() {
    }

    public Flower(String name, String funFact, String description, String imageUrl) {
        this.name = name;
        this.funFact = funFact;
        this.scientificName = scientificName;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() { return name; }
    public String getFunFact() { return funFact; }
    public String getScientificName() { return scientificName; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
}