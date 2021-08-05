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

import br.com.gft.realestate.model.Category;
import br.com.gft.realestate.repository.filters.CategoryFilter;
import br.com.gft.realestate.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    private static final String REGISTER_VIEW = "PageCategoryRegistration";

    @GetMapping("/newcategory")
    public ModelAndView newCategory(){
        ModelAndView mv = new ModelAndView(REGISTER_VIEW);
        mv.addObject(new Category());
        return mv;
    }

    @PostMapping
    public String saveCategory(@Validated Category category, Errors errors, RedirectAttributes attributes){
        if(errors.hasErrors()){
            return REGISTER_VIEW;
        }
        return categoryService.saveCategory(category, attributes);
        
    }

    @GetMapping
    public ModelAndView searchCategory(@ModelAttribute("filter") CategoryFilter filter) {
        List<Category> allCategories = categoryService.filter(filter);
        ModelAndView mv = new ModelAndView("PageCategoriesSearch");
        mv.addObject("categories", allCategories);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView editCategory(@PathVariable Long id){
        Category category = categoryService.editCategory(id);
        ModelAndView mv = new ModelAndView(REGISTER_VIEW);
        mv.addObject(category);
        return mv;
    }    
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        return categoryService.deleteById(id, attributes);
    }

}
