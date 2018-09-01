package com.karpuk.booking.entity;

import java.util.Objects;

public class Guest {

    public enum GuestTitleEnum{
        MR, MS, MRS
    }

    private String firstName;
    private String lastName;
    private String email;
    private GuestTitleEnum guestTitle;

    public Guest() {

    }

    public Guest(String firstName, String lastName, String email, GuestTitleEnum guestTitle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.guestTitle = guestTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GuestTitleEnum getGuestTitle() {
        return guestTitle;
    }

    public void setGuestTitle(GuestTitleEnum guestTitle) {
        this.guestTitle = guestTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(firstName, guest.firstName) &&
                Objects.equals(lastName, guest.lastName) &&
                Objects.equals(email, guest.email) &&
                guestTitle == guest.guestTitle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, guestTitle);
    }

    @Override
    public String toString() {
        return "Guest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", guestTitle=" + guestTitle +
                '}';
    }

}
