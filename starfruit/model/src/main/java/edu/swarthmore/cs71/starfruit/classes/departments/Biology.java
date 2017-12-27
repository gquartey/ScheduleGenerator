package edu.swarthmore.cs71.starfruit.classes.departments;

import edu.swarthmore.cs71.starfruit.classes.*;
import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.requirements.*;

import java.util.ArrayList;
import java.util.Collection;

//TODO: Need to change Strings to Groups
//TODO: Access global dictionary (String, Department) and define requirements

public class Biology implements Department {
    private DegreeRequirement major;
    private DegreeRequirement minor;

    public Biology() {
        Collection<CourseCode> comp = new ArrayList<>();

        //General
        Collection<CourseCode> general = new ArrayList<>();
        general.add(new CourseCode("BIOL",9));

        //G1
        Collection<CourseCode> g1 = new ArrayList<>();
        g1.add(new CourseCode("BIOL",10));
        g1.add(new CourseCode("BIOL",14));
        g1.add(new CourseCode("BIOL",16));
        g1.add(new CourseCode("BIOL",17));
        g1.add(new CourseCode("BIOL",19));


        //G2
        Collection<CourseCode> g2 = new ArrayList<>();
        g2.add(new CourseCode("BIOL",20));
        g2.add(new CourseCode("BIOL",22));
        g2.add(new CourseCode("BIOL",24));
        g2.add(new CourseCode("BIOL",25));
        g2.add(new CourseCode("BIOL",26));
        g2.add(new CourseCode("BIOL",29));
        g2.add(new CourseCode("BIOL",27));


        //G3
        Collection<CourseCode> g3 = new ArrayList<>();
        g3.add(new CourseCode("BIOL",30));
        g3.add(new CourseCode("BIOL",34));
        g3.add(new CourseCode("BIOL",36));
        g3.add(new CourseCode("BIOL",37));
        g3.add(new CourseCode("BIOL",39));
        g3.add(new CourseCode("BIOL",68));

        //math
        Collection<CourseCode> mathStat = new ArrayList<>();
        mathStat.add(new CourseCode("MATH",15));
        mathStat.add(new CourseCode("MATH",25));
        mathStat.add(new CourseCode("MATH",26));
        mathStat.add(new CourseCode("MATH",27));
        mathStat.add(new CourseCode("MATH",29));
        mathStat.add(new CourseCode("STAT",11));

        //Seminars
        Collection<CourseCode> sems = new ArrayList<>();
        sems.add(new CourseCode("BIOL",111));
        sems.add(new CourseCode("BIOL",114));
        sems.add(new CourseCode("BIOL",115));
        sems.add(new CourseCode("BIOL",116));
        sems.add(new CourseCode("BIOL",119));
        sems.add(new CourseCode("BIOL",121));
        sems.add(new CourseCode("BIOL",123));
        sems.add(new CourseCode("BIOL",124));
        sems.add(new CourseCode("BIOL",125));
        sems.add(new CourseCode("BIOL",126));
        sems.add(new CourseCode("BIOL",131));
        sems.add(new CourseCode("BIOL",135));
        sems.add(new CourseCode("BIOL",136));
        sems.add(new CourseCode("BIOL",137));
        //DO SENIOR PROJEct

        Collection<CourseCode> NSE = new ArrayList<>();
        NSE.add(new CourseCode("ASTR", 16));
        NSE.add(new CourseCode("CHEM", 15));
        NSE.add(new CourseCode("CPSC", 21));
        NSE.add(new CourseCode("ENGR", 5));
        NSE.add(new CourseCode("PHYS", 3));



        DegreeRequirement naturalScience = new RepeatedRequirement(2, new OneOfRequirement(NSE));
        DegreeRequirement b1 = new CourseRequirement(new CourseCode("BIOL",1));
        DegreeRequirement b2 = new CourseRequirement(new CourseCode("BIOL",2));
        DegreeRequirement c10 = new CourseRequirement(new CourseCode("CHEM", 10));
        DegreeRequirement math = new RepeatedRequirement(2, new OneOfRequirement(mathStat));
        DegreeRequirement seminars = new RepeatedRequirement(2, new OneOfRequirement(sems));
        DegreeRequirement group1 = new OneOfRequirement(g1);
        DegreeRequirement group2 = new OneOfRequirement(g2);
        DegreeRequirement group3 = new OneOfRequirement(g3);

        Collection<DegreeRequirement> thatYoungMajor = new ArrayList<>();
        thatYoungMajor.add(b1);
        thatYoungMajor.add(b2);
        thatYoungMajor.add(c10);
        thatYoungMajor.add(math);
        thatYoungMajor.add(seminars);
        thatYoungMajor.add(group1);
        thatYoungMajor.add(group2);
        thatYoungMajor.add(group3);
        thatYoungMajor.add(naturalScience);

        //12
        DegreeRequirement andRequirement = new AndRequirement(thatYoungMajor);
        this.major = new MinimumRequirement(12,andRequirement);

    }


    @Override
    public Collection<Solution> getSolutionsMajor(Collection<CourseCode> taken){
        return this.major.getSolutionSet(taken);
    }

    @Override
    public Collection<Solution> getSolutionsMinor(Collection<CourseCode> taken) {
        return null;
    }

    @Override
    public String getFourLetter() {
        return "BIOL";
    }
}