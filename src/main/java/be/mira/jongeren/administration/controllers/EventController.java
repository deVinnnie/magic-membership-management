package be.mira.jongeren.administration.controllers;

import be.mira.jongeren.administration.domain.EventType;
import be.mira.jongeren.administration.util.EventTypeOptions;
import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(name = "year", defaultValue = "2015") int year) {
        ModelAndView mav = new ModelAndView("events/all");
        List<Event> events = eventRepository.findAll();

        List<Integer> availableYears = events.stream()
                .map(e -> e.getDatum().getYear())
                .distinct()
                .collect(Collectors.toList());

        Map<EventType, List<Event>> categories = new HashMap<>();
        Arrays.stream(EventType.values()).forEach(
            type -> categories.put(
                    type,
                    events
                        .stream()
                        .filter(e -> e.getEventType().equals(type))
                        .filter(e -> e.getDatum().getYear() == year)
                        .collect(Collectors.toList())
                    )
        );

        mav.addObject("events", events);
        mav.addObject("categories", categories);
        mav.addObject("currentYear", year);
        mav.addObject("availableYears", availableYears);

        return mav;
    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public ModelAndView navigateToAddForm(Model model, @Autowired EventTypeOptions eventTypeOptions) {
        ModelAndView mav = new ModelAndView("events/new");
        mav.addObject("eventTypeOptions", eventTypeOptions.getOptions());
        return mav;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute Event event){
        eventRepository.save(event);
        ModelAndView mav = new ModelAndView("redirect:/events");
        return mav;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable("id") Long id) {
        Event event = eventRepository.findOne(id);
        if(event == null){
            throw new ResourceNotFound();
        }
        ModelAndView mav = new ModelAndView("events/details", "event", event);
        return mav;
    }
}
