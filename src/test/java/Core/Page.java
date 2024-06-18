package Core;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public class Page extends Navigable{
    public Page(WebDriver webDriver) {
        super(webDriver);
    }

    protected <P extends Page, C extends Component<P>> C hasA(
        final BiFunction<P, WebElement, C> componentFactory,
        final WebElement rootElement){
        return componentFactory.apply((P) this, rootElement);
    }

    protected <P extends Page, C extends Component<P>> List<C> hasMany(
            final BiFunction<P, WebElement, C> componentFactory,
            final List<WebElement> rootElements){
        if(rootElements != null){
            return rootElements.stream()
                    .map(element -> hasA(componentFactory, element))
                    .toList();
        }
        return Collections.emptyList();
    }

}
