package edu.swarthmore.cs71.starfruit.classes.departments;

import edu.swarthmore.cs71.starfruit.classes.*;
import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.requirements.*;

import java.util.ArrayList;
import java.util.Collection;


public class Engineering implements Department {
    private DegreeRequirement major;
    private DegreeRequirement minor;

    public Engineering() {

        // set up degree requirement list for AND
        Collection<DegreeRequirement> majDegree = new ArrayList<>();

        //get CourseReqs you need to take first
        DegreeRequirement e6 = new CourseRequirement(new CourseCode("ENGR", 6));
        majDegree.add(e6);

        DegreeRequirement e11 = new CourseRequirement(new CourseCode("ENGR",11));
        majDegree.add(e11);

        DegreeRequirement e12 = new CourseRequirement(new CourseCode("ENGR",12));
        majDegree.add(e12);

        DegreeRequirement e14 = new CourseRequirement(new CourseCode("ENGR",14));
        majDegree.add(e14);


        //get outside ENine you need to take
        CourseCode m15 = new CourseCode("MATH",15);
        CourseCode m25 = new CourseCode("MATH", 25);
        CourseCode m33 =new CourseCode("MATH", 33);
        CourseCode m43 = new CourseCode("MATH", 43);

        DegreeRequirement math15 = new CourseRequirement(m15);
        DegreeRequirement math25 = new CourseRequirement(m25);
        DegreeRequirement math33 = new CourseRequirement(m33);
        DegreeRequirement math43 = new CourseRequirement(m43);



        //Get physics your expected to do
        CourseCode physic3 = new CourseCode("PHYS",3);
        CourseCode physic4 = new CourseCode("PHYS",4);

        DegreeRequirement phy3 = new CourseRequirement(physic3);
        DegreeRequirement phy4 = new CourseRequirement(physic4);



        //Take 2 out of these
        Collection<CourseCode> other = new ArrayList<>();
        other.add(new CourseCode("BIOL",1));
        other.add(new CourseCode("BIOL",2));
        other.add(new CourseCode("CHEM",10));
        other.add(new CourseCode("MATH",27));

        DegreeRequirement otherScience = new OneOfRequirement(other);
        DegreeRequirement repeatScience = new RepeatedRequirement(2,otherScience);


        //Can take either 15 or 19
        Collection<CourseCode> haveToTake = new ArrayList<>();
        haveToTake.add(new CourseCode("ENGR", 15));
        haveToTake.add(new CourseCode("ENGR", 19));
        DegreeRequirement oneOf = new OneOfRequirement(haveToTake);
        majDegree.add(oneOf);

        //Have to take
        DegreeRequirement e41 = new CourseRequirement(new CourseCode("ENGR",41));
        majDegree.add(e41);



        //Electives you can use to complete major
        Collection<CourseCode> engine = new ArrayList<>();

        engine.add(new CourseCode("ENGR",20));
        engine.add(new CourseCode("ENGR",22));
        engine.add(new CourseCode("ENGR",23));
        engine.add(new CourseCode("ENGR",25));
        engine.add(new CourseCode("ENGR",26));
        engine.add(new CourseCode("ENGR",27));
        engine.add(new CourseCode("ENGR",28));
        engine.add(new CourseCode("ENGR",29));
        engine.add(new CourseCode("ENGR",35));
        engine.add(new CourseCode("ENGR",57));
        engine.add(new CourseCode("ENGR",58));
        engine.add(new CourseCode("ENGR",59));
        engine.add(new CourseCode("ENGR",60));
        engine.add(new CourseCode("ENGR",61));
        engine.add(new CourseCode("ENGR",62));
        engine.add(new CourseCode("ENGR",63));
        engine.add(new CourseCode("ENGR",66));
        engine.add(new CourseCode("ENGR",71));
        engine.add(new CourseCode("ENGR",72));
        engine.add(new CourseCode("ENGR",73));
        engine.add(new CourseCode("ENGR",74));
        engine.add(new CourseCode("ENGR",75));
        engine.add(new CourseCode("ENGR",76));
        engine.add(new CourseCode("ENGR",78));
        engine.add(new CourseCode("ENGR",81));
        engine.add(new CourseCode("ENGR",83));
        engine.add(new CourseCode("ENGR",84));
        engine.add(new CourseCode("ENGR",86));

        DegreeRequirement repeats = new OneOfRequirement(engine);
        DegreeRequirement electives = new RepeatedRequirement(5, repeats);
        majDegree.add(electives);

        //final class you are required to take
        DegreeRequirement e90 = new CourseRequirement(new CourseCode("ENGR",90));
        majDegree.add(e90);

        //final AND requirement
        DegreeRequirement andRequirement = new AndRequirement(majDegree);
        this.major = new MinimumRequirement(20,andRequirement);

    }



    @Override
    public Collection<Solution> getSolutionsMajor(Collection<CourseCode> taken) {
        return this.major.getSolutionSet(taken);
    }

    @Override
    public Collection<Solution> getSolutionsMinor(Collection<CourseCode> taken) {
        return new ArrayList<>();
    }

    @Override
    public String getFourLetter() {
        return "ENGR";
    }
}
