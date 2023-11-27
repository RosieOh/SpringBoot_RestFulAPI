package com.pro06.service;

import com.pro06.domain.BoardDTO;
import com.pro06.entity.Board;

import java.util.List;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    BoardDTO readOne(Long bno);

    void modify(BoardDTO boardDTO);

    void remove(Long bno);
}
