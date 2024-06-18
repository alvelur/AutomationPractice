package test;

import Components.ToDoItemComponent;
import Pages.ToDoPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
    }
    @AfterEach
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
    @Test
    void newTodoItemCreation(){
        ToDoPage page = new ToDoPage(driver);
        driver.get("https://todomvc.com/examples/vue/dist/#/");
        page.createNewTodo("This is a Todo");
        Assertions.assertEquals( "This is a Todo", page.getTodoItems().stream()
                .findFirst()
                .map(ToDoItemComponent::getContent)
                .orElseThrow(() -> new NoSuchElementException("No se encontró ningún elemento TODO")));
    }

    @Test
    void complexInteraction() {
        ToDoPage page = new ToDoPage(driver);
        driver.get("https://todomvc.com/examples/vue/dist/#/");

        page.createNewTodo("Active Todo")
                .createNewTodo("Completed Todo")
                .getTodoItems()
                .get(1)
                .toggle()
                .getPage()
                .filterBy(ToDoPage.Filter.COMPLETED)
                .getTodoItems()
                .get(0)
                .destroy()
                .filterBy(ToDoPage.Filter.ACTIVE);

        Assertions.assertEquals(page.getTodoItems()
                .get(0)
                .getContent(), "Active Todo");
    }


}
