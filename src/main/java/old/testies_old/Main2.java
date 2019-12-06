//package old.testies_old;
//
//import java.util.Queue;
//
//public class Main2 {
//    public static void main(String[] args) {
//        String url = "https://google.com";
//        String click = "//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input";
//        String setV1 = "//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input";
//        String setV2 = "geeks for geeks";
//        String clickSearchButtn = "//*[@id=\"tsf\"]/div[2]/div[1]/div[2]/div[2]/div[2]/center/input[1]"; //
//        //*[@id="tsf"]/div[2]/div[1]/div[2]/div[2]/div[2]/center/input[1]
//        String firstResult = "//*[@id=\"rso\"]/div[1]/div/div/div/div[1]/a/h3";
//        String firstResult2 = "//*[@id=\"rso\"]/div[1]/div/div/div/div/div[1]/a/div/cite";
//
//        /*
//        new
//         */
//
////        Queue<DerpyTester> derpyTesterQueue = null;
////
////        /*
////        new end
////         */
////
////        DerpyTester derpyTester = new DerpyTester();
////
////        try {
////            derpyTester.openURL(url);
////            derpyTester.maximizeWindow();
////            derpyTester.click(click);
////            derpyTester.setValue(setV1, setV2);
////            derpyTester.click(clickSearchButtn);
////            derpyTester.click(firstResult2);
////            derpyTester.takeScreenshot();
////        }catch (Exception e){
////            System.out.println(e.getMessage());
////        }finally {
////            try {
////                Thread.sleep(3000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////            derpyTester.exitBrowser();
////        }
//    }
//}
