package intermediate;

import domain.Article;
import domain.PersistentArticle;
import rx.Observable;

import static helpers.ImplementationHelper.sleep;
import static service.ArticleService.*;

/**
 * Intermediate compositions
 */
public class ComposeObservables {
    Observable<Article> search(String query) {
        Observable<Integer> articleIds = searchForArticles(query);
        return articleIds.flatMap(id -> {
            Observable<PersistentArticle> dbArticle = loadArticle(id);
            Observable<Integer> likeCount = fetchLikeCount(id);
            return dbArticle.flatMap(
                    article -> likeCount.flatMap(
                            lc -> Observable.just(new Article(article, lc))));
        });
    }

    Observable<Article> searchUsingZip(String query) {
        return searchForArticles(query).flatMap(
                id -> Observable.zip(loadArticle(id), fetchLikeCount(id), Article::new));
    }

    public static void main(String[] args) {
        System.out.println("Testing intermediate compositions....");
        ComposeObservables service = new ComposeObservables();
        service.search("test").subscribe(
                System.out::println,
                s -> {
                },
                () -> {
                    System.out.println("Completed");
                });
        sleep(4000);
    }
}
