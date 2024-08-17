package com.example.DateApplication;

import com.example.DateApplication.dto.DateIdea;
import com.example.DateApplication.dto.FindRequest;
import com.example.DateApplication.dto.exceptions.NoEntityException;
import com.example.DateApplication.service.*;
import com.example.DateApplication.utils.DateIdeaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/dateIdea")
public class DateController {
    private final FindIdeaService findIdeaService;
    private final FileStorageService fileStorageService;
    private final CountryService countryService;
    private final TypeService typeService;
    private final RandomService randomService;
    private final DateIdeaValidator dateIdeaValidator;
    private final DateIdeaService dateIdeaService;

    @Autowired
    public DateController(FindIdeaService findIdeaService,
                          FileStorageService fileStorageService,
                          RandomService randomService,
                          DateIdeaValidator dateIdeaValidator,
                          DateIdeaService dateIdeaService,
                          CountryService countryService,
                          TypeService typeService) {
        this.findIdeaService = findIdeaService;
        this.fileStorageService = fileStorageService;
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
                             Model model,
                             @RequestParam("file") MultipartFile file) throws Exception {
        dateIdeaValidator.validate(dateIdea, bindingResult);

        String fileName = fileStorageService.storeFile(file);
        dateIdea.setImageUrl(fileName);

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
                                Model model) throws Exception {

        System.out.println(findRequest);

        if (bindingResult.hasErrors()) {
            model.addAttribute("types", typeService.findAll());
            model.addAttribute("countries", countryService.findAll());
            return "find/find_idea";
        }

        List<DateIdea> dateIdeas = findIdeaService.getDateIdeas(findRequest);
        if (name.equals("Показать все")) {
            model.addAttribute("dateIdeas", dateIdeas);
            return "/find/show_all";
        } else if (name.equals("Выбрать случайно")) {
            model.addAttribute("dateIdea", randomService.getRandomDateIdea(dateIdeas));
            return "/find/show_idea";
        } else {
            return "redirect:/dateIdea";
        }

    }

    @GetMapping("/{id}")
    public String getDateIdeaById(@PathVariable("id") int id, Model model) throws NoEntityException {
        model.addAttribute("dateIdea",  dateIdeaService.findById(id));
        return "/find/show_idea";
    }

    @DeleteMapping("/{id}")
    public String deleteIdea(@PathVariable("id") int id) throws NoEntityException {
        dateIdeaService.deleteIdea(id);
        return "redirect:/dateIdea/find";
    }

    @ExceptionHandler(NoEntityException.class)
    public ModelAndView handleNoEntityException(NoEntityException ex) {
        String errorMessage = "Пользователь с id=" + ex.getEntityId() + " не найден. Пожалуйста проверьте запрос.";
        ModelAndView model = new ModelAndView();
        model.addObject("errorMessage", errorMessage);
        model.setViewName("/find/error");
        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView nullPointerException(Exception e) {
        ModelAndView model = new ModelAndView();
        model.addObject("errorMessage", e.getMessage());
        model.setViewName("/error");
        return model;
    }

    @ExceptionHandler(IOException.class)
    public ModelAndView ioException(IOException e) {
        ModelAndView model = new ModelAndView();
        model.addObject("errorMessage", e.getMessage());
        model.setViewName("/error");
        return model;
    }
}
