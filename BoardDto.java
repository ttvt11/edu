package com.edu.homepage.board.dto;

import lombok.Data;

//@Data : get,set메소드를 만들고, toString,hashcode 자동생성
@Data
public class BoardDto {

private int boardIdx;

private String title;

private String contents;

private int hitCnt;

private String createdDatetime;

private String creatorId;

private String updatedDatetime;

private String updaterId;

private String deletedYn;

}