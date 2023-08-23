package test.appTest.model.dao;

import test.appTest.model.db.JdbcUtils;
import test.appTest.model.dto.BoardDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {

    // update
    public void update(BoardDto updatedDto) {
//        Integer idx = (Integer) dto.getIdx();
//        BoardDto orginalDto = selectOne(updatedDto.getIdx());
        String sql = "update board3 set title = ?, contents = ? where idx = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, updatedDto.getTitle());
            pstmt.setString(2, updatedDto.getContents());
            pstmt.setLong(3, updatedDto.getIdx());
            int i = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("boardUpdate 에러 : " + e);
        } finally {
            JdbcUtils.close(conn, pstmt);
        }
    }

    // insert
    public void insertOne(BoardDto dto) {
        String sql = "insert into board3 (TITLE, CONTENTS, AUTHOR, CREATED_AT) values(?,?,?, now())";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getTitle());
            pstmt.setString(2, dto.getContents());
            pstmt.setString(3, dto.getAuthor());
            int i = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("boardInsert 에러 : " + e);
        } finally {
            JdbcUtils.close(conn, pstmt);
        }
    }

    public void deleteOne(int idx) {
        String sql = "delete from board3 where idx=?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JdbcUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idx);
            int i = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("boardDelete 에러 : " + e);
        } finally {
            JdbcUtils.close(conn, pstmt);
        }
    }

    // idx로 찾기
    public BoardDto selectOne(Long idx) {
        BoardDto boardDto = new BoardDto();
        String sql = "select * from board3 where idx = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, idx);
            pstmt.setLong(1, idx);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                boardDto.setIdx(rs.getLong("idx"));
                boardDto.setTitle(rs.getString("title"));
                boardDto.setContents(rs.getString("contents"));
                boardDto.setAuthor(rs.getString("author"));
                boardDto.setCreatedAt(rs.getString("created_at"));
            }
        } catch (SQLException e) {
            System.out.println("getDetail 에러 : " + e);
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.close(conn, pstmt, rs);
        }

        return boardDto;
    }

    public List<BoardDto> selectAll() {
        List<BoardDto> list = new ArrayList<>();
        String sql = "select * from board3";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardDto boardDto = new BoardDto();
                boardDto.setIdx(rs.getLong("idx"));
                boardDto.setTitle(rs.getString("title"));
                boardDto.setContents(rs.getString("contents"));
                boardDto.setAuthor(rs.getString("author"));
                boardDto.setCreatedAt(rs.getString("created_at"));

                list.add(boardDto);
            }
        } catch (SQLException e) {
            System.out.println("getDetail 에러 : " + e);
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.close(conn, pstmt);
        }

        return list;
    }

    // test : 글 하나 보기
    public BoardDto selectOneArticle() {
        BoardDto boardDto = new BoardDto();
        String sql = "select * from board3 where idx = 1";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                boardDto.setIdx(rs.getLong("idx"));
                boardDto.setTitle(rs.getString("title"));
                boardDto.setContents(rs.getString("contents"));
                boardDto.setAuthor(rs.getString("author"));
                boardDto.setCreatedAt(rs.getString("created_at"));
            }
        } catch (SQLException e) {
            System.out.println("getDetail 에러 : " + e);
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.close(conn, pstmt, rs);
        }
        return boardDto;
    }
}
