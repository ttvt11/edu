package com.edu.homepage.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.homepage.board.dto.BoardDto;
import com.edu.homepage.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	private static final String DEL = "Y";
	
	@Autowired
	BoardMapper boardMapper;
	
	public List<BoardDto> getSelectBoardList() throws Exception{
		List<BoardDto> boardList = boardMapper.selectBoardList();
		List<BoardDto> notDelList = new ArrayList<BoardDto>();
		List<BoardDto> notDelList2 = new ArrayList<BoardDto>();
		
		notDelList = delFlag(boardList, notDelList);
		
		for(BoardDto list : boardList) {
			if(DEL.equals(list.getDeletedYn())) {
				continue;
			}
		notDelList2.add(list);
		}
		
		List<BoardDto> notDelList3 = boardList.stream().filter(list -> !DEL.equals(list.getDeletedYn())).collect(Collectors.toList());
		return notDelList3;
	}
	
	@Override
	public void addInsertBoard(BoardDto dto) throws Exception {
		boardMapper.insertBoard(dto);
	}

	@Override
	public BoardDto getselectBoardDetail(int boardIdx) throws Exception {
		boardMapper.updateHitCount(boardIdx);
		return boardMapper.selectBoardDetail(boardIdx);
	}

	@Override
	public void updateBoardInfo(BoardDto dto) throws Exception {
		boardMapper.updateBoard(dto);
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
	}
	
	
	/**
	 * 기본형 for문을 이용한 삭제되지 않은 게시판정보리스트를 취득하는 메소드
	 * @param listAll
	 * @param notDel
	 * @return
	 */
	private List<BoardDto> delFlag(List<BoardDto> listAll, List<BoardDto> notDel) {
		for (int i = 0; i< listAll.size(); i++) {
			if(DEL.equals(listAll.get(i).getDeletedYn())) {
				continue;
			}
			notDel.add(listAll.get(i));
		}
			return notDel;
		
	}
	

}
