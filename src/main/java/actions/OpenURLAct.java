package actions;

import strikepackage.Browser;

public class OpenURLAct extends Action implements IAction{
    private String url;

    public OpenURLAct(String url, Browser browser) {
        super("openurl", browser);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "OpenURLAct{" +
                "url='" + url + '\'' +
                '}';
    }

    public void run() {
        try{
            browser.getWebDriver().get(url);
            browser.getTest().pass("Navigated to \""+url+"\"");
        }catch (Exception e){
            browser.getTest().fail("Impossible to navigated to \""+url+"\"");
        }
    }

}
