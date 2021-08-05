package br.com.gft.realestate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.realestate.model.Room;
import br.com.gft.realestate.repository.RoomRepository;

@Service
public class RoomService {
    
    @Autowired
    RoomRepository roomRepository;

    public String saveRoom(Room room, RedirectAttributes attributes) {
        try {
            roomRepository.save(room);
            attributes.addFlashAttribute("message", "Quarto salvo com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Erro ao salvar o quarto.");
        }
        return "redirect:/rooms/newroom";
    }

    public List<Room> findAllRooms() {
        List<Room> allRooms = roomRepository.findAll();
        return allRooms;
    }

    public Room editRoom(Long id) {
        return roomRepository.getOne(id);
    }

    public String deleteById(Long id, RedirectAttributes attributes){
        try {
            roomRepository.deleteById(id);
            attributes.addFlashAttribute("message", "Quantidade de Quarto excluído com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("message", "Erro ao excluir a quantidade de Quarto.");
        }
		return "redirect:/rooms";
    }

}
