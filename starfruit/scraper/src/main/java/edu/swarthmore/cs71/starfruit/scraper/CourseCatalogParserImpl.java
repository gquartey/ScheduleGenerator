package edu.swarthmore.cs71.starfruit.scraper;

import edu.swarthmore.cs71.starfruit.classes.course.Course;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
/*
* This class implements a coursecatalog parser
* by extending the parser class which takes in a file
* and extracts the string representation in order for it
* to be able to be parsed.*/
public class CourseCatalogParserImpl extends Parser {
    private CourseCatalogParser parser;
    public CourseCatalogParserImpl(File file) throws IOException {
        super(file);
        this.parser = new CourseCatalogParser(super.getPdfString());

    }
    public Collection<Course> parsePDF(){

        ArrayList<String> PDF_COMPONENTS = this.parser.produceBasicStrings();
        return this.parser.produceCourses(PDF_COMPONENTS);

    }

}
