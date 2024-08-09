package com.example.DateApplication;

import com.example.DateApplication.dto.DateIdea;
import com.example.DateApplication.dto.FindRequest;
import com.example.DateApplication.dto.entities.CityEntity;
import com.example.DateApplication.dto.entities.CountryEntity;
import com.example.DateApplication.repositories.CityRepository;
import com.example.DateApplication.repositories.CountryRepository;
import com.example.DateApplication.repositories.RegionRepository;
import com.example.DateApplication.repositories.TypeRepository;
import com.example.DateApplication.utils.DateIdeaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.model.IModel;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DateController {
    @Autowired
    private DateIdeaValidator dateIdeaValidator;

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/dateIdea")
    public String start() {
        return "index";
    }

    @GetMapping("/dateIdea/new")
    public String newIdea(DateIdea dateIdea, Model model) {
        model.addAttribute("dateIdea", new DateIdea());
        model.addAttribute("types", typeRepository.findAll());
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("regions", regionRepository.findAll());
        model.addAttribute("cities",cityRepository.findAll());
        return "create/new_idea";
    }
    @PostMapping("/dateIdea/new")
    public String createIdea(@ModelAttribute("dateIdea") @Valid DateIdea dateIdea,
                             BindingResult bindingResult,
                             Model model) {
        dateIdeaValidator.validate(dateIdea, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", typeRepository.findAll());
            model.addAttribute("countries", countryRepository.findAll());
            model.addAttribute("regions", regionRepository.findAll());
            model.addAttribute("cities",cityRepository.findAll());
            return "create/new_idea";
        } else {
            return "create/success";
        }
    }

    @GetMapping("/dateIdea/new/success")
    public String success() {
        return "create/success";
    }

    @GetMapping("dateIdea/find")
    public String findDateIdea(@ModelAttribute("findRequest") FindRequest findRequest, Model model) {
        model.addAttribute("types", typeRepository.findAll());
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("regions", regionRepository.findAll());
        model.addAttribute("cities",cityRepository.findAll());
        return "find/find_idea";
    }

    @GetMapping("/dateIdea/find/execute")
    public String findDateIdeas(@ModelAttribute("findRequest") @Valid FindRequest findRequest,
                                BindingResult bindingResult,
                                @RequestParam(name = "button") String name,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "find/find_idea";
        } else {
            if (name.equals("Показать все")) {
                //получаем из базы данных всех соответствующих фильтрам в List,
                // а потом model.addAttribute(List)
                //временно
                return "find/show_all";
            } else if (name.equals("Выбрать случайно")) {
                //рандомно достать 1 элемент
            }
        }


        return "redirect:/dateIdea"; // имя вашего шаблона
    }
}
