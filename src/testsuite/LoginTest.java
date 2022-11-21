package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class LoginTest extends Utility {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }

    public String getTextFromElement(By by){
        WebElement actualTextMessageElement = driver.findElement(by);
        return actualTextMessageElement.getText();
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials(){
        // Send Username to username field
        sendTextToElement(By.id("username"),"tomsmith");
        //Send password to password field
        sendTextToElement(By.name("password"),"SuperSecretPassword!");
        // Click on login button using x-path
        clickOnElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));

        // Verify the text ‘Secure Area text’
        String expectedMessage = "Secure Area";
        // Storing message in to variable as String data type also used x-path to locate the element
        String actualMessage = getTextFromElement(By.xpath("//h2[text()=' Secure Area']"));

        // Validate actual and expected message
        Assert.assertEquals("No Secure Area text found",expectedMessage, actualMessage);

    }

    @Test
    public void verifyTheUsernameErrorMessage(){
        // Send Username to username field
        sendTextToElement(By.id("username"),"tomsmith1");
        //Send password to password field
        sendTextToElement(By.name("password"),"SuperSecretPassword!");
        // Click on login button using x-path
        clickOnElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));

        // Verify the text ‘Secure Area text’
        String expectedMessage = "Your username is invalid!\n"+"×";
        // Storing message in to variable as String data type also used x-path to locate the element
        String actualMessage = getTextFromElement(By.xpath("//div[@id='flash']"));

        // Validate actual and expected message
        Assert.assertEquals("Your username is invalid!",expectedMessage, actualMessage);
    }

    @Test
    public void verifyThePasswordErrorMessage(){
        // Send Username to username field
        sendTextToElement(By.id("username"),"tomsmith");
        //Send password to password field
        sendTextToElement(By.name("password"),"SuperSecretPassword");
        // Click on login button using x-path
        clickOnElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));

        // Verify the text ‘Secure Area text’
        String expectedMessage = "Your password is invalid!\n"+"×";
        // Storing message in to variable as String data type also used x-path to locate the element
        String actualMessage = getTextFromElement(By.xpath("//div[@id='flash']"));

        // Validate actual and expected message
        Assert.assertEquals("Your password is invalid!",expectedMessage, actualMessage);
    }

    @After
    public void testDown(){
        closeBrowser();
    }

}