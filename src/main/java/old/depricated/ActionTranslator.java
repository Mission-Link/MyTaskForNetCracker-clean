package old.depricated;

import actions.helpers.ActionDeque;

/**
 *This class is required for executing
 * action deque
 * Translates action subclass instances into DerpyTester instance methods
 */
public class ActionTranslator {
    private ActionDeque actionDeque;
    private DerpyTester derpyTester;

    /**
     * Constructor ActionTranslator
     * @param derpyTester an instance of DerpyTester class that encapsulates WebDriver and
     *                    provides all the core functions
     * @param actionDeque an instance of ActionDeque that contains instances of
     *                    subclasses of class Action
     */
    public ActionTranslator(DerpyTester derpyTester, ActionDeque actionDeque) {
        this.actionDeque = actionDeque;
        this.derpyTester = derpyTester;
    }

    /**
     * Method executes actions that stores in actionDeque
     * Provides a translation between Action object and behavior of derpyTester
     */
//    public void executeDeque() {
//        for (Action tmp : actionDeque.getActionDeque()) {
//            /*
//            takes an object from deque and checks whether it matches to
//            Action subclasses
//
//            if matches then casts to found subclass
//
//            and launches required derpyTester method
//             */
//            if (tmp.getClass().toString().endsWith("OpenURLAct")) {
//                OpenURLAct action = (OpenURLAct) tmp;
//                derpyTester.openURL(action.getUrl());
//            } else if (tmp.getClass().toString().endsWith("ClickAct")) {
//                ClickAct action = (ClickAct) tmp;
//                derpyTester.click(action.getLocatorXpath());
//            } else if (tmp.getClass().toString().endsWith("SetValueAct")) {
//                SetValueAct action = (SetValueAct) tmp;
//                derpyTester.setValue(action.getLocatorXpath(), action.getTextToSet());
//            } else if (tmp.getClass().toString().endsWith("ScreenshotAct")) {
//                ScreenshotAct action = (ScreenshotAct) tmp;
//                derpyTester.takeScreenshot();
//            } else {
////                throw new UnsupportedOperationException("Operation "+tmp.getActionName()+
////                        " not supported yet");
//                System.out.println("Operation " + tmp.getActionName() +
//                        " not supported yet");
//            }
//        }//end of for each loop
//    }//end of method execDeque()


}//end of class
