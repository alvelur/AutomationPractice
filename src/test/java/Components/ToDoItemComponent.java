package Components;

import Core.Component;
import Core.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ToDoItemComponent<P extends Page> extends Component<P> {

    private static final By toggleInput = By.className("toggle");
    private static final By contentLabel = By.tagName("label");
    private static final By destroyButton = By.tagName("button");

    public ToDoItemComponent(P page, WebElement rootElement) {
        super(page, rootElement);
    }

    public ToDoItemComponent<P> toggle(){
        waitFor(ExpectedConditions.presenceOfNestedElementLocatedBy(getRootElement(),toggleInput)).click();
        return this;
    }
    public String getContent() {
        return waitFor(ExpectedConditions.presenceOfNestedElementLocatedBy(getRootElement(),contentLabel)).getText();
    }

    public P destroy(){
        new Actions(getWebDriver())
                .moveToElement(getRootElement())
                .click(waitFor(ExpectedConditions.presenceOfNestedElementLocatedBy(getRootElement(),destroyButton)))
                .build()
                .perform();
        return getPage();
    }
}
