package be.mira.jongeren.administration.selenium;

import be.mira.jongeren.administration.Application;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.junit.Assume.assumeTrue;

/**
 * Abstract base class to setup the WebDriver
 * for Selenium Testing. Selenium tests are
 * disabled by default. You can enable them
 * with the -Dselenium flag.
 *
 * Use the driver() method to access the WebDriver
 * in subclasses.
 *
 * Before running the Selenium Tests make sure you have
 * the ChromeDriver correctly installed.
 * Download it from https://chromedriver.storage.googleapis.com/index.html?path=2.25/
 * (Compatible with the current version of Chrome, v54)
 * Extract the chromedriver.exe file into the root of your C:\ drive.
 * The base class expects the Chrome Driver to be at C:\chromedriver.exe
 *
 * Should the test open the browser window but then fail with a connection time out,
 * then check if your version of the Chrome is compatible with the version of chromedriver.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes={Application.class})
@Transactional // Enables rollback after each test.
@ActiveProfiles("development")
public abstract class SeleniumTest {

    private static WebDriver driver;

    private String baseUrl = "http://localhost:8080";

    @Autowired
    private DataSource dataSource;

    @BeforeClass
    public static void setUp(){
        initializeWebDriver();
    }

    @Before
    public void setup(){
        Operation operation = deleteAllFrom("partaking", "event", "person");
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
    }

    private static void initializeWebDriver() {
        driver = new ChromeDriver();
        driver.manage()
                .window()
                .maximize();
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected WebDriver driver() {
        return driver;
    }

    protected DataSource dataSource() {
        return dataSource;
    }

    protected String getBaseUrl() {
        return baseUrl;
    }
}
