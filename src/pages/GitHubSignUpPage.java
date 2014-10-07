package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Browser;

/**
 * Created by girish.prabhakar on 17/02/14.
 */
public class GitHubSignUpPage extends GitHubLoadableComponent<GitHubSignUpPage>{

    @FindBy(id = "user_login")
    private WebElement userName;

    @FindBy(id = "user_email")
    private WebElement userEmail;

    @FindBy(id = "user_password")
    private WebElement password;

    @FindBy(id = "user_password_confirmation")
    private WebElement passwordConfirmation;

    @FindBy(id = "signup_button")
    private WebElement createAccount;

    public GitHubSignUpPage(Browser browser) {
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

    public GitHubSignUpPage userName(String userNameValue) {
        sendKeys(userName, userNameValue);
        return this;
    }

    public GitHubSignUpPage email(String emailValue) {
        sendKeys(userEmail, emailValue);
        return this;
    }

    public GitHubSignUpPage password(String passwordValue) {
        sendKeys(password, passwordValue);
        return this;
    }

    public GitHubSignUpPage passwordConfirm(String passwordConfirmValue) {
        sendKeys(passwordConfirmation, passwordConfirmValue);
        return this;
    }

    public GitHubSignUpPage createAccount() {
        createAccount.click();
        return this;
    }
}
