package com.karpuk.booking.entity;

import java.util.Objects;

public class Apartment {

    private String name;
    private double score;
    private String rating;
    private String location;

    public Apartment() {

    }

    public Apartment(String name, double score, String rating, String location) {
        this.name = name;
        this.score = score;
        this.rating = rating;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return Double.compare(apartment.score, score) == 0 &&
                Objects.equals(name, apartment.name) &&
                Objects.equals(rating, apartment.rating) &&
                Objects.equals(location, apartment.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score, rating, location);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", rating='" + rating + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

}
