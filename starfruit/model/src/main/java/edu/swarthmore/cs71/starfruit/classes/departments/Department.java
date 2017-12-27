package edu.swarthmore.cs71.starfruit.classes.departments;

import edu.swarthmore.cs71.starfruit.classes.Solution;
import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;

import java.io.Serializable;
import java.util.Collection;

public interface Department extends Serializable{

     Collection<Solution> getSolutionsMajor(Collection<CourseCode> taken);
     Collection<Solution> getSolutionsMinor(Collection<CourseCode> taken);

     String getFourLetter();
}
