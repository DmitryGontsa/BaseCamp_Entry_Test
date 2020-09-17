package pageobject.pages;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobject.AbstractPage;

@Getter
public class HerokuAppSecurePage extends AbstractPage {

    @FindBy(xpath = "//a[@class='button secondary radius']")
    private WebElement logoutButton;

    @FindBy(xpath = "//div[@class='flash success']")
    private WebElement successLoggedIntoMessage;

    @FindBy(xpath = "//h2")
    private WebElement secureAreaTitle;

    @FindBy(xpath = "//h4[@class='subheader']")
    private WebElement successSecureAriaMessage;

    public HerokuAppSecurePage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getSuccessLoggedIntoMessageText() {
        webDriverWait.until(ExpectedConditions.visibilityOf(successLoggedIntoMessage));
        String text = StringUtils.trim(successLoggedIntoMessage.getText());
        String messageText = text.substring(0, 30);
        return messageText;
    }

    public String getSuccessSecureAriaMessageText() {
        webDriverWait.until(ExpectedConditions.visibilityOf(successSecureAriaMessage));
        return successSecureAriaMessage.getText();
    }
}
