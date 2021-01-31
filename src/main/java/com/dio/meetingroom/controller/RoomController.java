package com.dio.meetingroom.controller;

import com.dio.meetingroom.exception.ResourceNotFoundException;
import com.dio.meetingroom.model.Room;
import com.dio.meetingroom.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/vi/rooms")
public class RoomController {

    @Autowired
    RoomRepository repository;

    @GetMapping
    public List<Room> getAllRooms(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") long roomId) throws ResourceNotFoundException {
        Room room = repository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found:: " + roomId));
        return  ResponseEntity.ok().body(room);
    }

    @PostMapping
    public Room createRoom(@Valid @RequestBody Room room){
        return repository.save(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(value = "id") long roomId,
                                           @Valid @RequestBody Room roomDetails) throws ResourceNotFoundException {

        Room room = repository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id:: " + roomId));
        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setStarHour(roomDetails.getStarHour());
        room.setEndHour(roomDetails.getEndHour());
        final Room updateRoom = repository.save(room);
        return ResponseEntity.ok(updateRoom);
    }

    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long roomId) throws ResourceNotFoundException {
        Room room = repository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found for this id:: " + roomId));

        repository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
