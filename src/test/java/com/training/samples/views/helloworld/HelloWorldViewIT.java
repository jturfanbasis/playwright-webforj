package com.training.samples.views.helloworld;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.training.samples.views.BaseTest;

public class HelloWorldViewIT extends BaseTest {

    @BeforeEach
    public void setupHelloWorld() {
        navigateToRoute("helloworld");
    }

    @Test
    public void testHelloWorld() {
        Locator nameInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What is your name?"));
        nameInput.fill("John Doe");

        Locator helloButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Say Hello").setExact(false));
        helloButton.click();

        Locator toast = page.getByText("Welcome to webforJ Starter John Doe!",
                new Page.GetByTextOptions().setExact(true));

        assertThat(toast).isVisible();
    }
}
