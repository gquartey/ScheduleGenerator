package edu.swarthmore.cs71.starfruit.classes;


import edu.swarthmore.cs71.starfruit.classes.course.Course;
import edu.swarthmore.cs71.starfruit.classes.departments.Department;
import edu.swarthmore.cs71.starfruit.classes.requirements.Distribution;
import edu.swarthmore.cs71.starfruit.classes.requirements.GradRequirement;
import org.joda.time.LocalDate;

import java.util.*;

public class ScheduleGeneratorModel {
    private int coursesAdded;
    private UserData userData;
    private User student;
    private HashMap<Department, Collection<Course>> springCatalog;
    private HashMap<Department, Collection<Course>> fallCatalog;
    private HashMap<Department, Collection<Course>> currCatalog;
    private HashMap<Department, Collection<Course>> fullCatalog;
    // if semNumber = 0; it's fall, if it's 1, it's spring
    private int semNumber;
    private History history;
    SemesterSchedule schedule;
    private Major primeMajor;
    private Major secondaryMajor;
    private Minor minor1;
    private Minor minor2;
    private Collection<Solution> solutionSetMajor1;
    private Collection<Solution> solutionSetMajor2;
    private Collection<Solution> solutionSetMinor1;
    private Collection<Solution> solutionSetMinor2;
    private Collection<Course> solutions;

    private int semLeft;
    private int semCourses;
    private int freeCourseSlots;
    private Solution solutionChosen;
    private Collection<GradRequirement> gradRequirement;
    private DistributionMapping distributionMapping;
    private DeptLib deptLib;
    private int sizeOfSolution;

    public ScheduleGeneratorModel(UserData userData, HashMap<Department, Collection<Course>> fallCatalog, HashMap<Department, Collection<Course>> springCatalog) {

        this.distributionMapping = DistributionMapping.getInstance();
        this.deptLib = DeptLib.getInstance();
        this.coursesAdded = 0;
        this.semCourses = 0;
        this.userData = userData;
        this.student = new User(userData);
        this.springCatalog = springCatalog;
        this.fallCatalog = fallCatalog;
        this.fullCatalog = new HashMap<>();
        this.fullCatalog.putAll(this.springCatalog);
        this.fullCatalog.putAll(this.fallCatalog);
        this.history = student.getHistory();
        //make semester schedule take in a date
        this.schedule = new SemesterSchedule("TBD");
        this.gradRequirement = student.getReqsRemaining();

        LocalDate localDate = new LocalDate();
        if (localDate.getMonthOfYear() < 10) {
            this.semLeft = (student.getGradYear() - localDate.getYear()) * 2;
            this.semNumber = 0;
        } else {
            this.semLeft = (student.getGradYear() - localDate.getYear()) * 2 -1;
            this.semNumber = 1;
        }
        this.primeMajor = this.student.getPrimeMajor();

        this.secondaryMajor = this.student.getSecondMajor();
        this.minor1 = this.student.getMinor1();
        this.minor2 = this.student.getMinor2();

        this.solutionSetMajor1 = new ArrayList<>();
        if (!history.getCourseTaken().isEmpty()) {
            Mapper mapper = new Mapper(history.getCourseTaken(), new ArrayList<>(), fullCatalog);
            solutionSetMajor1 = this.primeMajor.getDepartment().getSolutionsMajor(mapper.coursesToCodes());
        } else {
            solutionSetMajor1 = this.primeMajor.getDepartment().getSolutionsMajor(new ArrayList<>());
        }

        this.solutionSetMajor2 = new ArrayList<>();
        if (secondaryMajor != null) {
            if (!history.getCourseTaken().isEmpty()) {
                Mapper mapper = new Mapper(history.getCourseTaken(), new ArrayList<>(), fullCatalog);
                solutionSetMajor2 = this.secondaryMajor.getDepartment().getSolutionsMajor(mapper.coursesToCodes());
            } else {
                solutionSetMajor2 = this.secondaryMajor.getDepartment().getSolutionsMajor(new ArrayList<>());
            }
        }
    }

    private void setSemCourses (int sizeOfSolution) {

        if ((getTotalCredReq(sizeOfSolution) - semLeft*4) > 0) {
            this.semCourses = 5;
        } else {
            this.semCourses = 4;
        }

    }

    private void updateStudentHistory() {
        this.student.updateHistory(this.history.getCourseTaken());
    }

    // make it so that our code checks to see if the solution is valid based on the catalog object!!!
    // Also make sure to check that our thing works in a way that allows you to graduate
    public void generateSchedule() {

        if (semNumber == 0) {
            this.currCatalog = springCatalog;
        } else {
            this.currCatalog = fallCatalog;
        }

        schedule = new SemesterSchedule("gs");
        boolean valid = false;
        ArrayList<Solution> solutions1 = new ArrayList<>(solutionSetMajor1);
        ArrayList<Solution> solutions2 = new ArrayList<>(solutionSetMajor2);
        int solutionNum = 0;
        int solutionNum2 = 0;

        while (valid == false) {

            setSemCourses(solutions1.get(solutionNum).getCourses().size());
            this.freeCourseSlots = semLeft * 4;
            //major1
            if (!solutionSetMajor1.isEmpty()) {

                solutionChosen = solutions1.get(solutionNum);


                Mapper mapper = new Mapper (new ArrayList<>(), solutionChosen.getCourses(), currCatalog);
                Collection<Course> majorCourses = mapper.codesToCourses();


                addToSchedule(majorCourses, findNumCoursesMajor(solutions1.get(solutionNum).getCourses().size()));
            }

            if (schedule.getSemSize() < semCourses) {
                //gradReqs

                completeGradReq();
            }

            if (schedule.getSemSize() < semCourses && secondaryMajor != null) {

                solutionChosen = solutions2.get(solutionNum);
                Mapper mapper = new Mapper (new ArrayList<>(), solutionChosen.getCourses(), currCatalog);
                Collection<Course> major2Courses = mapper.codesToCourses();
                addToSchedule(major2Courses, findNumCoursesMajor(solutions2.get(solutionNum).getCourses().size())); // to get it to work first -> optimize once we get running

            }

            if (schedule.getSemSize() < semCourses) {
                fillCourses();
            }
            //checker
            Mapper mapper = new Mapper(history.getCourseTaken(), new ArrayList<>(), fullCatalog);

            valid = true;
        }
        if (this.semNumber == 0) {
            this.semNumber = 1;
        } else {
            this.semNumber = 0;
        }

    }
    //TODO check for bad schedule
    // TODO use getFinalSchedule


    //TODO: check for valid Solution in here


    public SemesterSchedule getSchedule() {
        return schedule;
    }


    //is the solution set given back in the correct order of hierarchy? aka prereqs
    public void addToSchedule(Collection<Course> courses, int numCoursesWanted) {

        for (Course course : courses) {
            //if our schedule is under capacity
            if (this.schedule.getSemSize() < this.semCourses) {
                //if we haven't added the optimal amount of courses yet
                if (this.coursesAdded < numCoursesWanted) {

                    //add course
                    if (!checkTaken(course)) {


                        this.schedule.addCourse(new ScheduledCourse(course));
                        this.history.addToHistory(course);
                        this.coursesAdded++;
                        for (GradRequirement i : gradRequirement) {
                            if (i.getType() == distributionMapping.getDepartmentToDistribution().get(course.getDepartment())) {
                                i.updateHistory(history);
                            }

                        }

                    }

                }
            }
        }
        coursesAdded = 0;
    }

    private boolean checkTaken(Course course) {

        if (!this.history.getCourseTaken().isEmpty()) {
            for (Course i : history.getCourseTaken()) {
                if (i.getCourseCodeObject().equals(course.getCourseCodeObject())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void completeGradReq() {

            //need to have a way of sorting through catalog and seeing which distribution a course belongs to
        Distribution distribution = null;
        if (this.secondaryMajor != null) {
            distribution = distributionMapping.getDepartmentToDistribution().get(this.secondaryMajor);
        }
        for (GradRequirement i : gradRequirement) {
            if (!i.isCompleted() && !i.equals(distribution)) {
                Collection<Department> possDepartments = distributionMapping.getDistributionToDepartment().get(i.getType());

                //figure out writing and PE later and Labs and conferences and freshmen seminars
                if (!i.getType().equals(Distribution.W) && !i.getType().equals(Distribution.PE)) {
                    Department chosen = getRandomDepartment(possDepartments);
                    //instead of doing random, later make it so that it takes user prefrences into account
                    //  Department department = getRandomDepartment(possDepartments);
                    Collection<Course> gradCourses = this.currCatalog.get(chosen);

                    if (!gradCourses.isEmpty()) {
                        addToSchedule(gradCourses, findNumCoursesGR(i.getTotalNeeded()));
                        i.updateHistory(this.history);
                    }

                }
            }
        }
    }

    //returns the optimal number of major courses one should take in a semester -> for optimizing sched later


    public int findNumCoursesMajor(int sizeOfSolution) {

        return sizeOfSolution/semLeft + 1;
    }


    //returns the optimal number of gradReq courses one should take in a sem
    // later make it so that students have to take 2 writing courses before junior year
    public int findNumCoursesGR(int totalNeeded) {

        return totalNeeded/semLeft + 1;
    }

    //returns a random department from the possible list (to satisfy gradreqs)
    //can make it personalized based on preferences in the future
    public Department getRandomDepartment(Collection<Department> collection) {
        Random rnd = new Random();
        int i = rnd.nextInt(collection.size());
        Department[] departments = collection.toArray(new Department[collection.size()]);
        return departments[i];
    }

    public int getTotalCredReq (int sizeOfSolution) {
        int numMajorReqs = 0;
        for (Major i : student.getMajors()) {
            numMajorReqs += sizeOfSolution;
        }
        return student.getNumReqsleft() + numMajorReqs ;
    }

    public History getHistory() {
        return history;
    }

    private void fillCourses() {
        Department chosen = getRandomDepartment(student.getStudentPref());
        addToSchedule(currCatalog.get(chosen), 2);
        Department chosen1 = getRandomDepartment(student.getStudentPref());
        addToSchedule(currCatalog.get(chosen1), 2);
        Department chosen2 = getRandomDepartment(student.getStudentPref());
        addToSchedule(currCatalog.get(chosen2), 2);
    }

    //TODO make it four a one year catalog and then assume off that
    public Collection<SemesterSchedule> getFullPlan(){
        Collection<SemesterSchedule> plan = new ArrayList<>();
        for(int i = 0; i < semLeft; i++){
            generateSchedule();
            SemesterSchedule sem = this.schedule;
            plan.add(sem);
        }
        return plan;
    }
}
