package com.example.DateApplication;

import com.example.DateApplication.dto.DateIdea;
import com.example.DateApplication.dto.FindRequest;
import com.example.DateApplication.repositories.*;
import com.example.DateApplication.service.*;
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
    private final CountryService countryService;
    private final TypeService typeService;
    private final RandomService randomService;
    private final DateIdeaValidator dateIdeaValidator;
    private final DateIdeaService dateIdeaService;

    @Autowired
    public DateController(RandomService randomService,
                          DateIdeaValidator dateIdeaValidator,
                          DateIdeaService dateIdeaService,
                          CountryService countryService,
                          TypeService typeService) {
        this.randomService = randomService;
        this.dateIdeaValidator = dateIdeaValidator;
        this.dateIdeaService = dateIdeaService;
        this.countryService = countryService;
        this.typeService = typeService;
    }

    @GetMapping("")
    public String start() {
        return "index";
    }

    @GetMapping("/new")
    public String newIdea(@ModelAttribute("dateIdea") DateIdea dateIdea, Model model) {
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("countries", countryService.findAll());
        return "create/new_idea";
    }
    @PostMapping("/new")
    public String createIdea(@ModelAttribute("dateIdea") @Valid DateIdea dateIdea,
                             BindingResult bindingResult,
                             Model model ) {
        dateIdeaValidator.validate(dateIdea, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", typeService.findAll());
            model.addAttribute("countries", countryService.findAll());
            return "create/new_idea";
        } else {
            dateIdeaService.save(dateIdea);
            return "create/success";
        }
    }

    @GetMapping("/new/success")
    public String success() {
        return "create/success";
    }

    @GetMapping("/find")
    public String findDateIdea(@ModelAttribute("findRequest") FindRequest findRequest, Model model) {
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("countries", countryService.findAll());
        return "find/find_idea";
    }

    @GetMapping("/find/execute")
    public String findDateIdeas(@ModelAttribute("findRequest") @Valid FindRequest findRequest,
                                BindingResult bindingResult,
                                @RequestParam(name = "button") String name,
                                Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("types", typeService.findAll());
            model.addAttribute("countries", countryService.findAll());
            return "find/find_idea";
        }

        List<DateIdea> dateIdeas = dateIdeaService.getDateIdeas(findRequest);
        if (name.equals("Показать все")) {
            model.addAttribute("dateIdeas", dateIdeas);
            return "/find/show_all";
        } else if (name.equals("Выбрать случайно")) {
            DateIdea randomdateIdea = randomService.getRandomDateIdea(dateIdeas);
            model.addAttribute("dateIdea", randomdateIdea);
            return "/find/show_idea";
        }

        return "redirect:/dateIdea";
    }

    @GetMapping("/{id}")
    public String getDateIdeaById(@PathVariable("id") int id, Model model) throws NoEntityException {
        DateIdea dateIdea = dateIdeaService.findById(id);
        model.addAttribute("dateIdea", dateIdea);
        return "/find/show_idea";
    }

    @DeleteMapping("/{id}")
    public String deleteIdea(@PathVariable("id") int id) {
        dateIdeaService.deleteIdea(id);
        return "redirect:/dateIdea/find";
    }

    @ExceptionHandler(NoEntityException.class)
    public String handleNoEntityException(NoEntityException ex, Model model) {
        String errorMessage = "Пользователь с id=" + ex.getEntityId() + " не найден. Пожалуйста проверьте запрос.";
        model.addAttribute("errorMessage", errorMessage);
        return "/find/error";
    }
}
