package be.mira.jongeren.administration.controllers;

import be.mira.jongeren.administration.util.GenderOptions;
import be.mira.jongeren.administration.domain.City;
import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.repository.CityRepository;
import be.mira.jongeren.administration.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CityRepository cityRepository;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("layout:persons/all");
        List<Person> persons = personRepository.findAll();

        mav.addObject("persons", persons);

        return mav;
    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public ModelAndView navigateToAddForm(Model model, @Autowired GenderOptions genderOptions) {
        ModelAndView mav = new ModelAndView("layout:persons/new");
        mav.addObject("genderOptions", genderOptions.getOptions());
        //mav.addObject("cities", cityRepository.findAll());
        return mav;
    }

    @RequestMapping(value="/new", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute Person person,  @RequestParam("postcode")  String postcode){
        City city = cityRepository.findByPostcode(postcode);

        person.setCity(city);

        personRepository.save(person);

        ModelAndView mav = new ModelAndView("redirect:/persons");
        return mav;
    }

    @RequestMapping(value="/details/{id}", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable("id") Long id) {
        Person person = personRepository.findOne(id);
        ModelAndView mav = new ModelAndView("layout:persons/details", "person", person);
        return mav;
    }
}
