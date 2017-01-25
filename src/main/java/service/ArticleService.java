package service;

import domain.PersistentArticle;
import rx.Observable;

import java.util.Arrays;
import java.util.List;

import static helpers.ImplementationHelper.randomSleep;
import static helpers.ImplementationHelper.sleep;

/**
 * Dummy article service.
 */
public class ArticleService {
    public static List<Integer> searchForArticlesSync(String query) {
        System.out.println("Calculating search result on " + Thread.currentThread().getName());
        sleep(1000);
        return Arrays.asList(12, 23, 32, 1, 3, 4, 6, 7, 8, 90);
    }

    public static Observable<Integer> searchForArticles(String query) {
        return Observable.from(searchForArticlesSync(query));
    }

    public static PersistentArticle loadArticleSync(Integer articleId) {
        System.out.println("Loading article on " + Thread.currentThread().getName());
        randomSleep(500, 1500);
        return new PersistentArticle(articleId, "Article title " + articleId);
    }

    public static Observable<PersistentArticle> loadArticle(Integer articleId) {
        return Observable.just(loadArticleSync(articleId));
    }

    public static Integer fetchLikeCountSync(Integer articleId) {
        System.out.println("Fetching like count on " + Thread.currentThread().getName());
        randomSleep(500, 800);
        return articleId * 10;
    }

    public static Observable<Integer> fetchLikeCount(Integer articleId) {
        return Observable.just(fetchLikeCountSync(articleId));
    }

    public static void main(String[] args) {
        System.out.println("Testing article service");
        sleep(2500);
    }
}
