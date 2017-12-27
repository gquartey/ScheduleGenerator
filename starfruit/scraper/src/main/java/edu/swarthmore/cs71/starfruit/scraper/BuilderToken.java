package edu.swarthmore.cs71.starfruit.scraper;

/*This is the buildertoken class
* Tokens represents subsets of information needed to build
* classes. By classifying tokens it makes it easier to create courses
* that we'll need to determine information from the string passed in.*/
public class BuilderToken {
    protected String tokenString;
    protected TokenType tokenType;
    /* a token should not be declared as a buildertoken because then it will not be
    * uphold the type system, should be declare explicitly as a subclass, and type can
    * not be changed once a token has been made
    * */
    // TODO: add token type to this constructor and get rid of subclasses
    public BuilderToken(String sequence) {
        this.tokenString = sequence;
        this.tokenType = null;
    }

    public String returnString(){
        return this.tokenString;
    }
    public TokenType returnType(){return this.tokenType;}

    /* subclasses represent all the different kinds of token
    *  a string can be, by labeling the strings as token
    *  it makes it easy to organize them and use them
    *  to make complete course objects
    * */
    public static class CourseDays extends BuilderToken{
        public CourseDays(String sequence) {
            super(sequence);
            this.tokenType = TokenType.MeetingDays;
        }
    }
    public static class Time extends BuilderToken{
        public Time(String sequence) {
            super(sequence);
            this.tokenType = TokenType.Time;
        }
    }
    public static class Or extends BuilderToken{
        public Or(String sequence) {
            super(sequence);
            this.tokenType = TokenType.Or;
        }
    }
    public static class OpenParen extends BuilderToken{
        public OpenParen(String sequence) {
            super(sequence);
            this.tokenType = TokenType.OpenParen;
        }
    }
    public static class ClosedParen extends BuilderToken{
        public ClosedParen(String sequence) {
            super(sequence);
            this.tokenType = TokenType.ClosedParen;
        }
    }
    public static class DescripComp extends BuilderToken{
        public DescripComp(String sequence) {
            super(sequence);
            this.tokenType = TokenType.DescripComp;
        }
    }
    public static class PreReqEnd extends BuilderToken{
        public PreReqEnd(String sequence) {
            super(sequence);
            this.tokenType = TokenType.PreReqEnd;
        }
    }
    public static class PreReq extends BuilderToken{
        public PreReq(String sequence) {
            super(sequence);
            this.tokenType = TokenType.PreReq;
        }
    }
    public static class Season extends BuilderToken {
        public Season(String sequence) {
            super(sequence);
            this.tokenType = TokenType.Season;
        }
    }
    public static class Year extends BuilderToken {
        public Year(String sequence) {
            super(sequence);
            this.tokenType = TokenType.Year;
        }
    }
    public static class Dept extends BuilderToken {
        public Dept(String sequence) {
            super(sequence);
            this.tokenType = TokenType.DEPT;
        }
    }
    public static class CourseNumber extends BuilderToken {
        public CourseNumber(String sequence) {
            super(sequence);
            this.tokenType = TokenType.CourseNumber;
        }
    }
    public static class ClassNameComponent extends BuilderToken {
        public ClassNameComponent(String sequence) {
            super(sequence);
            this.tokenType = TokenType.NameComponent;
        }
    }
    public static class Credits extends BuilderToken {
        public Credits(String sequence) {
            super(sequence);
            this.tokenType = TokenType.Credits;
        }
    }
    public static class Grade extends BuilderToken {
        public Grade(String sequence) {
            super(sequence);
            this.tokenType = TokenType.Grade;
        }
    }
}

