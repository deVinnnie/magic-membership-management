package be.mira.jongeren.administration.beans.person;

import be.mira.jongeren.administration.domain.City;
import be.mira.jongeren.administration.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.faces.bean.ManagedBean;
import java.util.List;

@Component
@ManagedBean
@ApplicationScope
public class CityBean {

    @Autowired
    private CityRepository cityRepository;

    public List<City> searchCity(String postcode){
        return cityRepository.findAllByPostcode(postcode);
    }

}
