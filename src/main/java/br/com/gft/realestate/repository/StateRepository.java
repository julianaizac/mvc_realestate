package br.com.gft.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.realestate.model.State;

public interface StateRepository extends JpaRepository<State, Long>{

    public List<State> findByNameContaining(String name);
    
}
