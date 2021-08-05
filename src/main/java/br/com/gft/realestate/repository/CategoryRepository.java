package br.com.gft.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.realestate.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    public List<Category> findByNameContaining(String name);
    
}
