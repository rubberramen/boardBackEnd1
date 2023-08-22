package test.appTest.model.dao;

import org.junit.jupiter.api.Test;
import test.appTest.model.dto.BoardDto;

import static org.junit.jupiter.api.Assertions.*;

class BoardDaoTest {

    BoardDao boardDao = new BoardDao();

    @Test
    void test01() {
        BoardDto boardDto = boardDao.selectOne(1);
        System.out.println("boardDto = " + boardDto);
    }

    @Test
    void test02() {
        boardDao.deleteOne(10);
    }

    @Test
    void test03() {
        BoardDto dto = new BoardDto();
        dto.setTitle("코코");
        dto.setContents("코코");
        dto.setAuthor("코코");

        boardDao.insertOne(dto);
    }

}