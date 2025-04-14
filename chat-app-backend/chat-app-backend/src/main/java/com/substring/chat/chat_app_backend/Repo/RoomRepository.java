package com.substring.chat.chat_app_backend.Repo;

import com.substring.chat.chat_app_backend.Entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room,String> {

    Room findByRoomId(String roomId);
}
