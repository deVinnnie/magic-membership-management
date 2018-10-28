package be.mira.jongeren.administration.controllers;

import be.mira.jongeren.administration.domain.*;
import be.mira.jongeren.administration.repository.EventRepository;
import be.mira.jongeren.administration.statistics.GeographicDistribution;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(value="/overview/{year}", method = RequestMethod.GET)
    public ModelAndView overview(@PathVariable("year") int year){
        ModelAndView mav = new ModelAndView("statistics/overview");

        List<Event> eventsInYear = eventRepository.findByYear(year, null);

        LongSummaryStatistics summaryStatistics = eventsInYear.stream()
                .mapToLong(Event::getNumberOfParticipants)
                .summaryStatistics();

        mav.addObject("events", eventsInYear);
        mav.addObject("year", year);
        mav.addObject("summary", summaryStatistics);
        return mav;
    }

    @RequestMapping(value="/geographic-distribution/{year}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public byte[] geographicDistribution(
            @PathVariable("year") int year,
            @RequestParam(required = false) EventType type
    ) throws JsonProcessingException {
        List<Event> eventsInYear = eventRepository.findByYear(year, type);

        Map<String, Long> occurencePerPostCode = eventsInYear
                .stream()
                .flatMap(e -> e.getParticipants().stream())
                .map(Partaking::getPerson)
                .map(Person::getCity)
                .collect(groupingBy(City::getPostcode, counting()));

        List<GeographicDistribution> geographicDistribution = occurencePerPostCode
                .entrySet()
                .stream()
                .map(entry -> new GeographicDistribution(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(GeographicDistribution.class).withHeader();

        return mapper.writer(schema).writeValueAsBytes(geographicDistribution);
    }
}
