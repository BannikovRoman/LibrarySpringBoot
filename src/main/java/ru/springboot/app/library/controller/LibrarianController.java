package ru.springboot.app.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.springboot.app.library.model.Librarian;
import ru.springboot.app.library.repository.LibrarianRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LibrarianController {

    @Autowired
    LibrarianRepository librarianData;

    @RequestMapping(value = "/showLibrarians")
    public ModelAndView showLibrarians() {
        List<Librarian> librarians = librarianData.findAll();

        Map<String, Object> params = new HashMap<>();
        params.put("librarians", librarians);

        return new ModelAndView("showLibrarians", params);
    }

    @RequestMapping(value = "/deleteLibrarian/{id}", method = RequestMethod.GET)
    public String deleteLibrarian(@PathVariable(required = true, name = "id") Long id) {
        librarianData.deleteById(id);
        return "redirect:/index";
    }

    @RequestMapping(value = "/addLibrarian", method = RequestMethod.GET)
    public String addLibrarian(Model model) {
        model.addAttribute("librarian", new Librarian());
        return "addLibrarian";
    }

    @RequestMapping(value = "/addLibrarian", method = RequestMethod.POST)
    public String addLibrarianSubmit(@ModelAttribute Librarian librarian, Model model) {
        librarianData.save(librarian);
        model.addAttribute("librarian", librarian);
        return "index";
    }
}
