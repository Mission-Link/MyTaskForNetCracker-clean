package actions.helpers;

import actions.*;
import logger.SimpleLogger;
import strikepackage.Browser;

import java.util.ArrayList;

/**
 * This class uses by class parser.Parser to retrieve an IAction obj
 * from ArrayList<String> instance that stores action tags
 */
public class ActionFactory {

    private static final String OPEN_URL = "openurl";
    private static final String CLICK = "click";
    private static final String SET_VALUE = "setvalue";
    private static final String SCREENSHOT = "screenshot";
    private static final String MAXIMIZE_WINDOW = "maximizewindow";

    private Browser browser;

    public ActionFactory(Browser browser) {
        this.browser = browser;
    }

    /**
     * Creates an instance of IAction subclass
     *
     * @param arrayList stores action parameters
     * @return IAction subclass object
     */
    public IAction createAction(ArrayList<String> arrayList, SimpleLogger simpleLogger) {
        String actionName = arrayList.get(0);
        System.out.println("Action factory received actionName: "+actionName);

        switch (actionName.toLowerCase()) {
            case OPEN_URL:
                String url = arrayList.get(1);
          return new OpenURLAct(url, browser);
            case CLICK:
                String xpath = arrayList.get(1);
                return new ClickAct(xpath, browser);
            case SET_VALUE:
                String xpath2 = arrayList.get(1);
                String textToSet = arrayList.get(2);
                return new SetValueAct(xpath2, textToSet, browser);
            case SCREENSHOT:
                return new ScreenshotAct(browser);
            case MAXIMIZE_WINDOW:
                return new MaximizeWindowAct(browser);
            default:
                NotSupportedYetAct act = new NotSupportedYetAct(arrayList, browser);
                ArrayList<String> arrayList2 = new ArrayList<>();
                arrayList2.add(act.toString());
                simpleLogger.writeLog(arrayList2);
                return act;
        }//end of switch
    }//end of method

}//end of class

