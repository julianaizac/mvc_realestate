package br.com.gft.realestate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.realestate.model.County;
import br.com.gft.realestate.model.State;
import br.com.gft.realestate.repository.CountyRepository;
import br.com.gft.realestate.repository.StateRepository;
import br.com.gft.realestate.repository.filters.CountyFilter;
import br.com.gft.realestate.service.CountyService;

@Controller
@RequestMapping("/counties")
public class CountyController {

    @Autowired
    CountyService countyService;

    @Autowired
    CountyRepository countyRepository;

    @Autowired
    StateRepository stateRepository;
    
    private static final String REGISTER_VIEW = "PageCountyRegistration";

    @GetMapping("/newcounty")
    public ModelAndView newCounty(){
        List<State> allStates = stateRepository.findAll();
        ModelAndView mv = new ModelAndView(REGISTER_VIEW);
        mv.addObject(new County());
        mv.addObject("allStates", allStates);
        return mv;
    }

    @PostMapping
    public String saveCounty(@Validated County county, Errors errors, RedirectAttributes attributes){
        if(errors.hasErrors()){
            return REGISTER_VIEW;
        }
        return countyService.saveCounty(county, attributes);
    }

    @GetMapping
    public ModelAndView searchCounty(@ModelAttribute("filter") CountyFilter filter) {
        List<County> allCounties = countyService.filter(filter);
        ModelAndView mv = new ModelAndView("PageCountiesSearch");
        mv.addObject("counties", allCounties);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView editCounty(@PathVariable Long id){
        List<State> allStates = stateRepository.findAll();
        County county = countyService.editCounty(id);
        ModelAndView mv = new ModelAndView(REGISTER_VIEW);
        mv.addObject(county);
        mv.addObject("allStates", allStates);
        return mv;
    } 

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        return countyService.deleteById(id, attributes);
    }

    @GetMapping("/listCountiesByState")
    @ResponseBody
    public List<County> listCountiesByState(Long idState){
        List<County> listCounties = stateRepository.findById(idState).get().getCounties();
        System.out.println(listCounties);
        return listCounties;
    }

}