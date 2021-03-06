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

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

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
        return "redirect:/index";
    }

    @RequestMapping(value = "/addReader", method = RequestMethod.GET)
    public String addReader(Model model) {
        model.addAttribute("reader", new Reader());
        return "addReader";
    }

    @RequestMapping(value = "/addReader", method = RequestMethod.POST)
    public String addReaderSubmit(@ModelAttribute Reader reader, Model model) {
        readerData.save(reader);
        model.addAttribute("reader", reader);
        return "index";
    }
}
