import common.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pageobject.data.UsersData;
import pageobject.pages.*;

public class HerokuTest extends BaseTest {

    @Test
    public void changeCheckBox() {

        String navigateUrl = propertyHelper.readProperty("herokuApp.checkboxes.url");
        webDriver.get(navigateUrl);

        CheckBoxesMainPage checkBoxesMainPage = new CheckBoxesMainPage(webDriver);
        checkBoxesMainPage.clickOnCheckBox();
        Boolean expectedResult = true;

        // First check variant:
        Assert.assertEquals("Wrong, CheckBox is not selected!",
                expectedResult, checkBoxesMainPage.getCheckBox().isSelected());

        // Next check variant:
        // Assertions.assertThat(checkBoxesMainPage.verifyIsCheckBoxChecked())
        //      .as("Error! CheckBox is not selected.")
        //      .isEqualTo(expectedResult);
    }

    @Test
    public void dynamicElement() {

        String navUrl = propertyHelper.readProperty("herokuApp.entry_ad.url");
        webDriver.get(navUrl);

        Entry_Ad_MainPage entry_ad_mainPage = new Entry_Ad_MainPage(webDriver);
        entry_ad_mainPage.closePopupPanel();
        entry_ad_mainPage.clickOnClickHereButton();

        String expectedResult = "THIS IS A MODAL WINDOW";
        String actualResult = entry_ad_mainPage.getPopupTitle().getText();

        Assertions.assertThat(actualResult)
                .as("The text 'THIS IS A MODAL WINDOW' is not displayed on the page!")
                .isEqualTo(expectedResult);
    }

    @Test
    public void testInvalidLogin() {

        String navigateUrl = propertyHelper.readProperty("herokuApp.login.url");
        webDriver.get(navigateUrl);
        UsersData data = new UsersData("David", "David@5577");

        AuthenticationMainPage authenticationMainPage = new AuthenticationMainPage(webDriver);
        authenticationMainPage.setUserName(data.getUSER_NAME());
        authenticationMainPage.setPassword(data.getPASSWORD());
        authenticationMainPage.clickOnLoginButton();

        String expectedResult = "Your username is invalid!";
        String actualResult = StringUtils.substring(authenticationMainPage.getErrorMessageText(), 0, 25);

        Assertions.assertThat(actualResult)
                .as("Attention! The message about incorrectly entered user data is not displayed on the screen!")
                .isEqualTo(expectedResult);
    }

    @Test
    public void testValidLogin() {

        String navUrl = propertyHelper.readProperty("herokuApp.login.url");
        webDriver.get(navUrl);
        UsersData data = new UsersData("tomsmith", "SuperSecretPassword!");

        AuthenticationMainPage authenticationMainPage = new AuthenticationMainPage(webDriver);
        authenticationMainPage.setUserName(data.getUSER_NAME());
        authenticationMainPage.setPassword(data.getPASSWORD());

        HerokuAppSecurePage herokuAppSecurePage = authenticationMainPage.selectLoginButton();

        String expectedResultForLoggedIntoMessage = "You logged into a secure area!";
        String expectedResultForSecureAriaMessage = "Welcome to the Secure Area. When you are done click logout below.";

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(herokuAppSecurePage.getLogoutButton().isDisplayed())
                .as("Error message! 'Logout' Button is not on display!")
                .isTrue();

        softAssertions.assertThat(herokuAppSecurePage.getSecureAreaTitle().isDisplayed())
                .as("Error! 'Secure Area' Title is missing from the screen!")
                .isTrue();

        softAssertions.assertThat(herokuAppSecurePage.getSuccessLoggedIntoMessageText())
                .as("Mistake! This field data is incorrect!")
                .isEqualTo(expectedResultForLoggedIntoMessage);

        softAssertions.assertThat(herokuAppSecurePage.getSuccessSecureAriaMessageText())
                .as("Error message! This field data is incorrect!")
                .isEqualTo(expectedResultForSecureAriaMessage);

        softAssertions.assertAll();
    }

    @Test
    public void testDynamicLoadingElement() {

        String urlAddress = propertyHelper.readProperty("herokuApp.dynamic_loading.url");
        webDriver.get(urlAddress);

        DynamicLoadingMainPage dynamicLoadingMainPage = new DynamicLoadingMainPage(webDriver);
        dynamicLoadingMainPage.clickOnStartButton();

        String expectedResult = "Hello World!";

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(dynamicLoadingMainPage.getMessageField().isDisplayed(),
                "Wrong! 'Hello World!' message is not displayed on the screen!");

        softAssert.assertEquals(dynamicLoadingMainPage.getMessageFieldText(), expectedResult,
                "Error message! This field data is incorrect!!!");

        softAssert.assertAll();
    }

    @Test
    public void testDownloadFile() {

        String navigateUrl = propertyHelper.readProperty("herokuApp.jqueryui.url");
        webDriver.get(navigateUrl);

        JQueryUIMainPage jQueryUIMainPage = new JQueryUIMainPage(webDriver);
        jQueryUIMainPage.clicksOnExcelItem();

        String expectedResult = "menu.xls";

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(jQueryUIMainPage.getDir().exists())
                .as("Error! There is no file in the specified directory!")
                .isTrue();

        softAssertions.assertThat(jQueryUIMainPage.getDir().getName())
                .as("Error! This is an incorrect file name!")
                .isEqualTo(expectedResult);

        softAssertions.assertAll();
    }
}
