import org.junit.Test;
import pages.GitHubHomePage;
import pages.GitHubSignInPage;

public class GitHubSignInJourney extends GitHubJourney {

    @Test
    public void signIn() {
        gitHubHomePage.signIn();
        new GitHubSignInPage(browser).get()
                   .userName("m_p_girish@yahoo.com")
                   .password("SirSelenium0")
                   .signIn();
    }
}
