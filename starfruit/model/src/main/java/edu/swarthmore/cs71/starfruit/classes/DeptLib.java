package edu.swarthmore.cs71.starfruit.classes;

import edu.swarthmore.cs71.starfruit.classes.departments.*;
import edu.swarthmore.cs71.starfruit.classes.departments.Math;

import java.util.*;

/* Object for mapping 4 letter code of Departments in Catalog to Department objects we have made
 */

public class DeptLib {
    private static DeptLib ourInstance = new DeptLib();

    public static DeptLib getInstance() {
        return ourInstance;
    }

    private HashMap<String,Department> CourseMapping;

    private DeptLib() {
        this.CourseMapping = new HashMap<>();

        //Defined
        this.CourseMapping.put("ENGR",new Engineering());
        this.CourseMapping.put("CPSC",new ComputerScience());
        this.CourseMapping.put("ECON",new Econ());


        //TODO: Define in next iteration
        this.CourseMapping.put("STAT",new Stat());
        this.CourseMapping.put("MATH",new Math());

        this.CourseMapping.put("ANCH", new AncientHistory());
        this.CourseMapping.put("ANTH", new Anthropology());
        this.CourseMapping.put("ARAB", new Arabic());
        this.CourseMapping.put("ARTT", new Art());
        this.CourseMapping.put("ARTH", new ArtHistory());
        this.CourseMapping.put("ASTR", new Astronomy());
        this.CourseMapping.put("ASIA", new AsianStudies());

        this.CourseMapping.put("BIOL", new Biology());
        this.CourseMapping.put("BLST", new BlackStudies());

        this.CourseMapping.put("CHEM", new Chemistry());
        this.CourseMapping.put("CHIN", new Chinese());
        this.CourseMapping.put("CLST", new Classics());
        this.CourseMapping.put("COGS", new CognitiveScience());
        this.CourseMapping.put("CPLT", new CompLiterature());

        this.CourseMapping.put("DANC", new Dance());

        this.CourseMapping.put("EDUC", new Education());
        this.CourseMapping.put("ENGL", new English());
        this.CourseMapping.put("ENVS", new EnvironmentalStudies());

        this.CourseMapping.put("FMST", new FilmMedia());
        this.CourseMapping.put("FREN", new French());

        this.CourseMapping.put("GSST", new GenderStudies());
        this.CourseMapping.put("GREK", new Greek());
        this.CourseMapping.put("GMST", new GermanStudies());

        this.CourseMapping.put("HIST", new HistoryStudies());
        this.CourseMapping.put("INTP", new InterpretationTheory());
        this.CourseMapping.put("ISLM", new IslamicStudies());

        this.CourseMapping.put("JPNS", new Japanese());

        this.CourseMapping.put("LATN", new Latin());
        this.CourseMapping.put("LALS", new LatinAmericanStudies());
        this.CourseMapping.put("LING", new Linguistics());
        this.CourseMapping.put("LITR", new LitRussian());

        this.CourseMapping.put("MDST", new MedievalStudies());
        this.CourseMapping.put("MUSI", new Music());


        this.CourseMapping.put("PEAC", new PeaceConflict());
        this.CourseMapping.put("PHED", new PhysEd());
        this.CourseMapping.put("PHIL", new Philosophy());
        this.CourseMapping.put("POLS", new PolySci());
        this.CourseMapping.put("PSYC", new Psychology());
        this.CourseMapping.put("PHYS", new Physics());

        this.CourseMapping.put("RELG", new Religion());
        this.CourseMapping.put("RUSS", new Russian());

        this.CourseMapping.put("SOCI", new Sociology());
        this.CourseMapping.put("SOAN", new SocioAnthro());
        this.CourseMapping.put("SPAN", new Spanish());

        this.CourseMapping.put("THEA", new Theater());


    }

    public Department getCourseMapping(String department) {
        if(this.CourseMapping.containsKey(department)){
            return CourseMapping.get(department);
        }else{
            throw new RuntimeException("There is a department that doesn't exist in the hashmap of departments, named:  "+ department);
        }
    }
    public Collection<Department> getAllDepartments(){
        Collection<Department> departments = new ArrayList<>();
        for(String i: this.CourseMapping.keySet()){
            departments.add(this.getCourseMapping(i));
        }
        return departments;
    }
}
