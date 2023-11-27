package com.pro06.service;

import com.pro06.entity.Chat;
import com.pro06.entity.Room;
import com.pro06.repository.ChatRepository;
import com.pro06.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final RoomRepository roomRepository;
    private final ChatRepository chatRepository;

    // 모든 채팅방 찾기
    public List<Room> findAllRoom() {
        return roomRepository.findAll();
    }

    // 특정 채팅방 찾기
    public Room findRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow();
    }

    // 채팅방 만들기
    public Room createRoom(String name) {
        return roomRepository.save(Room.createRoom(name));
    }

    // 채팅 생성
    public Chat createChat(Long roomId, String sender, String message) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        return chatRepository.save(Chat.createChat(room, sender, message));
    }

    // 채팅방 채팅 내용 불러오기
    public List<Chat> findAllChatByRoomId(Long roomId) {
        return chatRepository.findAllByRoomId(roomId);
    }

}
