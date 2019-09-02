package com.edu.homepage.board.service;

import java.util.List;

import com.edu.homepage.board.dto.BoardDto;

public interface BoardService {
	
	
	
	List<BoardDto> getSelectBoardList() throws Exception; 
	
	void addInsertBoard(BoardDto dto) throws Exception;
	
	BoardDto getselectBoardDetail(int boardIdx) throws Exception;
	
	void updateBoardInfo(BoardDto dto) throws Exception;
	
	void deleteBoard(int boardIdx) throws Exception;

}