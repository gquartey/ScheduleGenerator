package edu.swarthmore.cs71.starfruit.scraper;

import java.util.ArrayList;

public class TokenBank {

    private ArrayList<BuilderToken> tokenList;
    private int size;
    private BuilderToken lastToken;

    public TokenBank() {
        this.tokenList = new ArrayList<>();
        this.size = 0 ;
        this.lastToken = null;
    }

    public ArrayList<BuilderToken> getTokenList() {
        return tokenList;
    }

    // this takes in a string and determines which token it should be and then adds it to the list
    public void add(BuilderToken token){
        this.tokenList.add(token);
        this.lastToken = token;
        this.size++;
    }
    public void done(){
        this.tokenList.remove(tokenList.size()-1);
        this.tokenList.remove(tokenList.size()-1);
    }

    public TokenType getLastToken() {
        if(lastToken == null){
            return null;
        }
        return lastToken.tokenType;
    }
    public void printStringTokens(){
        for(BuilderToken i: this.tokenList){
            System.out.println(i.tokenString);
        }
    }
    public int size(){
        return this.size;
    }


}
