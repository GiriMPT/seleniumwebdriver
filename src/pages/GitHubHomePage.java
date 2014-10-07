package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Browser;


public class GitHubHomePage extends GitHubLoadableComponent<GitHubHomePage> {

    private static final String CONTEXT_TITLE = "GitHub";

    @FindBy(xpath = "//a[text() = 'Sign in']")
    private WebElement signIn;

    @FindBy(xpath = "//button[text()='Sign up for GitHub']")
    private WebElement signUp;

    public GitHubHomePage(Browser browser) {
        super(browser);
    }

    public GitHubHomePage signIn() {
        signIn.click();
        andWait();
        return this;
    }

    public GitHubHomePage signUp() {
        signUp.click();
        andWait();
        return this;
    }


    @Override
    protected void load() {
        driver().get(browser.configurationReader.valueNamedString("github.url"));
    }

    @Override
    protected void isLoaded() throws Error {
        andWait();
        isContextTitleLoaded();
    }
}
