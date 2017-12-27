package edu.swarthmore.cs71.starfruit.classes.departments;

import edu.swarthmore.cs71.starfruit.classes.*;
import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.requirements.*;

import java.util.ArrayList;
import java.util.Collection;


//TODO: Need to change Strings to Groups and need to access global dictionary (String, Department)


public class ComputerScience implements Department {
    private DegreeRequirement major;
    private DegreeRequirement minor;

//TODO: Change all of the integers to actual Course objects


    public ComputerScience() {
        Collection<CourseCode> comp = new ArrayList<>();
        //cores

        //G1
        Collection<CourseCode> g1 = new ArrayList<>();
        g1.add(new CourseCode("CPSC",41));
        g1.add(new CourseCode("CPSC",46));


        comp.add(new CourseCode("CPSC",41));
        comp.add(new CourseCode("CPSC",46));


        //G2
        Collection<CourseCode> g2 = new ArrayList<>();
        g2.add(new CourseCode("CPSC",43));
        g2.add(new CourseCode("CPSC",44));
        g2.add(new CourseCode("CPSC",45));
        g2.add(new CourseCode("CPSC",75));
        g2.add(new CourseCode("CPSC",87));
        g2.add(new CourseCode("CPSC",89));

        comp.add(new CourseCode("CPSC",43));
        comp.add(new CourseCode("CPSC",44));
        comp.add(new CourseCode("CPSC",45));
        comp.add(new CourseCode("CPSC",75));
        comp.add(new CourseCode("CPSC",87));
        comp.add(new CourseCode("CPSC",89));

        //G3
        Collection<CourseCode> g3 = new ArrayList<>();
        g3.add(new CourseCode("CPSC",40));
        g3.add(new CourseCode("CPSC",63));
        g3.add(new CourseCode("CPSC",65));
        g3.add(new CourseCode("CPSC",66));
        g3.add(new CourseCode("CPSC",68));
        g3.add(new CourseCode("CPSC",71));
        g3.add(new CourseCode("CPSC",73));
        g3.add(new CourseCode("CPSC",81));
        //cross
        g3.add(new CourseCode("CPSC",82));
        g3.add(new CourseCode("CPSC",72));

        comp.add(new CourseCode("CPSC",40));
        comp.add(new CourseCode("CPSC",63));
        comp.add(new CourseCode("CPSC",65));
        comp.add(new CourseCode("CPSC",66));
        comp.add(new CourseCode("CPSC",68));
        comp.add(new CourseCode("CPSC",71));
        comp.add(new CourseCode("CPSC",73));
        comp.add(new CourseCode("CPSC",81));
        //cross
        comp.add(new CourseCode("CPSC",82));
        comp.add(new CourseCode("CPSC",72));


        Collection<CourseCode> math = new ArrayList<>();
        math.add(new CourseCode("MATH",27));
        math.add(new CourseCode("MATH",29));

        Collection<DegreeRequirement> majDegree = new ArrayList<>();


        DegreeRequirement cs21 = new CourseRequirement(new CourseCode("CPSC", 21));
        majDegree.add(cs21);

        DegreeRequirement cs31 = new CourseRequirement(new CourseCode("CPSC",31));
        majDegree.add(cs31);

        DegreeRequirement cs35 = new CourseRequirement(new CourseCode("CPSC",35));
        majDegree.add(cs35);

        DegreeRequirement mathematics = new OneOfRequirement(math);
        majDegree.add(mathematics);

        DegreeRequirement G1 = new OneOfRequirement(g1);
        majDegree.add(G1);

        DegreeRequirement G2 = new OneOfRequirement(g2);
        majDegree.add(G2);

        DegreeRequirement G3 = new OneOfRequirement(g3);
        majDegree.add(G3);

        DegreeRequirement repeatedCS = new OneOfRequirement(comp);
        DegreeRequirement repeat = new RepeatedRequirement(3,repeatedCS);
        majDegree.add(repeat);


        DegreeRequirement andRequirement = new AndRequirement(majDegree);
        this.major = new MinimumRequirement(10,andRequirement);
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
        return "CPSC";
    }
}
