package br.com.gft.realestate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.realestate.model.Business;
import br.com.gft.realestate.repository.BusinessRepository;
import br.com.gft.realestate.repository.filters.BusinessFilter;

@Service
public class BusinessService {
    
    @Autowired
    BusinessRepository businessRepository;

    public String saveBusiness(Business business, RedirectAttributes attributes) {
        try {
            businessRepository.save(business);
            attributes.addFlashAttribute("message", "Negócio salvo com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Erro ao salvar o negócio!");
        }
        return "redirect:/business/newbusiness";
    }

    public Business editBusiness(Long id) {
        return businessRepository.getOne(id);
    }

    public String deleteById(Long id, RedirectAttributes attributes){
        try {
            businessRepository.deleteById(id);
            attributes.addFlashAttribute("message", "Negócio excluído com sucesso!");
            return "redirect:/business";
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Erro ao excluir o negócio!");
            return "redirect:/business";
        }
    }

    public List<Business> filter(BusinessFilter filter){
        String name = filter.getName() == null ? "" : filter.getName();
        return businessRepository.findByNameContaining(name);
    }

}
