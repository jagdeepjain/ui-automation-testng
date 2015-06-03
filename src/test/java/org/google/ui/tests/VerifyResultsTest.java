/**
 * @author jagdeepjain
 *
 */
package org.google.ui.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.google.ui.pages.GoogleSearchPage;
import org.google.ui.test.common.ScreenShotMaker;
import org.google.ui.test.config.Browser;

/*
 *  Verify that when user googles 'testng' he will get first results has 'TestNG - Welcome'   
 */
public class VerifyResultsTest {
    
    protected WebDriver driver;
    private Browser google = new Browser(driver);
    
    @BeforeClass
    protected void setUp() throws Exception {
        driver = google.getBrowser();
        google.openURL();
    }
    
    @Test
    public void test() throws IOException, AssertionError {
        String expected = "TestNG - Welcome";
        
        GoogleSearchPage ghp = PageFactory.initElements(driver,
                GoogleSearchPage.class);
        
        ghp.searchText("testng");
        List<String> actual = ghp.readSearchResults();
        
        try {
            Assert.assertTrue(expected.equals(actual.get(0))); 
        } catch (AssertionError ae) {
            ScreenShotMaker.getInstance().takeScreenShot(driver,
                    this.getClass().getCanonicalName());
            throw ae;
        }   
    }
    
    @AfterClass
    protected void tearDown() throws Exception {
        driver.quit();
    }
    
}
