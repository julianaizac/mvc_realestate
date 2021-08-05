package br.com.gft.realestate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.gft.realestate.model.Property;
import br.com.gft.realestate.repository.PropertyRepository;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    public String saveProperty(Property property, RedirectAttributes attributes) {
        try {
            propertyRepository.save(property);
            attributes.addFlashAttribute("message", "Imóvel salvo com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Erro ao salvar o Imóvel.");
        }
        return "redirect:/properties/newproperty";
    }

    public List<Property> findAllProperties() {
        List<Property> allProperties = propertyRepository.findAll();
        return allProperties;
    }

    public Property editProperty(Long id) {
        return propertyRepository.getOne(id);
    }

    public String deleteById(Long id, RedirectAttributes attributes){
        try {
            propertyRepository.deleteById(id);
            attributes.addFlashAttribute("message", "Imóvel excluído com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Erro ao excluir o Imóvel.");
        }
		return "redirect:/properties";
    }
    
}
