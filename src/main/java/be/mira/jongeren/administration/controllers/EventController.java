package be.mira.jongeren.administration.controllers;

import be.mira.jongeren.administration.util.EventTypeOptions;
import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("events/all");
        List<Event> events = eventRepository.findAll();

        mav.addObject("events", events);

        return mav;
    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public ModelAndView navigateToAddForm(Model model, @Autowired EventTypeOptions eventTypeOptions) {
        ModelAndView mav = new ModelAndView("events/new");
        mav.addObject("eventTypeOptions", eventTypeOptions.getOptions());
        return mav;
    }

    @RequestMapping(value="/new", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute Event event){
        System.out.println(event);
        eventRepository.save(event);
        ModelAndView mav = new ModelAndView("redirect:/events");
        return mav;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable("id") Long id) {
        Event event = eventRepository.findOne(id);
        ModelAndView mav = new ModelAndView("events/details", "event", event);
        return mav;
    }
}
