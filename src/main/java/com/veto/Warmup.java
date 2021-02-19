package com.veto;

import com.veto.model.Pet;
import com.veto.repositories.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(Warmup.class);

    private final PetRepository petRepository;

    public Warmup(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("Application warmup after context refreshed");
        final String name = "Filemon";
        if (!petRepository.existsByName(name)){
            logger.info("Nie ma żadnego zwierzaka o imieniu Filemon, dodaję go...");
            Pet pet = new Pet();
            pet.setName(name);
            pet.setOwnerFirstName("Janusz");
            pet.setOwnerLastName("Kowal");
            pet.setType("Kot");
            pet.setOwnerPhoneNumber("632356448");

            petRepository.save(pet);
        }
    }
}
