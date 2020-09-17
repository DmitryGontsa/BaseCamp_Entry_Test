package pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobject.AbstractPage;

public class AuthenticationMainPage extends AbstractPage {

    @FindBy(xpath = "//input[@name='username']")
    private WebElement userNameInput;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@class='radius']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='flash error']")
    private WebElement errorMessage;

    public AuthenticationMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void setUserName(String userName) {
        webDriverWait.until(ExpectedConditions.visibilityOf(userNameInput));
        userNameInput.sendKeys(userName);
    }

    public void setPassword(String password) {
        webDriverWait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.sendKeys(password);
    }

    public void clickOnLoginButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public HerokuAppSecurePage selectLoginButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        return new HerokuAppSecurePage(webDriver);
    }

    public String getErrorMessageText() {
        webDriverWait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }
}
