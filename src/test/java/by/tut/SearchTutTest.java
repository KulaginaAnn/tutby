package by.tut;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Ann on 14.11.18
 */


/**test tut.by search job*/
public class SearchTutTest {

    private static  Logger log ;
    private static WebDriver driver;

    private static final String TAB = "Работа";
    private static final String TEST_QUERY = "QA Automation Engineer";//input data

    private static String inputVacancyClassName = "bloko-control-group__main";
    private static String buttonSearchVacancyCssSelector= "button.bloko-button.bloko-button_primary.bloko-button_stretched";
    private static String divFoundVacanccyClassName = "search-item-name";



    /**initialization driver */
    @BeforeTest
    public static void setUp() {
        File file = new File("C:/selenium/chromedriver_win32/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();//initialization driver
        driver.manage().window().maximize();
        driver.get("https://www.tut.by/");
        log = Logger.getLogger("new logger");
    }


    @Test
    public void searchJobOnTutby() {

        log.info("start searchJob");
        searchTabJob();
        log.info("end searchJob");
        log.info("start input data");
        inputSearchVacancyName();
        log.info("end input data");
        log.info("start click button");
        clickButtonToSearchVacancy();
        log.info("end click button");
        log.info("start check");
        matchCheckVacancy();
        log.info("end check");
    }

    /**
     * search and click job
     */
    public void searchTabJob() {
        log.info("Step 1: Move to “Работа” category");
        WebElement tabJob = driver.findElement(By.linkText(TAB));
        tabJob.click();
    }

    /**
     * input data into input
     */
    public void inputSearchVacancyName() {
        log.info("Step 2: Entering text data into input for job search ");
        WebElement inputVacancy = driver.findElement(By.className (inputVacancyClassName));
        Actions actions = new Actions(driver);
        actions.moveToElement(inputVacancy);
        actions.click();
        actions.sendKeys(TEST_QUERY);
        actions.build().perform();
    }

    /**click button*/

    public void clickButtonToSearchVacancy() {
        log.info("Step 3: Click button job search ");

        WebElement buttonSearchVacancy = driver.findElement(By.cssSelector(buttonSearchVacancyCssSelector));
        buttonSearchVacancy.click();
    }

    /**check matches whith input data*/

    public void matchCheckVacancy() {
        log.info("Step 4: match check ");
        List<WebElement> listDivFoundVacancy = driver.findElements(By.className(divFoundVacanccyClassName));
        System.out.println("Всего записей: " + listDivFoundVacancy.size());
        Assert.assertNotNull(listDivFoundVacancy);
            for (WebElement list : listDivFoundVacancy) {
                Assert.assertEquals(list.getText(), TEST_QUERY);
                Assert.fail("there is a mismatch");
            }
    }


        /**close*/
        @AfterClass
        public static void teardown() {
            driver.quit();

        }



}
