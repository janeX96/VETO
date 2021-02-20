package com.veto.controller;

import com.veto.DTO.PetReadModel;
import com.veto.Services.PetService;
import com.veto.model.Pet;
import com.veto.repositories.PetRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PetController {

    public static final Logger logger = LoggerFactory.getLogger(PetController.class);
    private final ApplicationEventPublisher eventPublisher;
    private final PetService service;
    private final PetRepository repository;

    public PetController(ApplicationEventPublisher eventPublisher, PetService service, PetRepository repository) {
        this.eventPublisher = eventPublisher;
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/index")
    String index(){
        return "pages/index";
    }

    @GetMapping("/pets")
    String readAllVets(Model model){
        logger.warn("Exposing all the pets!");

        List<PetReadModel> res = new ArrayList<>();
        for (Pet pet : service.getAllPets())
        {
            res.add(new PetReadModel(pet));
        }



        model.addAttribute("pets", res);
        return "pages/pet-list";
    }

    @GetMapping("/addpet")
    String addPet(Model model){
        model.addAttribute("pet", new Pet());
        model.addAttribute("default", "");
        return "pages/pet-form";
    }

    @PostMapping("/pets")
    String savePet(@Valid Pet toCreate, BindingResult bindingResult, Model model){


        if(bindingResult.hasErrors()){
            return "pages/pet-form";
        }
        else
            try {
                PetReadModel result = service.addPet(toCreate);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                bindingResult.rejectValue("ownerPhoneNumber","error.user", "Podany numer telefonu jest już używany");
                return "pages/pet-form";
            }
        return "redirect:/pets";
    }

    @GetMapping("/pet-details")
    String getDetails(@RequestParam("id") int id, Model model){
        //logger.warn("Exposing all the vets!");

        try {
            Pet pet = service.findById(id);
            model.addAttribute("pet", pet);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return "pages/pet-details";
    }

    @GetMapping("/pets/edit/{id}")
    String editPet(@PathVariable int id, Model model) throws NotFoundException{
        Pet pet = service.findById(id);
        model.addAttribute("pet",pet);
        model.addAttribute("default", "");
        return "pages/pet-edit";
    }

    @Transactional
    @PostMapping("/pets/update/{id}")
    public String updatePet(@PathVariable int id, @Valid Pet updateFrom, BindingResult bindingResult, Model model){


        if(bindingResult.hasErrors()){
            return "pages/pet-edit";
        }
        else
            try {
                service.updatePet(id,updateFrom,eventPublisher);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                bindingResult.rejectValue("ownerPhoneNumber","error.user", "Podany numer telefonu jest już używany");
                return "pages/pet-edit";
            }

        return "redirect:/pet-details?id="+id;
    }


    @Transactional
    @GetMapping("/pets/delete/{id}")
    public String deleteVet(@PathVariable int id){

        service.deletePet(id);

        return "redirect:/pets";
    }
}
