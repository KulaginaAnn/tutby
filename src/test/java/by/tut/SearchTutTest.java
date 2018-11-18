package by.tut;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ann on 14.11.18
 */


/**test tut.by search job*/
public class SearchTutTest {

    private static final String testQuery = "QA automation engineer";//input data
    public static WebDriver driver;


    /**initialization driver */
    @BeforeClass
    public static void setUp() {
        File file = new File("C:/selenium/chromedriver_win32/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();//initialization driver
        driver.manage().window().maximize();
        driver.get("https://www.tut.by/");
    }

    /**
     * search and click job
     */
    @Test(priority = 0)
    public void searchJob() {
        WebElement searchJob = driver.findElement(By.linkText("Работа"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        searchJob.click();
    }

    /**
     * input data into input
     */
    @Test(priority = 1)
    public void inputData() {
        WebElement input = driver.findElement(By.cssSelector(" div.bloko-control-group__main"));

        Actions actions = new Actions(driver);
        actions.moveToElement(input);
        actions.click();
        actions.sendKeys(testQuery);
        actions.build().perform();
    }

    /**click button*/
    @Test(priority = 2)
    public void clickButton() {
        WebElement button = driver.findElement(By.cssSelector(" button.bloko-button.bloko-button_primary.bloko-button_stretched"));

        Actions action = new Actions(driver);
        action.moveToElement(button);
        action.click();
        action.build().perform();
    }

    /**check matches whith input data*/
    @Test(priority = 3)
    public void vac() {
        int numberOfMatches = 0;
        int numberOfNotMatches = 0;

        List<WebElement> list1 = (List<WebElement>) driver.findElements(By.cssSelector("div.search-item-name "));

        if (list1.size() > 0) {
            for (WebElement list : list1) {

                try {
                    list.findElement(By.partialLinkText("QA"));
                    numberOfMatches++;

                } catch (NoSuchElementException e) {
                    numberOfNotMatches++;
                }

            }
        }
        System.out.println("Всего записей: " + list1.size());
        System.out.println("количество совпадений: " + numberOfMatches);
        System.out.println("не удовлетворяет поиску: " + numberOfNotMatches);
    }


        /**close*/
        @AfterClass
        public static void closed() {
            driver.quit();

        }


}
