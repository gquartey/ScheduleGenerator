package edu.swarthmore.cs71.starfruit.scraper;

/*
* This implements our transcript parser, it takes in a pdf,
* extracts the string that represents the content of the transcript
* and parses the transcript, allowing us to return course objects
* that represent courses the user has already taken*/

import edu.swarthmore.cs71.starfruit.classes.course.Course;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class TranscriptParserImpl extends Parser{

    private TranscriptParser parser;
    public TranscriptParserImpl(File file) throws IOException {
        super(file);
        this.parser = new TranscriptParser(super.getPdfString());
    }
    public Collection<Course> ParsedPDF(){
        this.parser.simpleLexer(parser.getSplitString());
        Collection<CourseToken> tokencourses = this.parser.createClassTokensFromTokens(parser.getTokenList());
        parser.setCourseBank(tokencourses);
        this.parser.courseTokenToCourseObject();
        return this.parser.getCourses();

    }

}

