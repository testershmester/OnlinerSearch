package by.teachmeskills.steps;

import by.teachmeskills.dto.Characteristics;
import by.teachmeskills.pages.OnlinerSearchPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.After;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Base64;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class OnlinerSearchSteps {

    OnlinerSearchPage searchPage = new OnlinerSearchPage();

    @Given("User is on {string} page")
    public void userIsOnPage(String pageAddress) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        Configuration.browserCapabilities = chromeOptions;
        open(pageAddress);
    }

    @When("User searches for {string}")
    public void userSearchesFor(String searchText) {
        searchPage.searchInCatalog(searchText);
    }

    @Then("{string} is the first search result")
    public void isTheFirstSearchResult(String searchResultTitle) {
        ElementsCollection productTitles = searchPage.getSearchResultProductTitles();
        String actualTitle = productTitles.first().getText();
        assertThat(actualTitle).as("The first search result in incorrect").contains(searchResultTitle);
    }

    @And("The browser is maximized")
    public void theBrowserIsMaximized() {
        getWebDriver().manage().window().maximize();
    }

    @DataTableType
    public Characteristics characteristics(Map<String, String> entry) {
        return new Characteristics(
                entry.get("OS"),
                entry.get("Screen"),
                entry.get("Camera"),
                entry.get("SIM number"));
    }

    @And("Search result contains the next characteristics")
    public void searchResultContainsTheNextCharacteristics(Characteristics characteristics) {
        ElementsCollection productDetails = searchPage.getSearchResultProductDetails();
        String actualDescription = productDetails.first().getText();
        assertThat(actualDescription).as("The OS platform is invalid in description").contains(characteristics.getOs());
        assertThat(actualDescription).as("The Screen platform is invalid in description").contains(characteristics.getScreen());
        assertThat(actualDescription).as("The Camera platform is invalid in description").contains(characteristics.getCamera());
        assertThat(actualDescription).as("The SIM number platform is invalid in description").contains(characteristics.getSimNumber());
    }
}
