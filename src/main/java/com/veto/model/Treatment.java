package com.veto.model;


import com.veto.DTO.TreatmentReadModel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Entity
@Table(name="treatments")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Należy wybrać datę zabiegu")
    @Column(name= "date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @Size(min = 5, max = 60, message = "Pole powinno zawierać 5-60 znaków")
    @NotBlank(message = "Pole wymagane")
    @Column(name= "description")
    private String description;


    @ManyToOne
    @JoinColumn(name = "pet_id")
    @NotNull(message = "Pole wymagane")
    private Pet pet;


    @ManyToOne
    @JoinColumn(name = "vet_id")
    @NotNull(message = "Pole wymagane")
    private Vet vet;

    public Treatment() {
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public void updateFrom(Treatment t)
    {
        this.date = t.getDate();
        this.setPet(t.getPet());
        this.setVet(t.getVet());
        this.description=t.getDescription();
    }

    public TreatmentReadModel toReadModel(){
        return new TreatmentReadModel(this);
    }
}
