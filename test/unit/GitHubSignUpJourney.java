import org.junit.Test;
import pages.GitHubHomePage;
import pages.GitHubSignUpPage;

/**
 * Created by girish.prabhakar on 17/02/14.
 */
public class GitHubSignUpJourney extends GitHubJourney {

    @Test
    public void signUp() {
        gitHubHomePage.signUp();
        String userName = "SirTest" + System.currentTimeMillis();
        String userEmail = userName + "@yahoo.com";

        new GitHubSignUpPage(browser).get()
                .userName(userName)
                .email(userEmail)
                .password("random123")
                .passwordConfirm("random123")
                .createAccount();
    }
}
