package pageobject.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobject.AbstractPage;

@Getter
public class CheckBoxesMainPage extends AbstractPage {

    @FindBy(xpath = "(//input[@type='checkbox'])[1]")
    private WebElement checkBox;

    public CheckBoxesMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean verifyIsCheckBoxChecked() {
        Boolean isChecked;
        if (checkBox.isEnabled())
            isChecked = true;
        else
            isChecked = false;
        return isChecked;
    }

    public void clickOnCheckBox() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(checkBox));
        checkBox.click();
    }
}
