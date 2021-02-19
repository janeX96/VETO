package com.veto.Services;

import com.veto.DTO.TreatmentReadModel;
import com.veto.model.*;
import com.veto.repositories.PetRepository;
import com.veto.repositories.TreatmentRepository;
import com.veto.repositories.VetRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentService {
    private TreatmentRepository repository;
    private VetRepository vetRepository;
    private PetRepository petRepository;

    public TreatmentService(TreatmentRepository repository,
                            VetRepository vetRepository,
                            PetRepository petRepository) {
        this.repository = repository;
        this.vetRepository = vetRepository;
        this.petRepository = petRepository;
    }


    public Treatment addTreatment(Treatment source)
    {

        int vetId = source.getVet().getId();
        int petId = source.getPet().getId();

        if (!vetRepository.existsById(vetId))
            throw new IllegalArgumentException("no vet");

        if (!petRepository.existsById(petId))
            throw new IllegalArgumentException("no pet");

//        Vet vet = vetRepository.findById(vetId).get();
//        Pet pet = petRepository.findById(petId).get();

        return repository.save(source);
    }

    public List<TreatmentReadModel> getAllTreatments() {
        List<TreatmentReadModel> res = new ArrayList<>();
        for(Treatment t : repository.findAll())
            res.add(new TreatmentReadModel(t));

        return res;
    }

    public Treatment findById(int id) throws NotFoundException {
        return repository.findById(id).orElseThrow(()-> new NotFoundException("not found"));
    }

    public void updateTreatment(int id, Treatment treatment)
    {
        int vetId = treatment.getVet().getId();
        int petId = treatment.getPet().getId();

        if (!vetRepository.existsById(vetId))
            throw new IllegalArgumentException("no vet");

        if (!petRepository.existsById(petId))
            throw new IllegalArgumentException("no pet");

        repository.findById(id).ifPresent(t->
        {
            t.updateFrom(treatment);
            repository.save(t);
        });
    }

    public void deleteTreatment(int id)
    {
        repository.findById(id).ifPresent(t->
        {
            repository.deleteTreatmentById(id);
        });
    }
}
