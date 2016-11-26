package be.mira.jongeren.administration.service;

import be.mira.jongeren.administration.domain.Activiteit;
import be.mira.jongeren.administration.domain.ActiviteitType;
import be.mira.jongeren.administration.repository.ActiviteitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private ActiviteitRepository repository;

    public void save(Activiteit event) {
        this.repository.save(event);
    }

    public List<Activiteit> findAll() {
        return repository.findAll();
    }
}
