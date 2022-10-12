package com.akvelon.watertask.controller;

import com.akvelon.watertask.dto.CreateContainerFormDTO;
import com.akvelon.watertask.dto.CreateLivingBeeingFormDTO;
import com.akvelon.watertask.dto.LivingBeeingDTO;
import com.akvelon.watertask.entity.*;
import com.akvelon.watertask.entity.Converter.LivingBeeingConverter;
import com.akvelon.watertask.entity.contract.ContainerFactory;
import com.akvelon.watertask.entity.contract.LivingBeeingFactory;
import com.akvelon.watertask.service.DrinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
public class MainController {
    private final DrinkService drinkService;
    private final LivingBeeingFactory livingBeeingFactory;
    private final ContainerFactory containerFactory;
    private final LivingBeeingConverter livingBeeingConverter;

    public MainController(DrinkService drinkService, LivingBeeingFactory livingBeeingFactory, ContainerFactory containerFactory, LivingBeeingConverter livingBeeingConverter) {
        this.drinkService = drinkService;
        this.livingBeeingFactory = livingBeeingFactory;
        this.containerFactory = containerFactory;
        this.livingBeeingConverter = livingBeeingConverter;
    }

    @GetMapping("")
    public String displayMainPage(Model model){
        model.addAttribute("createLivingBeeingFormDTO",new CreateLivingBeeingFormDTO());
        LivingBeeing human = livingBeeingFactory.createLivingBeeing("human", 2.0);
        return "index";
    }

    @PostMapping("/create")
    public String submitForm(@ModelAttribute("createLivingBeeingFormDTO") CreateLivingBeeingFormDTO createLivingBeeingFormDTO,
                             RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("livingBeeingDTO",
                new LivingBeeingDTO(createLivingBeeingFormDTO.getStomachCapacity(),createLivingBeeingFormDTO.getLivingBeeing()));
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

    @PostMapping("/drink")
    public String drinkProcess(@ModelAttribute("container") CreateContainerFormDTO createContainerFormDTO,
                               @ModelAttribute("livingBeeingDTO") LivingBeeingDTO livingBeeingDTO,
                               RedirectAttributes redirectAttributes){
        LivingBeeing livingBeeing = livingBeeingFactory.createLivingBeeing(livingBeeingDTO.getLivingBeeingType(),livingBeeingDTO.getStomachMaximumVolume(),livingBeeingDTO.getStomachCurrentVolume());
        try{
            Container container = containerFactory.createContainer(createContainerFormDTO.getContainerType(),createContainerFormDTO.getCapacity());
            try{
                drinkService.drink(livingBeeing,container);
                redirectAttributes.addFlashAttribute("livingBeeingDTO",livingBeeingConverter.entityToDto(livingBeeing));
            }
            catch (IllegalArgumentException e){
                redirectAttributes.addFlashAttribute("livingBeeing",livingBeeing);
                return "redirect:/error/full";
            }
            redirectAttributes.addFlashAttribute("lastContainer",container);
        }
        catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("livingBeeing",livingBeeing);
            return "redirect:/error/containerProblem";
        }
        return "redirect:/"+livingBeeingDTO.getLivingBeeingType().toLowerCase();
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
        redirectAttributes.addFlashAttribute("livingBeeingDTO",livingBeeingConverter.entityToDto(livingBeeing));
        return (livingBeeing instanceof Human) ? "redirect:/human" : "redirect:/cat";
    }
}
