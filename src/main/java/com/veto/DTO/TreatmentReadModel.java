package com.veto.DTO;

import com.veto.model.Pet;
import com.veto.model.Treatment;
import com.veto.model.Vet;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class TreatmentReadModel {
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;
    private String description;
    private Vet vet;
    private Pet pet;

    public TreatmentReadModel(Treatment treatment) {
        this.id = treatment.getId();
        this.description = treatment.getDescription();
        this.date = treatment.getDate();
        this.vet = treatment.getVet();
        this.pet = treatment.getPet();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getFormattedDate() {
        String splitted[] = getDate().toString().split("T");
        String myDate = splitted[0] + ", godz: "+ splitted[1];

        return myDate;
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

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

