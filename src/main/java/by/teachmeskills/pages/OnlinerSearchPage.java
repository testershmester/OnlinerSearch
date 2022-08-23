package by.teachmeskills.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.*;

public class OnlinerSearchPage {

    public void searchInCatalog(String searchText) {
        $(".fast-search__input").sendKeys(searchText);
    }

    public ElementsCollection getSearchResultProductTitles() {
        switchToSearchFrame();
        return $$(".result__item_product .product__title .product__title-link");
    }

    public ElementsCollection getSearchResultProductDetails() {
        switchToSearchFrame();
        return $$(".product__details .product__description");
    }

    private void switchToSearchFrame() {
        if (!isInSearchFrame()) {
            switchTo().frame($(".modal-iframe"));
        }
    }

    public boolean isInSearchFrame() {
        String frameUrl = WebDriverRunner.currentFrameUrl();
        return frameUrl.contains("search/iframe");
    }
}
