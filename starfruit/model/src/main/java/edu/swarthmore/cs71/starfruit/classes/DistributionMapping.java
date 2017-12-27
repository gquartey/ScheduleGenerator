package edu.swarthmore.cs71.starfruit.classes;

import edu.swarthmore.cs71.starfruit.classes.departments.Department;
import edu.swarthmore.cs71.starfruit.classes.requirements.Distribution;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class DistributionMapping {
    private static DistributionMapping ourInstance = new DistributionMapping();

    public static DistributionMapping getInstance() {
        return ourInstance;
    }


    DeptLib depLib;
    private HashMap<Department,Distribution> DepartmentToDistribution;
    private HashMap<Distribution,Collection<Department>> DistributionToDepartment;

    //To do in future: Get writing reqs in and fill in every department into collections
    private DistributionMapping() {
        this.depLib = DeptLib.getInstance();
        DepartmentToDistribution = new HashMap<>();
        DepartmentToDistribution.put(depLib.getCourseMapping("ECON"), Distribution.SS);
        DepartmentToDistribution.put(depLib.getCourseMapping("ANTH"), Distribution.SS);
        DepartmentToDistribution.put(depLib.getCourseMapping("EDUC"), Distribution.SS);
        DepartmentToDistribution.put(depLib.getCourseMapping("HIST"), Distribution.SS);
        DepartmentToDistribution.put(depLib.getCourseMapping("LING"), Distribution.SS);
        DepartmentToDistribution.put(depLib.getCourseMapping("PSYC"), Distribution.SS);

        DepartmentToDistribution.put(depLib.getCourseMapping("CPSC"), Distribution.NS);
        DepartmentToDistribution.put(depLib.getCourseMapping("ENGR"), Distribution.NS);
        DepartmentToDistribution.put(depLib.getCourseMapping("MATH"), Distribution.NS);
        DepartmentToDistribution.put(depLib.getCourseMapping("STAT"), Distribution.NS);
        DepartmentToDistribution.put(depLib.getCourseMapping("ASTR"), Distribution.NS);
        DepartmentToDistribution.put(depLib.getCourseMapping("BIOL"), Distribution.NS);
        DepartmentToDistribution.put(depLib.getCourseMapping("CHEM"), Distribution.NS);
        DepartmentToDistribution.put(depLib.getCourseMapping("PHYS"), Distribution.NS);

        DepartmentToDistribution.put(depLib.getCourseMapping("ARTH"), Distribution.HU);
        DepartmentToDistribution.put(depLib.getCourseMapping("ENGL"), Distribution.HU);
        DepartmentToDistribution.put(depLib.getCourseMapping("FREN"), Distribution.HU);
        DepartmentToDistribution.put(depLib.getCourseMapping("GREK"), Distribution.HU);
        DepartmentToDistribution.put(depLib.getCourseMapping("SPAN"), Distribution.HU);
        DepartmentToDistribution.put(depLib.getCourseMapping("LITR"), Distribution.HU);
        DepartmentToDistribution.put(depLib.getCourseMapping("ARAB"), Distribution.HU);
        DepartmentToDistribution.put(depLib.getCourseMapping("ARTT"), Distribution.HU);
        DepartmentToDistribution.put(depLib.getCourseMapping("DANC"), Distribution.HU);
        DepartmentToDistribution.put(depLib.getCourseMapping("FMST"), Distribution.HU);
        DepartmentToDistribution.put(depLib.getCourseMapping("JPNS"), Distribution.HU);
        DepartmentToDistribution.put(depLib.getCourseMapping("LATN"), Distribution.HU);
        DepartmentToDistribution.put(depLib.getCourseMapping("PHIL"), Distribution.HU);

        this.DistributionToDepartment = new HashMap<>();
        Collection<Department> natural = new ArrayList<>();

        natural.add(depLib.getCourseMapping("ASTR"));
        natural.add(depLib.getCourseMapping("BIOL"));
        natural.add(depLib.getCourseMapping("CHEM"));
        natural.add(depLib.getCourseMapping("CPSC"));
        natural.add(depLib.getCourseMapping("ENGR"));
        natural.add(depLib.getCourseMapping("PHYS"));
        natural.add(depLib.getCourseMapping("MATH"));
        natural.add(depLib.getCourseMapping("STAT"));

        DistributionToDepartment.put(Distribution.NS, natural);

        Collection<Department> social = new ArrayList<>();

        social.add(depLib.getCourseMapping("ECON"));
        social.add(depLib.getCourseMapping("ANTH"));
        social.add(depLib.getCourseMapping("EDUC"));
        social.add(depLib.getCourseMapping("HIST"));
        social.add(depLib.getCourseMapping("LING"));
        social.add(depLib.getCourseMapping("PSYC"));

        DistributionToDepartment.put(Distribution.SS, social);


        Collection<Department> humanity = new ArrayList<>();

        humanity.add(depLib.getCourseMapping("ARTH"));
        humanity.add(depLib.getCourseMapping("ENGL"));
        humanity.add(depLib.getCourseMapping("FREN"));
        humanity.add(depLib.getCourseMapping("GREK"));
        humanity.add(depLib.getCourseMapping("SPAN"));
        humanity.add(depLib.getCourseMapping("LITR"));
        humanity.add(depLib.getCourseMapping("ARAB"));
        humanity.add(depLib.getCourseMapping("ARTT"));
        humanity.add(depLib.getCourseMapping("CHIN"));
        humanity.add(depLib.getCourseMapping("DANC"));
        humanity.add(depLib.getCourseMapping("FMST"));
        humanity.add(depLib.getCourseMapping("JPNS"));
        humanity.add(depLib.getCourseMapping("LATN"));
        humanity.add(depLib.getCourseMapping("PHIL"));

        DistributionToDepartment.put(Distribution.HU, humanity);

        Collection<Department> phys = new ArrayList<>();

        phys.add(depLib.getCourseMapping("PHED"));

        DistributionToDepartment.put(Distribution.PE, phys);

        Collection<Department> writing = new ArrayList<>();


    }

    public HashMap<Department, Distribution> getDepartmentToDistribution() {
        return DepartmentToDistribution;
    }

    public HashMap<Distribution, Collection<Department>> getDistributionToDepartment() {
        return DistributionToDepartment;
    }
}
