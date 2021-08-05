package br.com.gft.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.realestate.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Long>{
    
}
