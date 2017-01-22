package intermediate;

import domain.Article;
import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

/**
 * Testing the compositions
 * <p>
 * Created by jobaer on 1/22/2017.
 */
public class TestComposeObservables {
    @Test
    public void testBasicSearch() {
        ComposeObservables observables = new ComposeObservables();
        Observable<Article> searchResult = observables.search("test");
        assertSearchProperties(searchResult);
    }

    @Test
    public void testAlternateSearch() {
        ComposeObservables observables = new ComposeObservables();
        Observable<Article> searchResult = observables.searchV2("something");
        assertSearchProperties(searchResult);
    }

    @Test
    public void testSearchWithZip() {
        ComposeObservables observables = new ComposeObservables();
        Observable<Article> searchResult = observables.searchUsingZip("zippo");
        assertSearchProperties(searchResult);
    }

    private void assertSearchProperties(Observable<Article> searchResult) {
        TestSubscriber<Article> testSubscriber = new TestSubscriber<>();
        searchResult.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();

        testSubscriber.awaitValueCount(10, 5, TimeUnit.SECONDS);
        List<Article> events = testSubscriber.getOnNextEvents();
        events.forEach(article -> {
            int id = article.getId();
            int likeCount = article.getLikeCount();
            assertEquals(id * 10, likeCount);
        });
    }
}
