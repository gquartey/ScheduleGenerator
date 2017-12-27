package edu.swarthmore.cs71.starfruit.classes.departments;

import edu.swarthmore.cs71.starfruit.classes.*;
import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.requirements.*;

import java.util.ArrayList;
import java.util.Collection;

//TODO: Need to change Strings to Groups and need to access global dictionary (String, Department)


public class Econ implements Department {
    private DegreeRequirement major;

    private DegreeRequirement minor;

    public Econ() {

        DegreeRequirement core = new CourseRequirement(new CourseCode("ECON",1));
        DegreeRequirement core1 = new CourseRequirement(new CourseCode("ECON",11));
        DegreeRequirement core2 = new CourseRequirement(new CourseCode("ECON",21));


        Collection<CourseCode> statReq = new ArrayList<>();
        statReq.add(new CourseCode("ECON", 31));
        statReq.add(new CourseCode("STAT", 111));

        DegreeRequirement stat = new OneOfRequirement(statReq);

        Collection<CourseCode> repeats = new ArrayList<>();
        repeats.add(new CourseCode("ECON", 22));
        repeats.add(new CourseCode("ECON", 33));
        repeats.add(new CourseCode("ECON", 42));
        repeats.add(new CourseCode("ECON", 54));
        repeats.add(new CourseCode("ECON", 55));
        repeats.add(new CourseCode("ECON", 61));
        repeats.add(new CourseCode("ECON", 73));
        repeats.add(new CourseCode("ECON", 75));
        repeats.add(new CourseCode("ECON", 76));
        repeats.add(new CourseCode("ECON", 102));
        repeats.add(new CourseCode("ECON", 155));
        repeats.add(new CourseCode("ECON", 176));
        repeats.add(new CourseCode("ECON", 181));

        DegreeRequirement oneOf = new OneOfRequirement(repeats);

        DegreeRequirement repeated = new RepeatedRequirement(4,oneOf);

        Collection<DegreeRequirement> allReqs = new ArrayList<>();
        allReqs.add(core);
        allReqs.add(core1);
        allReqs.add(core2);
        allReqs.add(stat);
        allReqs.add(repeated);


        DegreeRequirement andRequirement = new AndRequirement(allReqs);
        this.major = new MinimumRequirement(8,andRequirement);

    }

    @Override
    public Collection<Solution> getSolutionsMajor(Collection<CourseCode> taken) {
        return this.major.getSolutionSet(taken);
    }

    @Override
    public Collection<Solution> getSolutionsMinor(Collection<CourseCode> taken) {
        //TODO
        return new ArrayList<>();
    }

    @Override
    public String getFourLetter() {
        return "ECON";
    }
}

