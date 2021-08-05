package br.com.gft.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.realestate.model.Business;

public interface BusinessRepository extends JpaRepository<Business, Long>{

    public List<Business> findByNameContaining(String name);
    
}
