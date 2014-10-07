package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

public class Browser {

    private WebDriver driver;
    public ConfigurationReader configurationReader = new ConfigurationReader("github-selenium.properties");

    public Browser loadBrowser() {
        String browser = System.getProperty("browser");
        if(browser != null && browser.equalsIgnoreCase("IE")){
            return loadIE();
        }
        return loadFirefox();
    }

    private Browser loadFirefox() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("network.negotiate-auth.delegation-uris", configurationReader.valueNamedString("github.domain"));
        firefoxProfile.setPreference("network.negotiate-auth.trusted-uris", configurationReader.valueNamedString("github.domain"));

        driver = new FirefoxDriver(firefoxProfile);

        return this;
    }

    private Browser loadIE() {
        String IEDriverLocation = "c:/IEDriverServer.exe";
        File IEDriver = new File(IEDriverLocation);
        if(!IEDriver.exists()){
            throw new RuntimeException("IE Driver not found please download 'https://code.google.com/p/selenium/downloads/detail?name=IEDriverServer_Win32_2.35.3.zip' and install at "+IEDriverLocation);
        }
        System.setProperty("webdriver.ie.driver", IEDriverLocation);
        driver = new InternetExplorerDriver();

        return this;
    }

    public Browser maximize() {
        driver.manage().window().maximize();
        return this;
    }
    public WebDriver getDriver() {
        return driver;
    }

    public long getDefaultWaitTime() {
        return configurationReader.valueNamedLong("github.default.waitTime");
    }

    public void setInput(String factName, String value) {
        WebElement element = driver.findElement(By.xpath("//input[contains(@id,'"+factName+"')]"));
        element.clear();
        element.sendKeys(value);
    }

    public void clickButton(String id) {

        WebElement searchButton = driver.findElement(By.id(id));

        searchButton.click();
    }
    
    public void clickButtonByXpath(String xpath) {
    	WebElement searchButton = driver.findElement(By.xpath(xpath));
        searchButton.click();
        waitForAjax();
    }

    public void selectOptionValue(String factName, String optionValue) {
        WebDriverWait wait = new WebDriverWait(driver, getDefaultWaitTime());

        WebElement factSelect = findElementByXpath("//img[contains(@id,'"+factName+"_image')]");
        factSelect.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@id,'" + factName + "')][text()='" + optionValue + "']")));
        WebElement factOption = driver.findElement(By.xpath("//span[contains(@id,'"+factName+"')][text()='"+optionValue+"']"));
        factOption.click();
    }

    public void waitForAjax(){
        try {
            new WebDriverWait(driver, 300)
                    .until(invisibilityOfElementLocated(By.xpath("//*[@id='mainForm:statusComp:connection-working']")));
        } catch (TimeoutException e) {

        } finally {
//            if(!driver.findElements(By.xpath("//*[@id='mainForm:statusComp:connection-trouble']")).isEmpty())
//                throw new TimeoutException("Red Circle of doom!");
//
//            if(!driver.findElements(By.xpath("//*[@id='mainForm:statusComp:connection-trouble']")).isEmpty())
//                throw new TimeoutException("Yellow Triangle of impending doom!");
        }
    }
    
    public void waitForXpathPresent(String xpath) {
    	WebDriverWait wait = new WebDriverWait(driver, getDefaultWaitTime());
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
    
    public void closeAllWindows() {
    	if(driver != null) {
    		driver.quit();
    	}
    }


    
	public void sleep(long timeMillis) {
		try {
			Thread.sleep(timeMillis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public WebElement findElementById(final String id) {
		WebDriverWait myWait = new WebDriverWait(getDriver(), 10);
	    ExpectedCondition<Boolean> conditionToCheck = new ExpectedCondition<Boolean>() {
	        @Override
	        public Boolean apply(WebDriver input) {
	            try {
	            	input.findElement(By.id(id));
	            	return true;
	            } catch (Exception e) {
	            	return false;
	            }
	        }
	    };
	    myWait.until(conditionToCheck);
	    return getDriver().findElement(By.id(id));
	}
		
	public WebElement findElementByXpath(final String xpath) {
		WebDriverWait myWait = new WebDriverWait(getDriver(), 10);
	    ExpectedCondition<Boolean> conditionToCheck = new ExpectedCondition<Boolean>() {
	        @Override
	        public Boolean apply(WebDriver input) {
	            try {
	            	input.findElement(By.xpath(xpath));
	            	return true;
	            } catch (Exception e) {
	            	return false;
	            }
	        }
	    };
	    myWait.until(conditionToCheck);
		return getDriver().findElement(By.xpath(xpath));
	}

    public void waitElementToBeClickable(String xpath){
        WebDriverWait wait = new WebDriverWait(driver, getDefaultWaitTime());
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public void checkIfElement(String xpath) {
        ExpectedConditions.presenceOfElementLocated(By.xpath(xpath));
    }
	
}
