package com.veto.Services;

import com.veto.DTO.VetReadModel;
import com.veto.model.Vet;
import com.veto.repositories.VetRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VetService {
    private VetRepository repository;

    public VetService(VetRepository repository) {
        this.repository = repository;
    }

    public List<Vet> getAllVets()
    {
//        List<VetReadModel> res = new ArrayList<>();
//        for (Vet vet : repository.findAll())
//            res.add(new VetReadModel(vet));

        List<Vet> res = repository.findAll();

        return res;
    }

    public VetReadModel addVet(Vet vet) throws Exception {
        if (repository.existsByEmail(vet.getEmail()))
            throw new Exception("Podany email jest już używany");

        return new VetReadModel(repository.save(vet));
    }

    public boolean existsById(Integer id)
    {
        return repository.existsById(id);
    }

    public Vet findById(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(()-> new NotFoundException("not found"));
    }

    public void updateVet(int id, Vet vet) throws Exception {

        if (repository.existsByEmail(vet.getEmail()) && !vet.getEmail().equals(repository.findById(id).get().getEmail()))
            throw new Exception("Podany email jest już używany");

        repository.findById(id).ifPresent(v->
        {
            v.updateFrom(vet);
            repository.save(v);
        });
    }

    public void deleteVet(int id) {
        repository.deleteVetById(id);
    }
}
