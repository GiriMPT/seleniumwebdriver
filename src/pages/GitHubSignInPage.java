package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Browser;

public class GitHubSignInPage extends GitHubLoadableComponent<GitHubSignInPage> {

    private static final String CONTEXT_TITLE = "GitHub";

    @FindBy(id = "login_field")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//input[@class='button']")
    private WebElement signInButton;

    public GitHubSignInPage(Browser browser) {
        super(browser);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        andWait();
        isContextTitleLoaded();
    }

    public GitHubSignInPage userName(String userNameValue) {
        sendKeys(userName , userNameValue);
        return this;
    }

    public GitHubSignInPage password(String passwordValue) {
        sendKeys(password , passwordValue);
        return this;
    }

    public GitHubSignInPage signIn() {
        signInButton.click();
        return this;
    }
}
