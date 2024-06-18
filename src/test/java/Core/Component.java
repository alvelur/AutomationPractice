package Core;

import org.openqa.selenium.WebElement;

public class Component<P extends Page> extends  Navigable {

    private final P page;
    private final WebElement rootElement;
    public Component(P page, final WebElement rootElement) {
        super(page.getWebDriver());
        this.page = page;
        this.rootElement = rootElement;
    }

    public P getPage(){
        return page;
    }

    protected WebElement getRootElement(){
        return rootElement;
    }

}
