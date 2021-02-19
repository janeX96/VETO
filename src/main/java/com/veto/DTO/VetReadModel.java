package com.veto.DTO;

import com.veto.model.Vet;

public class VetReadModel {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public VetReadModel(Vet vet) {
        this.id=vet.getId();
        this.firstName = vet.getFirstName();
        this.lastName = vet.getLastName();
        this.email= vet.getEmail();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
