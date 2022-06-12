package practicum;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.WebDriverWait;
import practicum.pageobject.MainPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class JumpsInConstructorSectionTest {

    static Browser browser;
    static String select = "current__2BEPc";
    static String google = "google";
    static WebDriverWait wait;
    static MainPage mainPage;

    @Parameterized.Parameter
    public String name;

    @Parameterized.Parameter(1)
    public int index;

    @Parameterized.Parameter(2)
    public String expectedValue;

    @Parameterized.Parameters(name = "jump to section {0}")
    public static Object[][] ingredientsData() {
        return new Object[][]{
                {"Соусы", 1, select},
                {"Начинки", 2, select},
                {"Булки", 0, select},
        };
    }

    @BeforeClass
    public static void setUp() {
        browser = new Browser(google);
        wait = browser.getWaitObject();
        mainPage = open(MainPage.pageUrl, MainPage.class);
    }

    @AfterClass
    public static void closeWindow() {
        browser.tearDown();
    }

    @Test
    public void jumpsGoogleTest() {
        String actual = mainPage
                .jumpsInSection(index, expectedValue, wait, name);
        assertTrue(actual.contains(expectedValue));
    }

}
