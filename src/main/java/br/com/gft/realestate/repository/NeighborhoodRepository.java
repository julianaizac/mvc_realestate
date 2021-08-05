package br.com.gft.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.realestate.model.Neighborhood;

public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long>{
    
    public List<Neighborhood> findByNameContaining(String name);

}
