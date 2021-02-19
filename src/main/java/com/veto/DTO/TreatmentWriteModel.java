package com.veto.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.veto.model.Pet;
import com.veto.model.Treatment;
import com.veto.model.Vet;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TreatmentWriteModel {

    private int id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;
    private String description;
    private int vetId;
    private int petId;


    public TreatmentWriteModel() {
    }

    public TreatmentWriteModel(Treatment treatment) {
        this.id = treatment.getId();
        this.date = treatment.getDate();
        this.description = treatment.getDescription();
        this.vetId = treatment.getVet().getId();
        this.petId = treatment.getPet().getId();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getVetId() {
        return vetId;
    }

    public void setVetId(int vetId) {
        this.vetId = vetId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }


    public Treatment toTreatment(Pet pet, Vet vet){
        var result = new Treatment();

        result.setDescription(description);
        result.setDate(date);
        result.setVet(vet);
        result.setPet(pet);

        return result;
    }
}

