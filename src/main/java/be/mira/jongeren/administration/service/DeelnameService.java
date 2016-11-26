package be.mira.jongeren.administration.service;

import be.mira.jongeren.administration.domain.Deelname;
import be.mira.jongeren.administration.repository.DeelnameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeelnameService {

    @Autowired
    private DeelnameRepository repository;

    public void save(Deelname deelname) {
        Deelname savedDeelname = repository.save(deelname);
    }
}
