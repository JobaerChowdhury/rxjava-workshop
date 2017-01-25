package domain;

public class PersistentArticle {
    private Integer id;
    private String title;

    public PersistentArticle(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "PersistentArticle{" +
            "id=" + id +
            ", title='" + title + '\'' +
            '}';
    }
}
