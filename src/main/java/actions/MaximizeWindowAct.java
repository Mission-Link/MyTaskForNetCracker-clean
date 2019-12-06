package actions;

import strikepackage.Browser;

public class MaximizeWindowAct extends Action implements IAction{
    public MaximizeWindowAct(Browser browser) {
        super("maximizewindow", browser);
    }

    @Override
    public void run() {
        try{
            browser.getWebDriver().manage().window().maximize();
            browser.getTest().pass("Browser window maximized successfully");
        }catch (Exception e){
            browser.getTest().fail("Impossible to maximize the window of a browser");
        }

    }
}
