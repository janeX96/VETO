package com.veto.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name="pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min=2, max=30, message = "Pole powinno zawierać 2-30 znaków")
    @NotBlank(message = "Pole wymagane")
    @Column(name = "name")
    private String name;

    @Size(min=3, max=60, message = "Pole powinno zawierać 3-60 znaków")
    @NotBlank(message = "Pole wymagane")
    @Column(name = "type")
    private String type;

    @Size(min=2, max=30, message = "Pole powinno zawierać 2-30 znaków")
    @NotBlank(message = "Pole wymagane")
    @Column(name = "ownerFirstName")
    private String ownerFirstName;

    @Size(min=2, max=60, message = "Pole powinno zawierać 2-60 znaków")
    @NotBlank(message = "Pole wymagane")
    @Column(name = "ownerLastName")
    private String ownerLastName;

    @Pattern(regexp = "^\\d{9}$", message = "Podaj poprawny numer telefonu")
    @NotBlank(message = "Pole wymagane")
    @Column(name = "ownerPhoneNumber")
    private String ownerPhoneNumber;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private Set<Treatment> treatments;


    public Pet() {
    }

    public int getId() {
        return id;
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

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(Set<Treatment> treatments) {
        this.treatments = treatments;
    }

    public void updateFrom(Pet source)
    {
        this.name=source.getName();
        this.type=source.getType();
        this.ownerFirstName=source.getOwnerFirstName();
        this.ownerLastName=source.getOwnerLastName();
        this.ownerPhoneNumber=source.getOwnerPhoneNumber();
    }
}
