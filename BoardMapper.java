package com.edu.homepage.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.edu.homepage.board.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	List<BoardDto> selectBoardList() throws Exception;
	
	void insertBoard(BoardDto dto) throws Exception;
	
	BoardDto selectBoardDetail(int boardIdx) throws Exception;

	void updateHitCount(int boardIdx) throws Exception;
	
	void updateBoard(BoardDto dto) throws Exception;
	
	void deleteBoard(int boardIdx) throws Exception;

}