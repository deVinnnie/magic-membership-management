package be.mira.jongeren.administration.service;

import be.mira.jongeren.administration.domain.Event;
import be.mira.jongeren.administration.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public void save(Event event) {
        this.repository.save(event);
    }

    public List<Event> findAll() {
        return repository.findAll();
    }
}
