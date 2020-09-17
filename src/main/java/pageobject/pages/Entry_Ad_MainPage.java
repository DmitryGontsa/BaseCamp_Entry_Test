package pageobject.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.AbstractPage;

@Getter
public class Entry_Ad_MainPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='modal-footer']/p")
    private WebElement closePopupButton;

    @FindBy(xpath = "//div[@class='modal-title']/h3")
    private WebElement popupTitle;

    @FindBy(xpath = "//a[@id='restart-ad'][@href]")
    private WebElement clickHereButton;

    public Entry_Ad_MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void closePopupPanel() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(closePopupButton));
        closePopupButton.click();
    }

    public void clickOnClickHereButton() {

        webDriverWait.until(ExpectedConditions.elementToBeClickable(clickHereButton)).click();

        new WebDriverWait(webDriver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='restart-ad'][@href]"))).click();

        actions.moveToElement(popupTitle).build().perform();
    }
}
