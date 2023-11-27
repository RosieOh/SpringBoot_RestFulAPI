package com.pro06.repository;

import com.pro06.entity.Chat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRepository extends CrudRepository<Chat, Long> {

    List<Chat> findAllByRoomId(Long roomId);
}
