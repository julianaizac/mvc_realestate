package br.com.gft.realestate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.realestate.model.Category;
import br.com.gft.realestate.repository.CategoryRepository;
import br.com.gft.realestate.repository.filters.CategoryFilter;

@Service
public class CategoryService {
    
    @Autowired
    CategoryRepository categoryRepository;

    public String saveCategory(Category category, RedirectAttributes attributes) {
        try {
            categoryRepository.save(category);
            attributes.addFlashAttribute("message", "Categoria salva com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Erro ao salvar o negócio.");          
        }
        return "redirect:/categories/newcategory";
    }

    public Category editCategory(Long id) {
        return categoryRepository.getOne(id);
    }

    public String deleteById(Long id, RedirectAttributes attributes){
        try {
            categoryRepository.deleteById(id);
            attributes.addFlashAttribute("message", "Categoria excluída com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Erro ao excluir a categoria.");
        }
		return "redirect:/categories";
    }

    public List<Category> filter(CategoryFilter filter){
        String name = filter.getName() == null ? "" : filter.getName();
        return categoryRepository.findByNameContaining(name);
    }
}
