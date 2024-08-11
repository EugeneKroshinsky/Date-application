package com.example.DateApplication;

import com.example.DateApplication.dto.DateIdea;
import com.example.DateApplication.dto.FindRequest;
import com.example.DateApplication.repositories.*;
import com.example.DateApplication.service.DateIdeaService;
import com.example.DateApplication.service.RandomService;
import com.example.DateApplication.utils.DateIdeaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/dateIdea")
public class DateController {
    private final CountryRepository countryRepository;
    private final TypeRepository typeRepository;
    private final RandomService randomService;
    private final DateIdeaRepository dateIdeaRepository;
    private final DateIdeaValidator dateIdeaValidator;
    private final DateIdeaService dateIdeaService;

    @Autowired
    public DateController(RandomService randomService,
                          DateIdeaRepository dateIdeaRepository,
                          DateIdeaValidator dateIdeaValidator,
                          DateIdeaService dateIdeaService,
                          CountryRepository countryRepository,
                          TypeRepository typeRepository) {
        this.randomService = randomService;
        this.dateIdeaRepository = dateIdeaRepository;
        this.dateIdeaValidator = dateIdeaValidator;
        this.dateIdeaService = dateIdeaService;
        this.countryRepository = countryRepository;
        this.typeRepository = typeRepository;
    }

    @GetMapping("")
    public String start() {
        return "index";
    }

    @GetMapping("/new")
    public String newIdea(@ModelAttribute("dateIdea") DateIdea dateIdea, Model model) {
        model.addAttribute("types", typeRepository.findAll());
        model.addAttribute("countries", countryRepository.findAll());
        return "create/new_idea";
    }
    @PostMapping("/new")
    public String createIdea(@ModelAttribute("dateIdea") @Valid DateIdea dateIdea,
                             BindingResult bindingResult,
                             Model model ) {
        dateIdeaValidator.validate(dateIdea, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", typeRepository.findAll());
            model.addAttribute("countries", countryRepository.findAll());
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
        DateIdea dateIdea = dateIdeaRepository.findById(id)
                .map(DateIdea::new)
                .orElse(null);
        model.addAttribute("dateIdea", dateIdea);
        return "find/show_idea";
    }
}
