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

import br.com.gft.realestate.model.Neighborhood;
import br.com.gft.realestate.model.State;
import br.com.gft.realestate.repository.CountyRepository;
import br.com.gft.realestate.repository.StateRepository;
import br.com.gft.realestate.repository.filters.NeighborhoodFilter;
import br.com.gft.realestate.service.NeighborhoodService;

@Controller
@RequestMapping("/neighborhoods")
public class NeighborhoodController {
    
    @Autowired
    NeighborhoodService neighborhoodService;

    @Autowired
    CountyRepository countyRepository;

    @Autowired
    StateRepository stateRepository; 

    private static final String REGISTER_VIEW = "PageNeighborhoodRegistration";

    @GetMapping("/newneighborhood")
    public ModelAndView newNeighborhood(){
        List<State> allStates = stateRepository.findAll();
        ModelAndView mv = new ModelAndView(REGISTER_VIEW);
        
        mv.addObject(new Neighborhood());
        mv.addObject("allStates", allStates);
        return mv;
    }

    @PostMapping
    public String saveNeighborhood(@Validated Neighborhood neighborhood, Errors errors, RedirectAttributes attributes){
        if(errors.hasErrors()){
            return REGISTER_VIEW;
        }
        return neighborhoodService.saveNeighborhood(neighborhood, attributes);
    }

    @GetMapping
    public ModelAndView searchNeighborhood(@ModelAttribute("filter") NeighborhoodFilter filter) {
        List<Neighborhood> allNeighborhoods = neighborhoodService.filter(filter);
        ModelAndView mv = new ModelAndView("PageNeighborhoodsSearch");
        mv.addObject("neighborhoods", allNeighborhoods);
        return mv;
    }
    @GetMapping("/{id}")
    public ModelAndView editNeighborhood(@PathVariable Long id){
        List<State> allStates = stateRepository.findAll();

        Neighborhood neighborhood = neighborhoodService.editNeighborhood(id);
        ModelAndView mv = new ModelAndView(REGISTER_VIEW);

        mv.addObject(neighborhood);
        mv.addObject("allStates", allStates);

        return mv;
    } 

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        return neighborhoodService.deleteById(id, attributes);
    }

    @GetMapping("/listNeighborhoodByCounty")
    @ResponseBody
    public List<Neighborhood> listNeighborhoodByCounty(Long idCounty){
        List<Neighborhood> listNeighborhoods = countyRepository.findById(idCounty).get().getNeighborhoods();
        return listNeighborhoods;
    }

}
