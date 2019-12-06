package actions;

import strikepackage.Browser;

import java.util.ArrayList;

public class NotSupportedYetAct extends Action implements IAction{
//    private String message;
    private ArrayList<String> parameters;

    public NotSupportedYetAct(ArrayList<String> parameters, Browser browser) {
        super(parameters.get(0), browser);
        this.parameters = parameters;
    }

    @Override
    public void run() {
        try{
            browser.getTest().warning("Received not \'supported yet action\' " +
                    "with parameters\n: "+toString());
            System.out.println(toString());
        }catch (Exception e){
            browser.getTest().fail("Impossible to handle\'supported yet action\' " +
                    "with parameters\n: "+toString());
        }
    }

    @Override
    public String toString() {
        return "NotSupportedYetAct{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                "\n, browser=" + browser.getWebDriver().toString() +
                '}';
    }
}//end of class
