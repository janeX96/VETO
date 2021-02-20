package com.veto.controller;

import com.veto.DTO.PetReadModel;
import com.veto.DTO.TreatmentReadModel;
import com.veto.DTO.TreatmentWriteModel;
import com.veto.DTO.VetReadModel;
import com.veto.Services.PetService;
import com.veto.Services.TreatmentService;
import com.veto.Services.VetService;
import com.veto.model.Pet;
import com.veto.model.Treatment;
import com.veto.model.Vet;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.swing.*;
import javax.validation.Valid;
import java.util.List;

@Controller
public class TreatmentController {

    public static final Logger logger = LoggerFactory.getLogger(TreatmentController.class);
    private final TreatmentService service;
    private final VetService vetService;
    private final PetService petService;


    public TreatmentController(TreatmentService service, VetService vetService, PetService petService) {
        this.service = service;
        this.vetService = vetService;
        this.petService = petService;
    }

    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/treatments")
   String readAllTreatments(Model model){
        logger.warn("Exposing all the vets!");
        List<TreatmentReadModel> res = service.getAllTreatments();
        model.addAttribute("treatments", res);

        return "pages/treatment-list";
    }



    @GetMapping("/addtreatment")
    String addTreatment(Model model){

//        List<VetReadModel> vets = vetService.getAllVets();
//        List<PetReadModel> pets = petService.getAllPets();
//        model.addAttribute("treatment", new TreatmentWriteModel());
        List<Vet> vets = vetService.getAllVets();
        List<Pet> pets = petService.getAllPets();
        model.addAttribute("treatment", new Treatment());
        model.addAttribute("pets", pets);
        model.addAttribute("vets", vets);
        model.addAttribute("default","");
        return "pages/treatment-form";
    }

    @PostMapping("/treatments")
    String saveTreatment(@Valid Treatment toCreate,BindingResult bindingResult, Model model){

        List<Vet> vets = vetService.getAllVets();
        List<Pet> pets = petService.getAllPets();

        if(bindingResult.hasErrors()){
            model.addAttribute("treatment", toCreate);
            model.addAttribute("pets", pets);
            model.addAttribute("vets", vets);

            //model.addAttribute("default","");
            return "pages/treatment-form";
        }
        else
            try {
//                model.addAttribute("pets", pets);
//                model.addAttribute("vets", vets);
//                model.addAttribute("treatment", toCreate);
//                model.addAttribute("default","");
                service.addTreatment(toCreate);

            } catch (Exception e) {
                System.out.println(e.getMessage());

//                if(e.getMessage().equals("no vet"))
//                bindingResult.rejectValue("vet","error.user", "Pole wymagane");
//
//                if(e.getMessage().equals("no pet"))
//                    bindingResult.rejectValue("pet","error.user", "Pole wymagane");

                return "pages/treatment-form";
            }




        return "redirect:/treatments";

    }

    @GetMapping("/treatment-details")
    String getDetails(@RequestParam("id") int id, Model model){

        try {
            Treatment treatment = service.findById(id);
            model.addAttribute("treatment", treatment);

        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return "pages/treatment-details";
    }


    @GetMapping("/treatments/edit/{id}")
    String editTreatment(@PathVariable int id, Model model) throws NotFoundException{
        Treatment treatment = service.findById(id);
        model.addAttribute("treatment",treatment);
        model.addAttribute("default","");
//        List<VetReadModel> vets = vetService.getAllVets();
//        List<PetReadModel> pets = petService.getAllPets();
        List<Vet> vets = vetService.getAllVets();
        List<Pet> pets = petService.getAllPets();
        model.addAttribute("pets", pets);
        model.addAttribute("vets", vets);
        return "pages/treatment-edit";
    }


    @Transactional
    @PostMapping("/treatments/update/{id}")
    public String updateTreatment(@PathVariable int id, @Valid Treatment updateFrom,
                                  BindingResult bindingResult, Model model){


        if(bindingResult.hasErrors()){
            List<Vet> vets = vetService.getAllVets();
            List<Pet> pets = petService.getAllPets();
            model.addAttribute("pets", pets);
            model.addAttribute("vets", vets);
            model.addAttribute("treatment", updateFrom);
            return "pages/treatment-edit";
        }
        else
            try {
                service.updateTreatment(id,updateFrom);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                if(e.getMessage().equals("no vet"))
                    bindingResult.rejectValue("vet","error.user", "Pole wymagane");

                if(e.getMessage().equals("no pet"))
                    bindingResult.rejectValue("pet","error.user", "Pole wymagane");

                return "pages/treatment-edit";
            }


        return "redirect:/treatment-details?id="+id;
    }

    @Transactional
    @GetMapping("/treatments/delete/{id}")
    public String deleteTreatment(@PathVariable int id){

        service.deleteTreatment(id);

        return "redirect:/treatments";
    }


}
