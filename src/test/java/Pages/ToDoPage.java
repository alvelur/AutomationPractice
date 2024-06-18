package Pages;

import Components.ToDoItemComponent;
import Core.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ToDoPage extends Page {

    private static final By selectAllLabel = By.id("toggle-all-input");
    private static final By newTodoInbox = By.className("new-todo");
    private static final By toDoItems = By.className("todo-list");
    private static final By item = By.className("view");

    private static final By clearCompletedButton = By.className("clear-completed");
    private static final By toDoCountSpan = By.className("todo-count");

    public enum Filter{
        ALL(By.linkText("All")),
        ACTIVE(By.linkText("Active")),
        COMPLETED(By.linkText("Completed"));

        private final By selector;

        Filter(By selector) {
            this.selector = selector;
        }
    }
    public ToDoPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ToDoPage toggleSelectAll(){
        waitFor(ExpectedConditions.presenceOfElementLocated(selectAllLabel)).click();
        return this;
    }

    public ToDoPage createNewTodo(String toDoItem){
        waitFor(ExpectedConditions.presenceOfElementLocated(newTodoInbox)).sendKeys(toDoItem, Keys.ENTER);
        return this;
    }

    public ToDoPage filterBy(Filter filter){
        waitFor(ExpectedConditions.elementToBeClickable(filter.selector)).click();
        return this;
    }

    public ToDoPage clear(Filter filter){
        waitFor(ExpectedConditions.elementToBeClickable(clearCompletedButton)).click();
        return this;
    }

    public List<ToDoItemComponent<ToDoPage>> getTodoItems(){
        return hasMany(ToDoItemComponent::new, waitFor(ExpectedConditions.presenceOfNestedElementsLocatedBy(toDoItems, item)));
    }

}
