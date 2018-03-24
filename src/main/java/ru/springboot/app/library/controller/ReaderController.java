package ru.springboot.app.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.springboot.app.library.model.Reader;
import ru.springboot.app.library.repository.ReaderRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReaderController {

    @Autowired
    ReaderRepository readerData;

    /*@RequestMapping(value = "/")
    public String index(Model model) {

        return "index";
    }*/

    @RequestMapping(value = "/showReaders")
    public ModelAndView showReaders() {

        List<Reader> readers = readerData.findAll();

        Map<String, Object> params = new HashMap<>();
        params.put("readers", readers);

        return new ModelAndView("showReaders", params);
    }

    @RequestMapping(value = "/deleteReader/{id}", method = RequestMethod.GET)
    public String deleteReader(@PathVariable(required = true, name = "id") Long id) {

        readerData.deleteById(id);

        return "showReaders";
    }


    //TODO addReader
    @RequestMapping(value = "/addReader", method = RequestMethod.POST)
    public String addReader(@ModelAttribute("reader") Reader reader) {

        readerData.save(reader);

        return "/";
    }

}
