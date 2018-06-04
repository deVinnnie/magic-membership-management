package be.mira.jongeren.administration.controllers;

import be.mira.jongeren.administration.util.PartakingTypeOptions;
import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.domain.Partaking;
import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.repository.EventRepository;
import be.mira.jongeren.administration.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/events/{eventId}/partakings")
public class PartakingsController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView viewPartakings(@PathVariable("eventId") Long eventId){
        Event event = eventRepository.findOne(eventId);

        ModelAndView mav = new ModelAndView("partakings/all");
        mav.addObject("partakings", event.getPartakings());

        return mav;
    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public ModelAndView navigateToAddForm(Model model, @PathVariable("eventId") Long eventId, @Autowired PartakingTypeOptions partakingTypeOptions) {
        ModelAndView mav = new ModelAndView("partakings/add");
        mav.addObject("allPartakingTypes", partakingTypeOptions.getOptions());
        mav.addObject("persons", personRepository.findAll());
        mav.addObject("event", eventRepository.findOne(eventId));
        mav.addObject("searchTerm", "");

        return mav;
    }

    @RequestMapping(value="/new", method = RequestMethod.GET, params="search")
    public ModelAndView searchPersons(Model model, @PathVariable("eventId") Long eventId, @Autowired PartakingTypeOptions partakingTypeOptions, @RequestParam("search") String searchTerm) {
        ModelAndView mav = this.navigateToAddForm(model, eventId, partakingTypeOptions);

        List persons = personRepository.searchByName(searchTerm);
        mav.addObject("persons", persons);
        mav.addObject("searchTerm", searchTerm);

        return mav;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ModelAndView add(@PathVariable("eventId") Long eventId,
                            @ModelAttribute Partaking partaking,
                            @RequestParam("person") Person person)
    {
        Event event = eventRepository.findOne(eventId);

        partaking.setPerson(person);
        partaking.setEvent(event);

        event.addPartaking(partaking);

        eventRepository.flush();

        ModelAndView mav = new ModelAndView("redirect:/events/" + event.getId());
        return mav;
    }
}
