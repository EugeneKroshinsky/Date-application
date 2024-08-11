package com.example.DateApplication;

import com.example.DateApplication.dto.DateIdea;
import com.example.DateApplication.dto.FindRequest;
import com.example.DateApplication.dto.entities.CityEntity;
import com.example.DateApplication.dto.entities.CountryEntity;
import com.example.DateApplication.dto.entities.DateIdeaEntity;
import com.example.DateApplication.repositories.*;
import com.example.DateApplication.service.DateIdeaService;
import com.example.DateApplication.service.RandomService;
import com.example.DateApplication.utils.DateIdeaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dateIdea")
public class DateController {
    @Autowired
    private RandomService randomService;
    @Autowired
    private DateIdeaRepository dateIdeaRepository;
    @Autowired
    private DateIdeaValidator dateIdeaValidator;
    @Autowired
    private DateIdeaService dateIdeaService;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("")
    public String start() {
        return "index";
    }

    @GetMapping("/new")
    public String newIdea(DateIdea dateIdea, Model model) {
        model.addAttribute("dateIdea", new DateIdea());
        model.addAttribute("types", typeRepository.findAll());
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("regions", regionRepository.findAll());
        model.addAttribute("cities",cityRepository.findAll());
        return "create/new_idea";
    }
    @PostMapping("/new")
    public String createIdea(@ModelAttribute("dateIdea") @Valid DateIdea dateIdea,
                             BindingResult bindingResult,
                             Model model) {
        dateIdeaValidator.validate(dateIdea, bindingResult);
        System.out.println(dateIdea);
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", typeRepository.findAll());
            model.addAttribute("countries", countryRepository.findAll());
            model.addAttribute("regions", regionRepository.findAll());
            model.addAttribute("cities",cityRepository.findAll());
            return "create/new_idea";
        } else {
            //добавлление в бд dateIdea
            return "create/success";
        }
    }

    @GetMapping("/new/success")
    public String success() {
        return "create/success";
    }

    @GetMapping("/find")
    public String findDateIdea(@ModelAttribute("findRequest") FindRequest findRequest, Model model) {
        model.addAttribute("types", typeRepository.findAll());
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("regions", regionRepository.findAll());
        model.addAttribute("cities",cityRepository.findAll());
        return "find/find_idea";
    }

    @GetMapping("/find/execute")
    public String findDateIdeas(@ModelAttribute("findRequest") @Valid FindRequest findRequest,
                                BindingResult bindingResult,
                                @RequestParam(name = "button") String name,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", typeRepository.findAll());
            model.addAttribute("countries", countryRepository.findAll());
            model.addAttribute("regions", regionRepository.findAll());
            model.addAttribute("cities",cityRepository.findAll());
            return "find/find_idea";
        } else {
            List<DateIdea> dateIdeas = dateIdeaService.getDateIdeas(findRequest);
            if (name.equals("Показать все")) {
                model.addAttribute("dateIdeas", dateIdeas);
                return "find/show_all";
            } else if (name.equals("Выбрать случайно")) {
                DateIdea randomdateIdea = randomService.getRandomDateIdea(dateIdeas);
                model.addAttribute("dateIdea", randomdateIdea);
                return "/find/show_idea";
            }
        }
        return "redirect:/dateIdea";
    }

    @GetMapping("/{id}")
    public String getDateIdeaById(@PathVariable("id") int id, Model model) {
        DateIdea dateIdea = dateIdeaRepository.findById(id).stream().
                map(DateIdea::new)
                .findAny()
                .orElse(null);
        model.addAttribute("dateIdea", dateIdea);
        return "find/show_idea";
    }
}
