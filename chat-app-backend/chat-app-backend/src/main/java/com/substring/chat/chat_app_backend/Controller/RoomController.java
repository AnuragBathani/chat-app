package com.substring.chat.chat_app_backend.Controller;


import com.substring.chat.chat_app_backend.Entities.Message;
import com.substring.chat.chat_app_backend.Entities.Room;
import com.substring.chat.chat_app_backend.Repo.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/v1/rooms")
public class RoomController {

    RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    //create room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomid){
        if(roomRepository.findByRoomId(roomid) != null){
            return ResponseEntity.badRequest().body("Room already Exist!!");
        }

        Room room=new Room();
        room.setRoomId(roomid);
        Room savedRoom = roomRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    // get rooms

    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(
            @PathVariable String roomId
    ){
        Room room=roomRepository.findByRoomId(roomId);

        if(room == null){
            return ResponseEntity.badRequest().body("Room not found!!");
        }

        return ResponseEntity.ok(room);


    }

    //get messages

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessage(
            @PathVariable String roomId,
            @RequestParam(value = "page",defaultValue = "0",required = false) int page,
            @RequestParam(value = "size",defaultValue = "20",required = false) int size
    ){

        Room room = roomRepository.findByRoomId(roomId);
        if(room == null){
            return ResponseEntity.badRequest().build();
        }

        List<Message> messages = room.getMessages();
        int start=Math.max(0,messages.size() - (page+1) * size);
        int end=Math.min(messages.size(),start+size);
        List<Message> paginetedmessage = messages.subList(start, end);


        return ResponseEntity.ok(paginetedmessage);




    }
}
