package br.com.gft.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.realestate.model.County;

public interface CountyRepository extends JpaRepository<County, Long>{
    
    public List<County> findByNameContaining(String name);

    public List<County> findByState_Id(Long id);

    public List<County> findByState_Name(String name);

}
