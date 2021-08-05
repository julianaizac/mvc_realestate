package br.com.gft.realestate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.realestate.model.Neighborhood;
import br.com.gft.realestate.repository.NeighborhoodRepository;
import br.com.gft.realestate.repository.filters.NeighborhoodFilter;

@Service
public class NeighborhoodService {
    
    @Autowired
    NeighborhoodRepository neighborhoodRepository;

    public String saveNeighborhood(Neighborhood neighborhood, RedirectAttributes attributes) {
        try {
            neighborhoodRepository.save(neighborhood);
            attributes.addFlashAttribute("message", "Bairro salvo com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Erro ao salvar o Bairro.");
        }

        return "redirect:/neighborhoods/newneighborhood";
    }

    public Neighborhood editNeighborhood(Long id) {
        return neighborhoodRepository.getOne(id);
    }

    public String deleteById(Long id, RedirectAttributes attributes){
        try {
            neighborhoodRepository.deleteById(id);
            attributes.addFlashAttribute("message", "Bairro excluído com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "erro ao excluir o Bairro.");
        }
		return "redirect:/neighborhoods";
    }

    public List<Neighborhood> filter(NeighborhoodFilter filter){
        String name = filter.getName() == null ? "" : filter.getName();
        return neighborhoodRepository.findByNameContaining(name);
    }
}
