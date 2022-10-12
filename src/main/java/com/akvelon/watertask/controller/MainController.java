package com.akvelon.watertask.controller;

import com.akvelon.watertask.dto.CreateContainerFormDTO;
import com.akvelon.watertask.dto.CreateLivingBeeingFormDTO;
import com.akvelon.watertask.entity.*;
import com.akvelon.watertask.entity.contracts.ContainerFactory;
import com.akvelon.watertask.entity.contracts.LivingBeeingFactory;
import com.akvelon.watertask.service.DrinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
    private final DrinkService drinkService;
    private final LivingBeeingFactory livingBeeingFactory;
    private final ContainerFactory containerFactory;

    public MainController(DrinkService drinkService, LivingBeeingFactory livingBeeingFactory, ContainerFactory containerFactory) {
        this.drinkService = drinkService;
        this.livingBeeingFactory = livingBeeingFactory;
        this.containerFactory = containerFactory;
    }

    @GetMapping("")
    public String displayMainPage(Model model){
        model.addAttribute("createLivingBeeingFormDTO",new CreateLivingBeeingFormDTO());
        return "index";
    }

    @PostMapping("/create")
    public String submitForm(@ModelAttribute("createLivingBeeingFormDTO") CreateLivingBeeingFormDTO createLivingBeeingFormDTO,
                             RedirectAttributes redirectAttributes) {
        String livingBeeing = createLivingBeeingFormDTO.getLivingBeeing();
        double stomachCapacity = createLivingBeeingFormDTO.getStomachCapacity();
        redirectAttributes.addFlashAttribute("livingBeeing",livingBeeingFactory.createLivingBeeing(livingBeeing,stomachCapacity));
        return "redirect:/" + createLivingBeeingFormDTO.getLivingBeeing();
    }

    @GetMapping("/human")
    public String displayHumanPage(Model model){
        model.addAttribute("container",new CreateContainerFormDTO());
        return "human";
    }

    @GetMapping("/cat")
    public String displayCatPage(Model model){
        model.addAttribute("container",new CreateContainerFormDTO());
        return "cat";
    }

    @PostMapping("/drink/{}")
    public String drinkProcess(@ModelAttribute("container") CreateContainerFormDTO createContainerFormDTO,
                               @ModelAttribute("livingBeeing") Human livingBeeing,
                               RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("livingBeeing",livingBeeing);
        try{
            Container container = containerFactory.createContainer(createContainerFormDTO.getType(),createContainerFormDTO.getCapacity());
            try{
                drinkService.drink(livingBeeing,container);
            }
            catch (IllegalArgumentException e){
                return "redirect:/error/full";
            }
            redirectAttributes.addFlashAttribute("lastContainer",container);
        }
        catch (IllegalArgumentException e){
            return "redirect:/error/containerProblem";
        }
        return "redirect:/human";
    }

    @PostMapping("/drinkCat")
    public String drinkProcess(@ModelAttribute("container") CreateContainerFormDTO createContainerFormDTO,
                               @ModelAttribute("livingBeeing") Cat livingBeeing,
                               RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("livingBeeing",livingBeeing);
        try{
            Container container = containerFactory.createContainer(createContainerFormDTO.getType(),createContainerFormDTO.getCapacity());
            if(container instanceof Bottle)
                return "redirect:/error/wrongContainer";
            try{
                drinkService.drink(livingBeeing,container);
            }
            catch (IllegalArgumentException e){
                return "redirect:/error/full";
            }
            redirectAttributes.addFlashAttribute("lastContainer",container);
        }
        catch (IllegalArgumentException e){
            return "redirect:/error/containerProblem";
        }
        return "redirect:/cat";
    }

    @GetMapping(value = "/error/{error}")
    public String displayMainPageWithError(Model model,
                                           @PathVariable String error,
                                           @ModelAttribute("livingBeeing") LivingBeeing livingBeeing,
                                           RedirectAttributes redirectAttributes){
        if(livingBeeing.isStomachFull()){
            redirectAttributes.addFlashAttribute("error",error);
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("error",error);
        redirectAttributes.addFlashAttribute("livingBeeing",livingBeeing);
        return (livingBeeing instanceof Human) ? "redirect:/human" : "redirect:/cat";
    }
}
