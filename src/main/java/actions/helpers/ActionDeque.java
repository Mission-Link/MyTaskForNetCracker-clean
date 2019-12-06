package actions.helpers;

import actions.*;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 *Class ActionDeque encapsulates deque instance that
 * stores actions which require to test
 */
public class ActionDeque {
    private Deque<IAction> actionDeque;

    //constructor
    public ActionDeque() {
        actionDeque = new ArrayDeque<>();
    }

    //methods
    public void putAction(IAction action) {
        actionDeque.add(action);
    }


    public void printDeque() {
        for (IAction tmp : actionDeque) {
//            System.out.println(tmp.getClass().toString());
            if (tmp.getClass().toString().endsWith("OpenURLAct")) {
                OpenURLAct action = (OpenURLAct) tmp;
                System.out.println(action);
            } else if (tmp.getClass().toString().endsWith("ClickAct")) {
                ClickAct action = (ClickAct) tmp;
                System.out.println(action);
            } else if (tmp.getClass().toString().endsWith("SetValueAct")) {
                SetValueAct action = (SetValueAct) tmp;
                System.out.println(action);
            } else if (tmp.getClass().toString().endsWith("ScreenshotAct")) {
                ScreenshotAct action = (ScreenshotAct) tmp;
                System.out.println(action);
            } else {
                System.out.println("Operation not supported yet");
            }
        }//end of for each loop
    }//end of method printDeque()

    public Deque<IAction> getActionDeque() {
        return actionDeque;
    }

    public void execute(){
        for(IAction tmp: actionDeque){
            tmp.run();
        }
    }

}//end of class
