package intermediate;

import domain.Article;
import rx.Observable;

import static helpers.ImplementationHelper.sleep;

/**
 * Intermediate compositions
 */
public class ComposeObservables {
    Observable<Article> search(String query) {
        throw new RuntimeException("Not implemented");
    }

    Observable<Article> searchUsingZip(String query) {
        throw new RuntimeException("Not implemented");
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
