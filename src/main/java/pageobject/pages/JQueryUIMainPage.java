package pageobject.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobject.AbstractPage;

import java.io.File;

@Getter
public class JQueryUIMainPage extends AbstractPage {

    @FindBy(xpath = "(//a[@class='ui-corner-all'])[3]")
    private WebElement enabledMenuItem;

    @FindBy(xpath = "(//li[@role='presentation']/a[@class='ui-corner-all'])[3]")
    private WebElement downloadsMenuItem;

    @FindBy(xpath = "(//ul[@class='ui-menu ui-widget ui-widget-content ui-corner-all']//li[@class='ui-menu-item'])[6]")
    private WebElement excelMenuItem;

    public JQueryUIMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clicksOnExcelItem() {
        webDriverWait.until(ExpectedConditions.visibilityOf(enabledMenuItem)).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(downloadsMenuItem)).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(excelMenuItem)).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(excelMenuItem));
    }

    private File dir = new File("C:/Users/DMITRY GONTSA/Downloads/menu.xls");
}
