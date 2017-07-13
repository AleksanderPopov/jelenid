package com.jelenide;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static com.jelenide.ReflectionTools.setFieldValue;
import static com.jelenide.Selectors.byCss;
import static com.jelenide.webdriver.WebDriverRunner.getDriver;

/**
 * Created by Alex on 7/9/2017.
 */
public class Jelenide {
  public static Jelement $(String css) {
    return new DefaultJelement(byCss(css));
  }

  public static Jelement $(By locator) {
    return new DefaultJelement(locator);
  }

  public static Jelement $(WebElement element) {
    return new DefaultJelement(element);
  }

  public static <T extends DefaultJelement> T $(String css, T object) {
    return $(byCss(css), object);
  }

  public static <T extends DefaultJelement> T $(By locator, T object) {
    setFieldValue(object, "locator", locator);
    return object;
  }

  public static Jelements $$(String css) {
    return DefaultJelements.by(byCss(css));
  }

  public static Jelements $$(By locator) {
    return DefaultJelements.by(locator);
  }

  public static Jelements $$(Collection<WebElement> elements) {
    return DefaultJelements.wrap(elements);
  }

  public static <T extends Jelement> Jelements<T> $$(By locator, Class<T> type) {
    return DefaultJelements.typed(locator, type);
  }

  public static FluentWait<WebDriver> Wait() {
    return new FluentWait<WebDriver>(getDriver()).withTimeout(Configuration.timeout, TimeUnit.MILLISECONDS).pollingEvery(Configuration.pollingInterval, TimeUnit.MILLISECONDS);
  }
}