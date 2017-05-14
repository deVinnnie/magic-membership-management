package be.mira.jongeren.administration.controllers;

import be.mira.jongeren.administration.domain.Membership;
import be.mira.jongeren.administration.domain.Person;
import be.mira.jongeren.administration.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/memberships")
public class MembershipController {

    @Autowired
    private MembershipRepository membershipRepository;

    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView getAllMemberships(){
        ModelAndView mav = new ModelAndView("memberships/all");
        mav.addObject("memberships", membershipRepository.findAll());
        return mav;
    }

    @RequestMapping(value="{id}", method = RequestMethod.GET)
    public ModelAndView getMembership(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("memberships/details");
        Membership membership = membershipRepository.findOne(id);
        mav.addObject("membership", membership);
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView createMembership(Membership membership, @RequestParam("person.id") Person person){
        membership.setPerson(person);
        ModelAndView mav = new ModelAndView("redirect:memberships/");
        membershipRepository.save(membership);
        return mav;
    }
}
