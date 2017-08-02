package net.thucydides.junit.integration;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;
import net.thucydides.samples.DemoSiteSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

/**
 * This is a very simple scenario of testing a single page.
 * @author johnsmart
 *
 */
@RunWith(ThucydidesRunner.class)
public class WhenOpeningStaticDemoPageWithSamePage {

    @Managed(driver = "chrome", options = "--headless")
    public WebDriver webdriver;

    @ManagedPages
    public Pages pages;
    
    @Steps
    public DemoSiteSteps steps;
        
    @Test
    @Title("The user opens the index page")
    public void the_user_opens_the_page() {
        steps.opensPage();
        steps.should_display("A visible title");
    }    
    
    @Test
    @Title("The user selects a value")
    public void the_user_selects_a_value() {
        steps.opensPage();
        steps.enter_values("Label 2", true);
        steps.onSamePage(DemoSiteSteps.class).should_have_selected_value("2");
    }

    @Test
    @Title("The user enters different values.")
    public void the_user_opens_another_page() {
        steps.opensPage();
        steps.enter_values("Label 3", true);
        steps.onSamePage(DemoSiteSteps.class).do_something();
        steps.onSamePage(DemoSiteSteps.class).should_have_selected_value("3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_correct_exception() {
        steps.opensPage();
        steps.throw_exception();
    }
}
