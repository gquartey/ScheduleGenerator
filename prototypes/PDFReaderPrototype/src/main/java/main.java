import java.io.IOException;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws IOException {
        //PDFManager test1 = new PDFManager("Resume 2015.pdf");
        //test1.printPages(3,3);

        PDFManager courses = new PDFManager("/Users/gabrielquartey/SwatGithub/group-starfruit/prototypes/PDFReaderPrototype/course_schedule_current.pdf");

        //courses.printPages(124,130);
        String test = courses.returnString(5,6);
        String[] testSplit = test.split("\\s");
        System.out.println(testSplit.length);
        System.out.println(test);
//        int acc = 0;
//        int preReq = 0;
//        for(String i: testSplit){
//            if(i.length() == 5 && i.contains(".")){
//                acc++;
//            }
//            if(i.equals("Prerequisite:")){
//                preReq++;
//            }
//        }
//        System.out.println("Acc = " + acc);
//        System.out.println("Pre reqs = "+ preReq);
//        if(test.contains("Course Credit and Numbering System")){
//            System.out.println("yeeee");
//        }
        //System.out.println(test.length());
        //System.out.println(test.split("\\s").length);

        //Boolean print = false;


    }
}
