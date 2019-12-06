//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//import org.xml.sax.SAXException;
//
//import java.io.IOException;
//
//public class Launcher {
//    private DerpyTester derpyTester = null;
//    private ActionTranslator actionTranslator = null;
//
//    @BeforeTest
//    private void setUpTester() {
//        derpyTester = new DerpyTester();
//        String path = ".\\src\\main\\testfile\\test_scenario.xml";
//        try {
//            actionTranslator = new ActionTranslator(derpyTester, new parser.Parser().parseXML(path));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Test
//    private void testIt() {
////        actionTranslator.executeDeque();
//    }
//
//    @AfterTest
//    private void stopTester() {
//        derpyTester.exitBrowser();
//    }
//
//}
