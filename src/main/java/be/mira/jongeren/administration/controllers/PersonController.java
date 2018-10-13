package be.mira.jongeren.administration.controllers;

import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.domain.Partaking;
import be.mira.jongeren.administration.repository.PartakingRepository;
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
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PartakingRepository partakingsRepository;

    @Autowired
    private CityRepository cityRepository;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("persons/all");
        List<Person> persons = personRepository.findAll();

        mav.addObject("persons", persons);

        return mav;
    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public ModelAndView navigateToAddForm(Model model, @Autowired GenderOptions genderOptions) {
        ModelAndView mav = new ModelAndView("persons/new");
        mav.addObject("genderOptions", genderOptions.getOptions());
        //mav.addObject("cities", cityRepository.findAll());
        return mav;
    }

    @RequestMapping(value="{id}/edit", method = RequestMethod.GET)
    public ModelAndView navigateToEditForm(Model model, @PathVariable("id") UUID id, @Autowired GenderOptions genderOptions) {
        ModelAndView mav = new ModelAndView("persons/edit");
        mav.addObject("genderOptions", genderOptions.getOptions());

        Person person = personRepository.findOne(id);
        mav.addObject("person", person);
        return mav;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ModelAndView add(
        @ModelAttribute Person person,
        @RequestParam("postcode")  String postcode
    ){
        City city = cityRepository.findByPostcode(postcode);

        person.setCity(city);

        if(person.getId()!=null) {
            Long version = personRepository.findOne(person.getId()).getVersion();
            person.setVersion(version);
        }

        personRepository.save(person);
        ModelAndView mav = new ModelAndView("redirect:/persons");
        return mav;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable("id") UUID id) {
        Person person = personRepository.findOne(id);

        List<Event> events = partakingsRepository.findForPerson(id)
                .stream()
                .map(Partaking::getEvent)
                .collect(Collectors.toList());

        ModelAndView mav = new ModelAndView("persons/details", "person", person);
        mav.addObject("events", events);
        return mav;
    }
}
