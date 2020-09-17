package pageobject.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobject.AbstractPage;

@Getter
public class DynamicLoadingMainPage extends AbstractPage {

    @FindBy(xpath = "//div[@id='start']/button/.")
    private WebElement startButton;

    @FindBy(xpath = "//div[@id='finish']/h4")
    private WebElement messageField;

    public DynamicLoadingMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickOnStartButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(startButton));
        startButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(messageField));
    }

    public String getMessageFieldText() {
        webDriverWait.until(ExpectedConditions.visibilityOf(messageField));
        return messageField.getText();
    }
}
