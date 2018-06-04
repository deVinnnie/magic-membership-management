package be.mira.jongeren.administration.controllers;

import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(value="/overview/{year}", method = RequestMethod.GET)
    public ModelAndView overview(@PathVariable("year") int year){
        ModelAndView mav = new ModelAndView("statistics/overview");

        List<Event> eventsInYear = eventRepository.findAll()
                .stream()
                .filter(e -> e.getDatum().getYear() == year)
                .collect(Collectors.toList());

        LongSummaryStatistics summaryStatistics = eventsInYear.stream()
                .mapToLong(Event::getNumberOfParticipants)
                .summaryStatistics();

        mav.addObject("events", eventsInYear);
        mav.addObject("year", year);
        mav.addObject("summary", summaryStatistics);
        return mav;
    }
}
