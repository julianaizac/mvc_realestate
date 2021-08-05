package br.com.gft.realestate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.realestate.model.State;
import br.com.gft.realestate.repository.StateRepository;
import br.com.gft.realestate.repository.filters.StateFilter;

@Service
public class StateService {
    
    @Autowired
    StateRepository stateRepository;

    public String saveState(State state, RedirectAttributes attributes) {
        try {
            stateRepository.save(state);
            attributes.addFlashAttribute("message", "Estado salvo com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Erro ao salvar o Estado.");
        }
        return "redirect:/states/newstate";
    }

    public State editState(Long id) {
        return stateRepository.getOne(id);
    }

    public String deleteById(Long id, RedirectAttributes attributes){
        try {
            stateRepository.deleteById(id);
            attributes.addFlashAttribute("message", "Estado excluído com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Erro ao excluir o Estado.");
        }
		return "redirect:/states";
    }

    public List<State> filter(StateFilter filter){
        String name = filter.getName() == null ? "" : filter.getName();
        return stateRepository.findByNameContaining(name);
    }

}
