package dev.golony.blog;

public class PostDto {
    private int bId;
    private String name;
    private String title;
    private String content;
    private String date;
    private int hit;

    public PostDto(int bId, String name, String title, String content, String date, int hit) {
        this.bId = bId;
        this.name = name;
        this.title = title;
        this.content = content;
        this.date = date;
        this.hit = hit;
    }

    public int getbId() {
        return bId;
    }

    public String getName() { return name; }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public int getHit() {
        return hit;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public void setName(String name) { this.name = name; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }
}
