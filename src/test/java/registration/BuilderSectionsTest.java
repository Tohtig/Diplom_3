package registration;

import org.junit.jupiter.api.Test;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuilderSectionsTest extends WebDriverParams {
    @Test
    public void jumpToIngredientsSection() {
        open(MainPage.URL);
        MainPage mainPage = new MainPage();
        mainPage.clickFillingHeader();
        assertTrue(mainPage.fillingSectionSelected());
        mainPage.clickSauceHeader();
        assertTrue(mainPage.sauceSectionSelected());
        mainPage.clickBunHeader();
        assertTrue(mainPage.bunSectionSelected());
    }
}
