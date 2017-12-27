package edu.swarthmore.cs71.starfruit.classes.departments;


import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.requirements.*;

import edu.swarthmore.cs71.starfruit.classes.Solution;

import java.util.ArrayList;
import java.util.Collection;


public class Chemistry implements Department {
    private DegreeRequirement major;
    private DegreeRequirement minor;

    public Chemistry() {
        Collection<DegreeRequirement> majDegree = new ArrayList<>();

        DegreeRequirement chem1 = new CourseRequirement( new CourseCode("CHEM", 10));
        DegreeRequirement phys3 = new CourseRequirement(new CourseCode("PHYS",3));

        Collection<CourseCode> ancillary = new ArrayList<>();
        ancillary.add(new CourseCode("PHYS",4));
        ancillary.add(new CourseCode("PHYS",7));
        ancillary.add(new CourseCode("PHYS",8));

        DegreeRequirement science = new OneOfRequirement(ancillary);


        DegreeRequirement math34 = new CourseRequirement(new CourseCode("MATH", 34));


        DegreeRequirement chem2 = new CourseRequirement( new CourseCode("CHEM", 22));
        DegreeRequirement chem3 = new CourseRequirement( new CourseCode("CHEM", 32));
        DegreeRequirement chem4 = new CourseRequirement( new CourseCode("CHEM", 38));
        DegreeRequirement chem5 = new CourseRequirement( new CourseCode("CHEM", 44));
        DegreeRequirement chem6 = new CourseRequirement( new CourseCode("CHEM", 55));
        DegreeRequirement chem7 = new CourseRequirement( new CourseCode("CHEM", 56));
        DegreeRequirement chem8 = new CourseRequirement( new CourseCode("CHEM", 57));

        Collection<CourseCode> seminars = new ArrayList<>();
        seminars.add(new CourseCode("CHEM", 106));
        seminars.add(new CourseCode("CHEM", 108));
        seminars.add(new CourseCode("CHEM", 110));
        seminars.add(new CourseCode("CHEM", 112));

        DegreeRequirement finalSem = new OneOfRequirement(seminars);

        majDegree.add(chem1);
        majDegree.add(phys3);
        majDegree.add(science);
        majDegree.add(math34);
        majDegree.add(chem2);
        majDegree.add(chem3);
        majDegree.add(chem4);
        majDegree.add(chem5);
        majDegree.add(chem6);
        majDegree.add(chem7);
        majDegree.add(chem8);
        majDegree.add(finalSem);

        DegreeRequirement andRequirement = new AndRequirement(majDegree);
        this.major = new MinimumRequirement(12,andRequirement);
    }


    @Override
    public Collection<Solution> getSolutionsMajor(Collection<CourseCode> taken) {
        return this.major.getSolutionSet(taken);
    }

    @Override
    public Collection<Solution> getSolutionsMinor(Collection<CourseCode> taken) {
        return null;
    }

    @Override
    public String getFourLetter() {
        return "CHEM";
    }
}