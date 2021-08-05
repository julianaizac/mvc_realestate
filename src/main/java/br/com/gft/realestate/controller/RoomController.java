package br.com.gft.realestate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.realestate.model.Room;
import br.com.gft.realestate.service.RoomService;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    RoomService roomService;

    private static final String REGISTER_VIEW = "PageRoomRegistration";

    @GetMapping("/newroom")
    public ModelAndView newRoom(){
        ModelAndView mv = new ModelAndView(REGISTER_VIEW);
        mv.addObject(new Room());
        return mv;
    }

    @PostMapping
    public String saveRoom(@Validated Room room, Errors errors, RedirectAttributes attributes){
        if(errors.hasErrors()){
            return REGISTER_VIEW;
        }
        return roomService.saveRoom(room, attributes);  
    }

    @GetMapping
    public ModelAndView searchRoom() {
        List<Room> allRooms = roomService.findAllRooms();
        ModelAndView mv = new ModelAndView("PageRoomsSearch");
        mv.addObject("rooms", allRooms);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView editRoom(@PathVariable Long id){
        Room room = roomService.editRoom(id);
        ModelAndView mv = new ModelAndView(REGISTER_VIEW);
        mv.addObject(room);
        return mv;
    } 

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        return roomService.deleteById(id, attributes);
        
    }
    
}
