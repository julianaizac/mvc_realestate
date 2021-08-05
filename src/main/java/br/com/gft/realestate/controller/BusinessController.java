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

import br.com.gft.realestate.model.Business;
import br.com.gft.realestate.repository.filters.BusinessFilter;
import br.com.gft.realestate.service.BusinessService;

@Controller
@RequestMapping("/business")
public class BusinessController {
    
    @Autowired
    BusinessService businessService;

    private static final String REGISTER_VIEW = "PageBusinessRegistration";
    private static final String SEARCH_VIEW = "PageBusinessSearch";

    @GetMapping("/newbusiness")
    public ModelAndView newBusiness(){
        ModelAndView mv = new ModelAndView(REGISTER_VIEW);
        mv.addObject(new Business());
        return mv;
    }

    @PostMapping
    public String saveBusiness(@Validated Business business, Errors errors, RedirectAttributes attributes){
        if(errors.hasErrors()){
            return REGISTER_VIEW;
        }
        return businessService.saveBusiness(business, attributes);
    }

    @GetMapping
    public ModelAndView searchBusiness(@ModelAttribute("filter") BusinessFilter filter) {
        List<Business> allBusiness = businessService.filter(filter);
        
        ModelAndView mv = new ModelAndView(SEARCH_VIEW);
        mv.addObject("business", allBusiness);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView editCategory(@PathVariable Long id){
        Business business = businessService.editBusiness(id);
        ModelAndView mv = new ModelAndView(REGISTER_VIEW);
        mv.addObject(business);
        return mv;
    } 

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
		return businessService.deleteById(id, attributes);
    }

}
