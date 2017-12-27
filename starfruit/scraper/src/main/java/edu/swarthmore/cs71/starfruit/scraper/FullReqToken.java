package edu.swarthmore.cs71.starfruit.scraper;

public class FullReqToken {
    private String dept;
    private String CourseNum;
    private int AmtOfReqs;

    public FullReqToken() {
        this.dept = null;
        this.CourseNum = null;
        this.AmtOfReqs = 0;
    }
    public void addToken(BuilderToken token){
        if(token != null){
            if(token.returnType().equals(TokenType.DEPT) && this.dept.equals(null)){

            }
        }
    }
}
