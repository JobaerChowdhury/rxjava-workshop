package intermediate;

import domain.Article;
import rx.Observable;

import static helpers.ImplementationHelper.sleep;
import static service.ArticleService.*;

/**
 * Intermediate compositions
 */
public class ComposeObservables {
    Observable<Article> search(String query) {
        return searchForArticles(query).flatMap(
            id -> loadArticle(id).flatMap(
                pa -> fetchLikeCount(id).flatMap(
                    lc -> Observable.just(new Article(pa, lc)))));
    }

    Observable<Article> searchUsingZip(String query) {
        Observable<Integer> integerObservable = searchForArticles(query);
        return integerObservable.flatMap(
            id -> Observable.zip(loadArticle(id), fetchLikeCount(id), (pa, lc) -> new Article(pa, lc)));
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
