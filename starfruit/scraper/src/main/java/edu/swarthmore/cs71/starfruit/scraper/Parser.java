package edu.swarthmore.cs71.starfruit.scraper;

import edu.swarthmore.cs71.starfruit.classes.course.Course;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/* this acts as an easy to use object that allows us to simply import a file
    * and define what kind of format it has and this will greate course objects from
    * the parsed file that are able to be returned through the get courses method
    * */
public abstract class Parser {
    private PdfManager manager;
    private String pdfString;
    private Collection<Course> courses;
    public Parser(File file) throws IOException {
        this.manager = new PdfManager(file);
        this.pdfString = manager.pdfString();
        this.courses = null;

    }

    public PdfManager getManager() {
        return manager;
    }

    public void setCourse(Collection<Course>courses){
        this.courses = courses;
    }
    public String getPdfString(){ return this.pdfString;}
}
