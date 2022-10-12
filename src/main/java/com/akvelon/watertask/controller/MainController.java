package com.akvelon.watertask.controller;

import com.akvelon.watertask.dto.CreateContainerFormDTO;
import com.akvelon.watertask.dto.CreateLivingBeeingFormDTO;
import com.akvelon.watertask.dto.LivingBeeingDTO;
import com.akvelon.watertask.entity.Container;
import com.akvelon.watertask.entity.Human;
import com.akvelon.watertask.entity.LivingBeeing;
import com.akvelon.watertask.entity.contract.ContainerFactory;
import com.akvelon.watertask.entity.contract.LivingBeeingFactory;
import com.akvelon.watertask.entity.converter.LivingBeeingConverter;
import com.akvelon.watertask.service.DrinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {
    public static final String LIVING_BEEING_DTO = "livingBeeingDTO";
    public static final String REDIRECT = "redirect:/";
    public static final String ERROR = "error";
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
        return "index";
    }

    @PostMapping("/create")
    public String submitForm(@ModelAttribute("createLivingBeeingFormDTO") CreateLivingBeeingFormDTO createLivingBeeingFormDTO,
                             RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(LIVING_BEEING_DTO,
                new LivingBeeingDTO(createLivingBeeingFormDTO.getStomachCapacity(),createLivingBeeingFormDTO.getLivingBeeing()));
        return REDIRECT + createLivingBeeingFormDTO.getLivingBeeing();
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
            drinkService.drink(livingBeeing,container);
            redirectAttributes.addFlashAttribute(LIVING_BEEING_DTO,livingBeeingConverter.entityToDto(livingBeeing));
            redirectAttributes.addFlashAttribute("lastContainer",container);
        }
        catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("livingBeeing",livingBeeing);
            redirectAttributes.addFlashAttribute(ERROR,e.getMessage());
            return "redirect:/errorDrinking";
        }
        return REDIRECT+livingBeeingDTO.getLivingBeeingType().toLowerCase();
    }

    @GetMapping(value = "/errorDrinking")
    public String displayMainPageWithError(Model model,
                                           @ModelAttribute(ERROR) String error,
                                           @ModelAttribute("livingBeeing") LivingBeeing livingBeeing,
                                           RedirectAttributes redirectAttributes){
        if(livingBeeing.isStomachFull()){
            redirectAttributes.addFlashAttribute(ERROR,error);
            return REDIRECT;
        }
        redirectAttributes.addFlashAttribute(ERROR,error);
        redirectAttributes.addFlashAttribute(LIVING_BEEING_DTO,livingBeeingConverter.entityToDto(livingBeeing));
        return (livingBeeing instanceof Human) ? "redirect:/human" : "redirect:/cat";
    }

    @GetMapping("/redirectWithRedirectView")
    public RedirectView redirectWithUsingRedirectView(
            RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");
        return new RedirectView("redirectedUrl");
    }
}
