package com.veto.controller;

import com.veto.DTO.VetReadModel;
import com.veto.Services.VetService;
import com.veto.model.Vet;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VetController {

    public static final Logger logger = LoggerFactory.getLogger(VetController.class);
    private final VetService service;

    public VetController(VetService service) {
        this.service = service;
    }

    @GetMapping("/vets")
    String readAllVets(Model model){
        logger.warn("Exposing all the vets!");

        List<VetReadModel> res = new ArrayList<>();

        for (Vet vet : service.getAllVets())
        {
            res.add(new VetReadModel(vet));
        }

        model.addAttribute("vets", res);
        return "pages/vet-list";
    }

    @GetMapping("/addvet")
    String addVet(Model model){

        model.addAttribute("vet", new Vet());
        model.addAttribute("default", "");
        return "pages/vet-form";
    }

    @GetMapping("/vet-details")
    String getDetails(@RequestParam("id") int id, Model model){
        //logger.warn("Exposing all the vets!");

        try {
            Vet vet = service.findById(id);
            model.addAttribute("vet", vet);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return "pages/vet-details";
    }


    @GetMapping("/vets/edit/{id}")
    String editVet(@PathVariable int id, Model model, Authentication auth) throws NotFoundException{
        if (auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN"))){
            Vet vet = service.findById(id);
            model.addAttribute("vet",vet);
            model.addAttribute("default","");
            return "pages/vet-edit";
        }
        return "pages/index";
    }



    @Transactional
    @PostMapping("/vets/update/{id}")
    public String updateVet(@PathVariable int id, @Valid Vet updateFrom, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "pages/vet-edit";
        }
        else
            try {
                service.updateVet(id,updateFrom);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                bindingResult.rejectValue("email","error.user", "Podany email jest już używany");
                return "pages/vet-edit";
            }

        return "redirect:/vet-details?id="+id;
    }


    @PostMapping("/vets")
    String saveVet(@Valid Vet toCreate, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "pages/vet-form";
        }
        else
            try {
                VetReadModel result = service.addVet(toCreate);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                bindingResult.rejectValue("email","error.user", "Podany email jest już używany");
                return "pages/vet-form";
            }

            return "redirect:/vets";
    }


    @Transactional
    @GetMapping("/vets/delete/{id}")
    public String deleteVet(@PathVariable int id){

        service.deleteVet(id);

        return "redirect:/vets";
    }


}
