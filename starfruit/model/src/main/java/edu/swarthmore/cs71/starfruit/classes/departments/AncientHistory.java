package edu.swarthmore.cs71.starfruit.classes.departments;


import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.requirements.DegreeRequirement;
import edu.swarthmore.cs71.starfruit.classes.Solution;
import edu.swarthmore.cs71.starfruit.classes.requirements.OneOfRequirement;
import edu.swarthmore.cs71.starfruit.classes.requirements.RepeatedRequirement;

import java.util.ArrayList;
import java.util.Collection;

//TODO: Need to change Strings to Groups
//TODO: Access global dictionary (String, Department) and define requirements

public class AncientHistory implements Department {
    private DegreeRequirement major;
    private DegreeRequirement minor;

    public AncientHistory() {
        Collection<DegreeRequirement> majDegree = new ArrayList<>();

        Collection<CourseCode> classestoTake = new ArrayList<>();
        classestoTake.add(new CourseCode("ANCH",31));
        classestoTake.add(new CourseCode("ANCH",32));
        classestoTake.add(new CourseCode("ANCH",42));
        classestoTake.add(new CourseCode("ANCH",44));
        classestoTake.add(new CourseCode("ANCH",56));
        classestoTake.add(new CourseCode("ANCH",66));

        DegreeRequirement oneOf = new OneOfRequirement(classestoTake);
        DegreeRequirement repeat = new RepeatedRequirement(4, oneOf);

        majDegree.add(repeat);
    }


    @Override
    public Collection<Solution> getSolutionsMajor(Collection<CourseCode> taken) {
        return null;
    }

    @Override
    public Collection<Solution> getSolutionsMinor(Collection<CourseCode> taken) {
        return null;
    }

    @Override
    public String getFourLetter() {
        return "ANCH";
    }
}