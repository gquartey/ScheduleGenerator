package edu.swarthmore.cs71.starfruit.scraper.jparsec_work;

import edu.swarthmore.cs71.starfruit.scraper.TokenType;

public class MyToken {
    private String token_string;
    private TokenType tokenType;
    public MyToken(String token_string, TokenType tokenType) {
        this.token_string = token_string;
        this.tokenType = tokenType;
    }

    public String getToken_string() {
        return token_string;
    }

    public TokenType getTokenType() {

        return tokenType;
    }
}
