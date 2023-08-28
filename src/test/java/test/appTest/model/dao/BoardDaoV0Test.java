package test.appTest.model.dao;

import model.dao.BoardDaoV0;
import org.junit.jupiter.api.Test;
import model.dto.BoardDto;

class BoardDaoV0Test {

    BoardDaoV0 boardDaoV0 = new BoardDaoV0();

//    @Test
//    void test01() {
//        BoardDto boardDto = boardDaoV0.selectOne(1);
//        System.out.println("boardDto = " + boardDto);
//    }

    @Test
    void test02() {
        boardDaoV0.deleteOne(10);
    }

    @Test
    void test03() {
        BoardDto dto = new BoardDto();
        dto.setTitle("코코");
        dto.setContents("코코");
        dto.setAuthor("코코");

        boardDaoV0.insertOne(dto);
    }

    @Test
    void update() {
        BoardDto updatedDto = new BoardDto();
        updatedDto.setIdx(3L);
        updatedDto.setTitle("게시글 제목3 수정");
        updatedDto.setContents("게시글 내용3 수정");

        boardDaoV0.update(updatedDto);

    }

}