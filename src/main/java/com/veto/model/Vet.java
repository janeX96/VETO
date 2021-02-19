package com.veto.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name="vets")
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min=2, max=30, message = "Pole powinno zawierać 2-30 znaków")
    @NotBlank(message = "Pole jest wymagane")
    @Column(name = "firstName")
    private String firstName;

    @Size(min=2, max=60, message = "Pole powinno zawierać 2-60 znaków")
    @NotBlank(message = "Pole jest wymagane")
    @Column(name = "lastName")
    private String lastName;

    @Email(message = "Podaj poprawny adres e-mail")
    @NotBlank(message = "Podaj poprawny email")
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "vet", cascade = CascadeType.ALL)
    private Set<Treatment> treatments;


    public Vet() {
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public Set<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(Set<Treatment> treatments) {
        this.treatments = treatments;
    }

    public void updateFrom(Vet source)
    {
        this.firstName = source.firstName;
        this.lastName = source.lastName;
        this.email = source.email;
    }
}
