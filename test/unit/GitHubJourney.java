import org.junit.After;
import org.junit.Before;
import pages.GitHubHomePage;
import utils.Browser;

/**
 * Created by girish.prabhakar on 17/02/14.
 */
public abstract class GitHubJourney {

    protected Browser browser;

    protected GitHubHomePage gitHubHomePage;

    @Before
    public void prepareGitHub() {
        browser = new Browser()
                .loadBrowser()
                .maximize();

        gotoHomePage();
    }


    public void gotoHomePage() {
        gitHubHomePage = new GitHubHomePage(browser).get();
    }

    @After
    public void cleanUp() {
       // browser.closeAllWindows();
    }
}
