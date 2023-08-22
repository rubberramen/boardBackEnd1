package test.appTest.model.dto;

public class BoardDto {

    private Long idx;
    private String title;
    private String contents;
    private String author;
    private String createdAt;

    public BoardDto() {
    }

    public BoardDto(Long idx, String title, String contents, String author, String createdAt) {
        this.idx = idx;
        this.title = title;
        this.contents = contents;
        this.author = author;
        this.createdAt = createdAt;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "idx=" + idx +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", author='" + author + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
