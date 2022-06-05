package practicum;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.PageObject.MainPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class JumpsInConstructorSectionYandexTest {

    static String select = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
    static String noSelect = "tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect";
    static Browser browser;
    static MainPage mainPage;

    @Parameterized.Parameter
    public String name;

    @Parameterized.Parameter(1)
    public int index;

    @Parameterized.Parameter(2)
    public String expectedBefore;

    @Parameterized.Parameter(3)
    public String expectedAfter;

    @Parameterized.Parameter(4)
    public String screenshotName;

    @Parameterized.Parameters(name = "jump to section {0}")
    public static Object[][] ingredientsData() {
        return new Object[][] {
                {"Булки", 0, select, select, "buns_yandex"},
                {"Соусы", 1, noSelect, select, "sauces_yandex"},
                {"Начинки", 2, noSelect, select, "fillings_yandex"},
        };
    }
    @BeforeClass
    public static void setUp() throws InterruptedException {
       browser = new Browser("yandex");
       mainPage = open(MainPage.pageUrl, MainPage.class);
       Thread.sleep(1000);
    }

    @AfterClass
    public static void closeWindow() {
        browser.tearDown();
    }

    @Test
    public void jumpsYandexTest() throws InterruptedException {
         String before = mainPage
                .checkValue(index);
        String actual = mainPage
                .selectIngredient(index, screenshotName)
                .checkValue(index);
        assertEquals(expectedBefore, before);
        assertEquals(expectedAfter, actual);
    }

}
