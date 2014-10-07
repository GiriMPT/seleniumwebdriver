package pages;

import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
import utils.Browser;

public abstract class GitHubLoadableComponent<T extends GitHubLoadableComponent<T>> extends LoadableComponent<T> {

    protected final Browser browser;

    @FindBy(xpath = "//span[@class='mega-octicon octicon-logo-github']")
    protected WebElement contextTitle;

    protected GitHubLoadableComponent(Browser browser) {
        this.browser = browser;
        PageFactory.initElements(browser.getDriver(), this);
    }

    protected WebDriver driver() {
        return browser.getDriver();
    }

    public T andWait() {
        browser.waitForAjax();
        return (T) this;
    }


    protected WebElement waitFor(final WebElement element) {
        new WebDriverWait(driver(), 10).until(new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver driver) {
                try {
                    return element.isDisplayed() && element.isEnabled();
                }catch (WebDriverException e) {
                    return false;
                }
            }
        });
        return element;
    }

    protected boolean checkIfElementExists(final String xpath) {
       return driver().findElements(By.xpath(xpath)).size() != 0;

    }

    protected T doubleClick(final WebElement element) {
        new Actions(browser.getDriver())
                .doubleClick(waitFor(element))
                .perform();
        return (T) this;
    }

    protected T click(final WebElement element) {
        waitFor(element).click();
        return (T) this;
    }

    protected T sendKeys(final WebElement element, String keys) {
        waitFor(element).clear();
        waitFor(element).sendKeys(keys);
        return andWait();
    }

    protected WebElement contextTitle() {
        return waitFor(contextTitle);
    }



    protected void isContextTitleLoaded() throws Error {
        try {
            if(!contextTitle().isDisplayed())
                throw new Error("GitHub Context not loaded");
        } catch (StaleElementReferenceException e) {
            // try again - page has reloaded.
            isContextTitleLoaded();
        } catch (WebDriverException e) {
            throw new Error(e);
        }
    }


    public T waitUntilElementToBeClickable(String xpath) {
        browser.waitElementToBeClickable(xpath);
        return (T) this;
    }


}
