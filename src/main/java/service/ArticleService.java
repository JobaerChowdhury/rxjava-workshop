package service;

import domain.PersistentArticle;
import rx.Observable;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static helpers.ImplementationHelper.sleep;
import static helpers.ImplementationHelper.withRandomDelay;

/**
 * Dummy article service.
 */
public class ArticleService {
    public static Observable<Integer> searchForArticles(String query) {
        Observable<Integer> ids = Observable.from(Arrays.asList(12, 23, 32, 1, 3, 4, 6, 7, 8, 90));
        return ids.delay(1, TimeUnit.SECONDS);
    }

    public static Observable<PersistentArticle> loadArticle(Integer articleId) {
        PersistentArticle persistentArticle = new PersistentArticle(articleId, "Article title " + articleId);
        Observable<PersistentArticle> result = Observable.just(persistentArticle);
        return withRandomDelay(result);
    }

    public static Observable<Integer> fetchLikeCount(Integer articleId) {
        int totalLikes = articleId * 10;
        Observable<Integer> result = Observable.just(totalLikes);
        return withRandomDelay(result);
    }

    public static void main(String[] args) {
        System.out.println("Testing article service");
        sleep(2500);
    }
}
