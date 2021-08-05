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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.realestate.model.State;
import br.com.gft.realestate.repository.filters.StateFilter;
import br.com.gft.realestate.service.StateService;

@Controller
@RequestMapping("/states")
public class StateController {
    
    @Autowired
    StateService stateService;

    private static final String REGISTER_VIEW = "PageStateRegistration";

    @GetMapping("/newstate")
    public ModelAndView newBusiness(){
        ModelAndView mv = new ModelAndView(REGISTER_VIEW);
        mv.addObject(new State());
        return mv;
    }

    @PostMapping
    public String saveState(@Validated State state, Errors errors, RedirectAttributes attributes){
        if(errors.hasErrors()){
            return REGISTER_VIEW;
        }
        return stateService.saveState(state, attributes);
    }

    @GetMapping
    public ModelAndView searchState(@ModelAttribute("filter") StateFilter filter) {
        List<State> allStates = stateService.filter(filter);
        ModelAndView mv = new ModelAndView("PageStatesSearch");
        mv.addObject("states", allStates);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView editState(@PathVariable Long id){
        State state = stateService.editState(id);
        ModelAndView mv = new ModelAndView(REGISTER_VIEW);
        mv.addObject(state);
        return mv;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        return stateService.deleteById(id, attributes);
    }

}
