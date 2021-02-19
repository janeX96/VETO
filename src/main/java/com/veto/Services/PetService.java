package com.veto.Services;

import com.veto.DTO.PetReadModel;
import com.veto.event.PetEvent;
import com.veto.model.Pet;
import com.veto.repositories.PetRepository;
import javassist.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public List<Pet> getAllPets()
    {
//        List<PetReadModel> res = new ArrayList<>();
//        for (Pet pet : repository.findAll())
//            res.add(new PetReadModel(pet));

        List<Pet> res = repository.findAll();

        return res;
    }

    public Pet findById(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(()-> new NotFoundException("not found"));
    }


    public PetReadModel addPet(Pet pet) throws Exception {
        if (repository.existsByOwnerPhoneNumber(pet.getOwnerPhoneNumber()))
            throw new Exception("Podany numer telefonu jest już używany");

        return new PetReadModel(repository.save(pet));
    }

    public void updatePet(int id, Pet pet, ApplicationEventPublisher eventPublisher) throws Exception {
        if (repository.existsByOwnerPhoneNumber(pet.getOwnerPhoneNumber()) && !pet.getOwnerPhoneNumber().equals(repository.findById(id).get().getOwnerPhoneNumber()))
            throw new Exception("Podany numer telefonu jest już używany");

        repository.findById(id).ifPresent(p->
        {
            eventPublisher.publishEvent(p.updateFrom(pet));
            repository.save(p);
        });
    }

    public void deletePet(int id) {
        repository.deletePetById(id);
    }
}
