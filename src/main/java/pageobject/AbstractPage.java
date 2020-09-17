package pageobject;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@Data
public abstract class AbstractPage {

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;
    protected Actions actions;

    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.actions = new Actions(webDriver);
        webDriverWait = new WebDriverWait(webDriver, 30);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(webDriver, this);
    }

    public Actions moveToWebElement(WebElement webElement) {
        Actions builder = new Actions(webDriver);
        Actions hoverOverLocationSelector = builder.moveToElement(webElement);
        hoverOverLocationSelector.perform();
        return hoverOverLocationSelector;
    }
}