package tests;

import edu.swarthmore.cs71.starfruit.classes.*;
import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.departments.Department;
import edu.swarthmore.cs71.starfruit.classes.requirements.*;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class ModelTests {

    @Test
    public void getSolutionsForHarderMajor(){
        DeptLib deptLib = DeptLib.getInstance();
        Department major = deptLib.getCourseMapping("CPSC");

        //getting the solution set we use -> know its the same because we sort it
        Collection<Solution> toTest = major.getSolutionsMajor(new ArrayList<>());
        for (Solution s: toTest){
            for (CourseCode c:s.getCourses()){
                System.out.println(c.getStringCourseCode());
            }
            break;
        }
        Assert.assertEquals(19630,toTest.size()); // we know we need 20 classes to get CPSC major
    }

    @Test
    public void Calendar() {
        LocalDate localDate = new LocalDate();

        DateTime dateTime = new DateTime();
        dateTime.withYear(2017);

    }

    // TODO move to middleman to use Catalog object
//    @Test
//    public void SimpleFourYear() {
//        DeptLib deptLib = DeptLib.getInstance();
//        HashMap<Department, Collection<Course>> allCourses = new HashMap<>();
//
//
//        ArrayList<String> prereq = new ArrayList<>();
//        Collection<Course> repeats = new ArrayList<>();
//        repeats.add(new Course("Intro", deptLib.getCourseMapping("ECON"), 1, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("Micro", deptLib.getCourseMapping("ECON"), 11, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("Financial", deptLib.getCourseMapping("ECON"), 22, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("I Econometrics", deptLib.getCourseMapping("ECON"), 31, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("Financial AC", deptLib.getCourseMapping("ECON"), 33, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("Law and Economics", deptLib.getCourseMapping("ECON"), 42, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("Global cap", deptLib.getCourseMapping("ECON"), 54, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("Behavioral", deptLib.getCourseMapping("ECON"), 55, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("Industrial", deptLib.getCourseMapping("ECON"), 6, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("REG", deptLib.getCourseMapping("ECON"), 73, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("Health", deptLib.getCourseMapping("ECON"), 75, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("Environ", deptLib.getCourseMapping("ECON"), 76, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("Adv Macro", deptLib.getCourseMapping("ECON"), 102, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("Behavioral exp", deptLib.getCourseMapping("ECON"), 155, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("Environm sem", deptLib.getCourseMapping("ECON"), 176, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("Econ Dev", deptLib.getCourseMapping("ECON"), 181, false, false, false, new ArrayList<>()));
//        repeats.add(new Course("Macro", deptLib.getCourseMapping("ECON"), 21, false, false, false, new ArrayList<>()));
//
//        Collection<Course> stats= new ArrayList<>();
//        stats.add(new Course("Stat", deptLib.getCourseMapping("STAT"), 111, false, false, false, new ArrayList<>()));
//
//
//        //Symbolizes other classes (that can be made as of now) outside major
//        Collection<Course> otherCourses = new ArrayList<>();
//        for(int i = 1; i < 28; i++){
//            otherCourses.add(new Course("W"+Integer.toString(i), deptLib.getCourseMapping("ENGR"), i, false, false, false, prereq));
//        }
//
//        allCourses.put(deptLib.getCourseMapping("ECON"), repeats);
//        allCourses.put(deptLib.getCourseMapping("ENGR"), otherCourses);
//        allCourses.put(deptLib.getCourseMapping("STAT"), stats);
//
//
//        UserData userData = new UserData();
//        userData.setMajor1("ECON");
//        userData.setClassYear(2021);
//
//        Collection<Course> hist = new ArrayList<>();
//        User user = new User(userData);
//        History history = new History(hist);
//        userData.setAcademicHistory(history);
//        Major m = user.getPrimeMajor();
//
//        //getting the solution set we use -> know its the same because we sort it
//        Collection<Solution> toTest = user.getPrimeMajor().getClassesNeeded(new ArrayList<>());
//        Collection<CourseCode> ourReqs = new ArrayList<>();
//
//        ourReqs = toTest.iterator().next().getCourses();
//
//
//        Collection<Integer> alreadySeen = new ArrayList<>();
//
//        HashMap<Department, Collection<Course>> map = allCourses;
//        ScheduleGeneratorModel model = new ScheduleGeneratorModel(userData, allCourses, map);
//
//        Collection<SemesterSchedule> plan = model.getFullPlan();
//
//        int counter = 1;
//        int majorClassesTaken= 0;
//        for(SemesterSchedule semSched: plan){
//            for (ScheduledCourse j : semSched.getSchedule()) {
//                // Just to check to see if our First solution was satisfied  --> we always look at first option 1st
//                if(j.getSchedDept().getFourLetter().equals("ECON")){
//                    for(CourseCode code: ourReqs){
//                        //in case of double counting class
//                        if(j.getCourseNum().equals(code.getCourseNumber()) && !alreadySeen.contains(j.getCourseNum())){
//                            majorClassesTaken++;
//                            alreadySeen.add(j.getCourseNum());
//                        }
//                    }
//                }
//                System.out.println(counter + ") " + j.getCourseName());
//            }
//            counter++;
//            System.out.println("***************New Semester*******************");
//        }
//
//        Assert.assertEquals(8,majorClassesTaken); // we know we need 8 classes to get ECON major
//
//    }

    @Test
    public void GetSolutionsForUser(){

        UserData survey = new UserData();
        survey.setMajor1("ECON");
        User user = new User(survey);
        Major major = user.getPrimeMajor();
        Collection<Solution> classes = major.getClassesNeeded(new ArrayList<>());
    }


    @Test
    public void AndRequirementWithRepeatReqTest(){
        Collection<DegreeRequirement> dummyMajor = new ArrayList<>();

        Collection<CourseCode> g1 = new ArrayList<>();
        for(int i = 0; i<6; i++){
            g1.add(new CourseCode("CS",i));
        }
        DegreeRequirement one = new OneOfRequirement(g1);

        DegreeRequirement c1 = new CourseRequirement(new CourseCode("CS", 21));
        DegreeRequirement c2 = new CourseRequirement(new CourseCode("CS", 31));
        DegreeRequirement c3 = new CourseRequirement(new CourseCode("CS", 35));

        Collection<CourseCode> group = new ArrayList<>();
        for(int i = 0; i<6; i++){
            group.add(new CourseCode("CS",i+10));
        }

        DegreeRequirement group1 = new OneOfRequirement(group);
        DegreeRequirement repeat = new RepeatedRequirement(3,group1);

        dummyMajor.add(one);
        dummyMajor.add(c1);
        dummyMajor.add(c2);
        dummyMajor.add(c3);
        dummyMajor.add(repeat);

        DegreeRequirement and = new AndRequirement(dummyMajor);

        Collection<Solution> solutions = and.getSolutionSet(new ArrayList<>());
//
//        for(Solution s: solutions){
//            for (CourseCode courseCode: s.getCourses()){
//                System.out.println(courseCode.getStringCourseCode());
//            }
//            System.out.println("??????????????????????????????");
//        }

        org.junit.Assert.assertEquals(120,solutions.size());
    }

    @Test
    public void RepeatReq(){
        Collection<CourseCode> g1 = new ArrayList<>();
        for(int i = 0; i<10; i++){
            g1.add(new CourseCode("A",i));
        }
        DegreeRequirement one = new OneOfRequirement(g1);
        DegreeRequirement repeats = new RepeatedRequirement(5,one);

        Collection<Solution> result = repeats.getSolutionSet(new ArrayList<>());

        org.junit.Assert.assertEquals(252,result.size());
    }

    @Test
    public void AndRequirementRepeatingCommonCourseTest(){
        Collection<DegreeRequirement> dummyMajor = new ArrayList<>();

        Collection<CourseCode> g1 = new ArrayList<>();
        for(int i = 0; i<6; i++){
            g1.add(new CourseCode("CS",i));
        }
       CourseCode cs31 = new CourseCode("CS", 31);
        g1.add(cs31);
        DegreeRequirement one = new OneOfRequirement(g1);

        DegreeRequirement c1 = new CourseRequirement(new CourseCode("CS", 21));
        DegreeRequirement c2 = new CourseRequirement(cs31);
        DegreeRequirement c3 = new CourseRequirement(new CourseCode("CS", 35));

        dummyMajor.add(one);
        dummyMajor.add(c1);
        dummyMajor.add(c2);
        dummyMajor.add(c3);

        DegreeRequirement and = new AndRequirement(dummyMajor);

        Collection<Solution> solutions = and.getSolutionSet(new ArrayList<>());

        org.junit.Assert.assertEquals(6,solutions.size());

    }

    @Test
    public void AndRequirementSimpleTest(){
        Collection<DegreeRequirement> dummyMajor = new ArrayList<>();

        Collection<CourseCode> g1 = new ArrayList<>();
        for(int i = 0; i<6; i++){
            g1.add(new CourseCode("CS",i));
        }
        DegreeRequirement one = new OneOfRequirement(g1);

        DegreeRequirement c1 = new CourseRequirement(new CourseCode("CS", 21));
        DegreeRequirement c2 = new CourseRequirement(new CourseCode("CS", 31));
        DegreeRequirement c3 = new CourseRequirement(new CourseCode("CS", 35));

        dummyMajor.add(one);
        dummyMajor.add(c1);
        dummyMajor.add(c2);
        dummyMajor.add(c3);

        DegreeRequirement and = new AndRequirement(dummyMajor);

        Collection<Solution> solutions = and.getSolutionSet(new ArrayList<>());

        org.junit.Assert.assertEquals(6,solutions.size());
    }

    /*

    @Test
    public void MapperChecker() {
        //can't really test until we set up catalog
        Mapper mapper = new Mapper();

    }
*/

    @Test
    public void NumChecker() {
        DeptLib deptLib = DeptLib.getInstance();

        UserData userData = new UserData();
        HashMap<Department, Collection<Course>> map = new HashMap<>();
        userData.setMajor1("ECON");
        userData.setClassYear(2019);
        Collection<Course> hist = new ArrayList<>();
        History history = new History(hist);
        history.addToHistory(new Course("Econ1", deptLib.getCourseMapping("ECON"), 001, false, false, false, new ArrayList<>()));
        userData.setAcademicHistory(history);
        ScheduleGeneratorModel generator = new ScheduleGeneratorModel(userData, map, map);

        Major major = new Major(deptLib.getCourseMapping("ECON"));


        org.junit.Assert.assertEquals(generator.findNumCoursesMajor(7), 3 );

    }



    @Test
    public void testRandom(){
        DeptLib deptLib = DeptLib.getInstance();

        UserData userData = new UserData();
        HashMap<Department, Collection<Course>> map = new HashMap<>();
        userData.setMajor1("ECON");
        userData.setClassYear(2019);
        Collection<Course> hist = new ArrayList<>();
        History history = new History(hist);
        userData.setAcademicHistory(history);
        history.addToHistory(new Course("Econ1", deptLib.getCourseMapping("ECON"), 001, false, false, false, new ArrayList<>()));
        Collection<Department> collection = new ArrayList<>();
        ScheduleGeneratorModel model = new ScheduleGeneratorModel(userData, map, map);

        Department dep1 = model.getRandomDepartment(deptLib.getAllDepartments());

        Department dep2 = model.getRandomDepartment(deptLib.getAllDepartments());
        //odds that this happens twice is very unlikely
        if(dep2.equals(dep1)){
            dep2 = model.getRandomDepartment(deptLib.getAllDepartments());
        }
        Assert.assertEquals(dep1.equals(dep2), false);

    }

    // TODO move to middleman
    //    @Test
//    public void testScheduleGenSimpleGreedy() {
//        DeptLib deptLib = DeptLib.getInstance();
//        HashMap<Department, Collection<Course>> allCourses = new HashMap<>();
//
//
//        ArrayList<Course> egnrCourses = new ArrayList<>();
//        ArrayList<String> prereq = new ArrayList<>();
//        egnrCourses.add(new Course("Econ1", deptLib.getCourseMapping("ECON"), 1, false, false, false, prereq));
//        egnrCourses.add(new Course("Econ2", deptLib.getCourseMapping("ECON"), 11, false, false, false, prereq));
//        egnrCourses.add(new Course("Econ3", deptLib.getCourseMapping("ECON"), 21, false, false, false, prereq));
//        egnrCourses.add(new Course("Econ4", deptLib.getCourseMapping("ECON"), 31, false, false, false, prereq));
//        egnrCourses.add(new Course("Econ5", deptLib.getCourseMapping("ECON"), 76, false, false, false, prereq));
//
//        Collection<Course> otherCourses = new ArrayList<>();
//        for(int i = 1; i < 4; i++){
//            otherCourses.add(new Course("W"+Integer.toString(i), deptLib.getCourseMapping("ENGR"), i, false, false, false, prereq));
//        }
//
//        allCourses.put(deptLib.getCourseMapping("ECON"), egnrCourses);
//        allCourses.put(deptLib.getCourseMapping("ENGR"), otherCourses);
//
//
//        UserData userData = new UserData();
//        userData.setMajor1("ECON");
//        userData.setClassYear(2018);
//
//        Collection<Course> hist = new ArrayList<>();
//        User user = new User(userData);
//        History history = new History(hist);
//        userData.setAcademicHistory(history);
//        Major m = user.getPrimeMajor();
//
//       // history.addToHistory(new Course("C1", deptLib.getCourseMapping("SPAN"), 011, false, true, false, new ArrayList<>()));
//
//
//        ScheduleGeneratorModel model = new ScheduleGeneratorModel(userData, allCourses, allCourses);
//        model.generateSchedule();
//
//        SemesterSchedule sem = model.getSchedule();
//
//
//        ArrayList<ScheduledCourse> scheduledChecker = new ArrayList<>();
//
//
//        for (Course i : egnrCourses) {
//            scheduledChecker.add(new ScheduledCourse(i));
//        }
//
//        int j = 0;
//
//        for (ScheduledCourse x : sem.getSchedule()) {
//            Assert.assertEquals(scheduledChecker.get(j).getCourseName(), x.getCourseName());
//            j++;
//        }
//    }



    @Test
    public void AbleToGenerateModel() {
        DeptLib deptLib = DeptLib.getInstance();
        DistributionMapping disMap;
        HashMap<Department, Collection<Course>> allCourses = new HashMap<>();
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("W1", deptLib.getCourseMapping("ENGR"), 001, false, false, false, new ArrayList<>()));
        courses.add(new Course("W2", deptLib.getCourseMapping("ENGR"), 002, false, false, false, new ArrayList<>()));
        courses.add(new Course("W3", deptLib.getCourseMapping("ENGR"), 003, false, false, false, new ArrayList<>()));
        courses.add(new Course("W4", deptLib.getCourseMapping("ENGR"), 004, false, false, false, new ArrayList<>()));

        allCourses.put(deptLib.getCourseMapping("ENGR"), courses);

        UserData userData = new UserData();
        userData.setMajor1("ECON");
        userData.setClassYear(2019);
        Collection<Course> hist = new ArrayList<>();
        History history = new History(hist);
        userData.setAcademicHistory(history);
        Major m = new Major(deptLib.getCourseMapping(userData.getMajor1()));

        ScheduleGeneratorModel model = new ScheduleGeneratorModel(userData, allCourses, allCourses);
    }

    @Test
    public void GradWritingandPECompleted(){
        DeptLib deptLib = DeptLib.getInstance();
        Collection<Course> courses = new ArrayList<Course>();
        Integer reqsRemaining = 2;
        Boolean isDone = false;

        Course first = new Course("W1", deptLib.getCourseMapping("ENGR"), 1283, false, true, true, new ArrayList<>());
        Course second = new Course("W2", deptLib.getCourseMapping("ENGR"), 1284, false, true, true, new ArrayList<>());
        Course third = new Course("W3", deptLib.getCourseMapping("ENGR"), 1285, false, true, true, new ArrayList<>());
        courses.add(first);
        courses.add(second);
        courses.add(third);

        History hist = new History(courses);
        UserData survey = new UserData();

        Course fourth = new Course("Phys Ed", deptLib.getCourseMapping("PHED"), 1286, false, false, true, new ArrayList<>());
        hist.addToHistory(fourth);

        survey.setAcademicHistory(hist);
        survey.setMajor1("ENGR");

        User user = new User(survey);
        Collection<GradRequirement> result = user.getReqsRemaining();

        Assert.assertEquals(reqsRemaining,Integer.valueOf(result.size()));
        Assert.assertEquals(isDone,user.isGradReqsCompleted());

    }

    @Test
    public void GradReqsNotCompleted(){
        DeptLib deptLib = DeptLib.getInstance();
        Collection<Course> courses = new ArrayList<Course>();
        Integer reqsRemaining = 3;
        Boolean notDone = false;

        Course first = new Course("W1", deptLib.getCourseMapping("ENGR"), 1283, false, true, false, new ArrayList<>());
        Course second = new Course("W2", deptLib.getCourseMapping("ENGR"), 1284, false, true, true, new ArrayList<>());
        Course third = new Course("W3", deptLib.getCourseMapping("ENGR"), 1285, false, true, true, new ArrayList<>());
        courses.add(first);
        courses.add(second);
        courses.add(third);
        History hist = new History(courses);
        UserData survey = new UserData();
        survey.setMajor1("ENGR");
        survey.setAcademicHistory(hist);
        User user = new User(survey);
        Collection<GradRequirement> result = user.getReqsRemaining();

        Assert.assertEquals(reqsRemaining,Integer.valueOf(result.size()));
        Assert.assertEquals(notDone,user.isGradReqsCompleted());
    }


    @Test
    public void AddClassAlreadyInHistory(){
        DeptLib deptLib = DeptLib.getInstance();
        Collection<Course> expected = new ArrayList<Course>();
        Collection<Course> courses = new ArrayList<Course>();
        Integer numW = 1;
        Integer numPE = 1;
        Course first = new Course("CS21", deptLib.getCourseMapping("CPSC"), 1283, false, true, true, new ArrayList<>());
        History hist = new History(courses);
        //add the same class
        hist.addToHistory(first);
        hist.addToHistory(first);
        expected.add(first);
        Assert.assertEquals(expected,hist.getCourseTaken());
        Assert.assertEquals(numPE,Integer.valueOf(hist.getPe()));
        Assert.assertEquals(numW,Integer.valueOf(hist.getWrite()));
    }


    @Test
    public void AddClassToHistory(){
        DeptLib deptLib = DeptLib.getInstance();
        Collection<Course> expected = new ArrayList<Course>();
        Collection<Course> courses = new ArrayList<Course>();
        Integer numW = 2;
        Integer numPE = 2;
        Course first = new Course("CS21", deptLib.getCourseMapping("CPSC"), 1283, false, true, true, new ArrayList<>());
        History hist = new History(courses);
        Course added = new Course("ENG05", deptLib.getCourseMapping("ENGR"), 1284, false, true, true, new ArrayList<>());
        hist.addToHistory(first);
        hist.addToHistory(added);
        expected.add(first);
        expected.add(added);
        Assert.assertEquals(expected,hist.getCourseTaken());
        Assert.assertEquals(numPE,Integer.valueOf(hist.getPe()));
        Assert.assertEquals(numW,Integer.valueOf(hist.getWrite()));
    }

    @Test
    public void CheckDoubleMajors(){
        Integer numMajors = 2;
        UserData survey = new UserData();
        survey.setMajor1("ENGR");
        survey.setMajor2("CPSC");
        User user = new User(survey);
        Assert.assertEquals(numMajors,Integer.valueOf(user.getMajors().size()));

    }


    @Test
    public void CheckDoubleMinors(){
        Integer numMinors = 2;
        UserData survey = new UserData();
        survey.setMajor1("ENGR");
        survey.setMinor2("CPSC");
        survey.setMinor1("ENGL");
        User user = new User(survey);
        Assert.assertEquals(numMinors,Integer.valueOf(user.getMinors().size()));
    }



    @Test
    public void UserGradYear(){
        Integer expected = 2018;
        UserData survey = new UserData();
        survey.setClassYear(2018);
        survey.setMajor1("ENGR");
        User user = new User(survey);
        Assert.assertEquals(expected,Integer.valueOf(user.getGradYear()));

    }

}
