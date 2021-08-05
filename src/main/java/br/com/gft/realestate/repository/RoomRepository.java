package br.com.gft.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.realestate.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{

    public List<Room> findByAmountContaining(Integer amount);

}
