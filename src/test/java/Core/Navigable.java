package Core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public abstract class Navigable {
    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;
    private final static Duration defaultWait = Duration.ofSeconds(10L);

    public Navigable(final WebDriver webDriver){
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver,defaultWait);
    }

    public WebDriver getWebDriver(){
        return webDriver;
    }

    protected <V> V waitFor(Function<? super WebDriver, V> isTrue){
        return webDriverWait.until(isTrue);
    }

    public WebElement findElement(By by){
        return getWebDriver().findElement(by);
    }

    public List<WebElement> findElements(By by){
        return getWebDriver().findElements(by);
    }

}
