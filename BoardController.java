package com.edu.homepage.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu.homepage.board.dto.BoardDto;
import com.edu.homepage.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception {
		ModelAndView mv = new ModelAndView("board/boardList");
		mv.addObject("list", boardService.getSelectBoardList());
		return mv;
	}
	
	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception {
		
		return "board/boardWrite";
	}
	
	
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(BoardDto dto) throws Exception {
			boardService.addInsertBoard(dto);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/openBoardDetail.do")
		public ModelAndView openBoardDetail(int boardIdx) throws Exception {
			ModelAndView mv = new ModelAndView("board/boardDetail");
			mv.addObject("board", boardService.getselectBoardDetail(boardIdx));
			return mv;
	}
	
	@RequestMapping("/board/updateBoard.do")
	public String openBoardDetail(BoardDto dto) throws Exception {
		boardService.updateBoardInfo(dto);
		return "redirect:/board/openBoardList.do";
	}
	
	
	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/openBoardList.do";
	}
	
	
	
}
