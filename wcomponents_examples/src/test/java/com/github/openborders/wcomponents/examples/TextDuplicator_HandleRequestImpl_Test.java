package com.github.openborders.wcomponents.examples; 

import com.github.openborders.wcomponents.examples.TextDuplicator_HandleRequestImpl;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.github.openborders.wcomponents.Container;

import com.github.openborders.wcomponents.test.selenium.MultiBrowserRunner;
import com.github.openborders.wcomponents.test.selenium.WComponentSeleniumTestCase;

/**
 * Selenium unit tests for {@link TextDuplicator_HandleRequestImpl}.
 * 
 * @author Yiannis Paschalidis 
 * @since 1.0.0
 */
@Category(SeleniumTests.class)
@RunWith(MultiBrowserRunner.class)
public class TextDuplicator_HandleRequestImpl_Test extends WComponentSeleniumTestCase
{
    /** Creates a TextDuplicator_Test_SeleniumImpl. */
    public TextDuplicator_HandleRequestImpl_Test()
    {
        super(new TextDuplicator_HandleRequestImpl());
    }

    @Test
    public void testDuplicator()
    {
        // Launch the web browser to the LDE
        WebDriver driver = getDriver();

        // Enter some text and use the duplicate button
        String inputFieldName = ((Container) getUi()).getChildAt(1).getId();
        driver.findElement(By.name(inputFieldName)).sendKeys("dummy");
        driver.findElement(By.xpath("//button[text()='Duplicate']")).click();
        Assert.assertEquals("Incorrect text field text after duplicate", "dummydummy", 
                     driver.findElement(By.xpath("//input[@type='text']")).getAttribute("value"));
        
        // Clear the text
        driver.findElement(By.xpath("//button[text()='Clear']")).click();        
        Assert.assertEquals("Incorrect text field text after clear", "", 
                     driver.findElement(By.xpath("//input[@type='text']")).getAttribute("value"));
    }
}