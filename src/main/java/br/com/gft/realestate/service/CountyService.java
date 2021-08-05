package br.com.gft.realestate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.realestate.model.County;
import br.com.gft.realestate.repository.CountyRepository;
import br.com.gft.realestate.repository.filters.CountyFilter;

@Service
public class CountyService {
    
    @Autowired
    CountyRepository countyRepository;

    public String saveCounty(County state, RedirectAttributes attributes) {
        try {
            countyRepository.save(state);
            attributes.addFlashAttribute("message", "Município salvo com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Erro ao salvar o Município.");
        }
        return "redirect:/counties/newcounty";
    }

    public County editCounty(Long id) {
        return countyRepository.getOne(id);
    }

    public String deleteById(Long id, RedirectAttributes attributes){
        try {
            countyRepository.deleteById(id);
            attributes.addFlashAttribute("message", "Município excluído com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Erro ao excluir o Município.");
        }
		return "redirect:/counties";
    }

    public List<County> filter(CountyFilter filter){
        String name = filter.getName() == null ? "" : filter.getName();
        return countyRepository.findByNameContaining(name);
    }
}
