package edu.swarthmore.cs71.starfruit.scraper;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import edu.swarthmore.cs71.starfruit.classes.course.CourseCode;
import edu.swarthmore.cs71.starfruit.classes.course.CourseCodeComparator;
import edu.swarthmore.cs71.starfruit.classes.requirements.DegreeRequirement;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
/*
* This is a pre-req parser implementation that
* extends parser and implements our pre req parser.
* It takes in a file and extracts the string so that
* the file can be parsed for information*/
public class PreReqParserImpl extends Parser {
    private PreReqParser parser;
    public PreReqParserImpl(File file) throws IOException {
        super(file);
        this.parser = new PreReqParser(super.getPdfString());
    }
    public HashMap<CourseCode,DegreeRequirement> ParsePDF(){
        this.parser.generateTokens(this.parser.getSplitString());
        return parser.courseTokensToHashMap();
 
    }
}
