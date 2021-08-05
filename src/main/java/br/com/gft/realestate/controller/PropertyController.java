package br.com.gft.realestate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.realestate.model.Business;
import br.com.gft.realestate.model.Category;
import br.com.gft.realestate.model.Property;
import br.com.gft.realestate.model.Room;
import br.com.gft.realestate.model.State;
import br.com.gft.realestate.repository.BusinessRepository;
import br.com.gft.realestate.repository.CategoryRepository;
import br.com.gft.realestate.repository.RoomRepository;
import br.com.gft.realestate.repository.StateRepository;
import br.com.gft.realestate.service.PropertyService;


@Controller
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    StateRepository stateRepository;

    private static final String REGISTER_VIEW = "PagePropertyRegistration";

    @GetMapping("/newproperty")
    public ModelAndView newCounty(){
        List<Business> allBusiness = businessRepository.findAll();
        List<Category> allCategories = categoryRepository.findAll();
        List<Room> allRooms = roomRepository.findAll();
        List<State> allStates = stateRepository.findAll();

        ModelAndView mv = new ModelAndView(REGISTER_VIEW);
        mv.addObject(new Property());
        mv.addObject("allBusiness", allBusiness);
        mv.addObject("allCategories", allCategories);
        mv.addObject("allRooms", allRooms);
        mv.addObject("allStates", allStates);
        
        return mv;
    }

    @GetMapping
    public ModelAndView searchProperty() {
        List<Property> allProperties = propertyService.findAllProperties();
        ModelAndView mv = new ModelAndView("PagePropertiesSearch");
        mv.addObject("properties", allProperties);
        return mv;
    }

    @PostMapping
    public String saveState(@Validated Property property, Errors errors, RedirectAttributes attributes){
        if(errors.hasErrors()){
            return REGISTER_VIEW;
        }
        return propertyService.saveProperty(property, attributes); 
    }

    @GetMapping("/{id}")
    public ModelAndView editProperty(@PathVariable Long id){
        Property property = propertyService.editProperty(id);
        ModelAndView mv = new ModelAndView(REGISTER_VIEW);
        mv.addObject(property);

        List<Business> allBusiness = businessRepository.findAll();
        List<Category> allCategories = categoryRepository.findAll();
        List<Room> allRooms = roomRepository.findAll();
        List<State> allStates = stateRepository.findAll();

        mv.addObject("allBusiness", allBusiness);
        mv.addObject("allCategories", allCategories);
        mv.addObject("allRooms", allRooms);
        mv.addObject("allStates", allStates);
        
        return mv;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        return propertyService.deleteById(id, attributes);
    }

}
