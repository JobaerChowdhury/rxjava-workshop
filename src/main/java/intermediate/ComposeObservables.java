package intermediate;

import domain.Article;
import domain.PersistentArticle;
import rx.Observable;
import service.ArticleService;

import static helpers.ImplementationHelper.sleep;
import static service.ArticleService.fetchLikeCount;
import static service.ArticleService.loadArticle;
import static service.ArticleService.searchForArticles;

/**
 * Intermediate compositions
 */
public class ComposeObservables {
    Observable<Article> search(String query) {
        Observable<Integer> idList = searchForArticles(query);
        return idList.flatMap(id -> {
            Observable<PersistentArticle> persistentArticleObservable = loadArticle(id);
            Observable<Integer> likeCount = fetchLikeCount(id);
            return persistentArticleObservable.flatMap(dbArticle ->
                    likeCount.flatMap(
                            c -> Observable.just(new Article(dbArticle, c))));
        });
    }

    Observable<Article> searchV2(String query) {
        return searchForArticles(query).flatMap(
                id -> loadArticle(id).flatMap(
                        dbArticle -> fetchLikeCount(dbArticle.getId()).flatMap(
                                lc -> Observable.just(new Article(dbArticle, lc)))));
    }

    Observable<Article> searchV3(String query) {
        return searchForArticles(query).flatMap(id -> {
            Observable<PersistentArticle> dbArticle = loadArticle(id);
            Observable<Integer> likeCount = fetchLikeCount(id);
            return dbArticle.zipWith(likeCount, Article::new);
        });
    }

    public static void main(String[] args) {
        System.out.println("Testing intermediate compositions....");
        ComposeObservables service = new ComposeObservables();
        service.searchV3("test").subscribe(System.out::println);
        sleep(3000);
    }
}
