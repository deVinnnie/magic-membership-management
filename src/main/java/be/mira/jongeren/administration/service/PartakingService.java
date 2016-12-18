package be.mira.jongeren.administration.service;

import be.mira.jongeren.administration.domain.Partaking;
import be.mira.jongeren.administration.repository.PartakingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartakingService {

    @Autowired
    private PartakingRepository repository;

    public void save(Partaking partaking) {
        Partaking savedPartaking = repository.save(partaking);
    }
}
