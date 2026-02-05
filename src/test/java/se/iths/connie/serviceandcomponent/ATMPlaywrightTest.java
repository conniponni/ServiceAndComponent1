package se.iths.connie.serviceandcomponent;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ATMPlaywrightTest {

    static Playwright playwright;
    static Browser browser;
    static Page page;

    @BeforeAll
    static void setup() throws InterruptedException {

        Thread serverThread =
                new Thread(() ->
                        SpringApplication.run(
                                AtmApp.class));

        serverThread.setDaemon(true);

        serverThread.start();

        Thread.sleep(5000);

        playwright = Playwright.create();

        browser =
                playwright.chromium().launch(
                        new BrowserType
                                .LaunchOptions()
                                .setHeadless(true)
                );

        page = browser.newPage();

    }

    @AfterAll
    static void teardown() {
        browser.close();
        playwright.close();
    }

    @Test
    void pageLoads() {
        page.navigate("http://localhost:8080");

        assertEquals(
                "ATM Balance",
                page.title());
    }

    @Test
    void balanceVisible() {
        page.navigate("http://localhost:8080");
        String balance =
                page.locator("#balance")
                        .innerText();

        assertEquals("0", balance);
    }
}
