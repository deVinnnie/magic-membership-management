package be.mira.jongeren.administration.repository;

import be.mira.jongeren.administration.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAllByPostcode(String postcode);

    City findByPostcode(String postcode);
}

