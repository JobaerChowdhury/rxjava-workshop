package domain;

public class Article {
    private PersistentArticle persistentArticle;
    private Integer likeCount;

    public Article(PersistentArticle persistentArticle, Integer likeCount) {
        this.persistentArticle = persistentArticle;
        this.likeCount = likeCount;
    }

    public Integer getId() {
        return persistentArticle.getId();
    }

    public String getTitle() {
        return persistentArticle.getTitle();
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + persistentArticle.getId() +
                ", title=" + persistentArticle.getTitle() +
                ", likeCount=" + likeCount +
                '}';
    }
}
