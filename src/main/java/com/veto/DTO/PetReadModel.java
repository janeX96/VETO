package com.veto.DTO;

import com.veto.model.Pet;

public class PetReadModel {
    private int id;
    private String name;
    private String type;
    private String ownerLastName;
    private String ownerFirstName;
    private String ownerPhoneNumber;

    public PetReadModel(Pet pet) {
        id = pet.getId();
        name=pet.getName();
        type=pet.getType();
        ownerFirstName=pet.getOwnerFirstName();
        ownerLastName=pet.getOwnerLastName();
        ownerPhoneNumber=pet.getOwnerPhoneNumber();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }
}
