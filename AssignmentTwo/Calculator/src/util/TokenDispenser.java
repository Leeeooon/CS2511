package util;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * This class extends java.io.StreamTokenizer for the Calculator application.
 * It provides methods for advancing through expression strings and returning
 * the relevant tokens.
 * It also provides methods for testing for a token's type (number, operator, 
 * etc.)
 * @author tcolburn
 */
public class TokenDispenser extends StreamTokenizer {
    
    /**
     * Creates a token dispenser for a given string input.
     * @param input the string input
     */
    public TokenDispenser(String input) {
        super(new StringReader(input));
        ordinaryChar((int)'/');  // to keep '/' from being interpreted as a comment character
        ordinaryChar((int)'-');  // to keep '-' from being interpreted as unary minus
    }
    
    /**
     * Parses the next token in the input.
     */
    public void advance() {
        try {
            nextToken();                       // nextToken is inherited from the StreamTokenizer class
        }
        catch(IOException ex) {
            throw new RuntimeException("Error calling nextToken in TokenDispenser");
        }
    }
    
    /**
     * Returns the most recently parsed token in the input.
     * @return an object that is either a number, a word (string of
     * alphabetic characters), a single non-alphabetic character, or
     * a string indicating EOF
     */
    public Object getToken() {
        if (tokenIsEOF()) {
            return EOF;
        }
        if (tokenIsNumber()) {
            return getNumber();
        }
        if (tokenIsWord()) {
            return getWord();
        }
        return (char) ttype;
    }
    
    /**
     * Tests for whether the most recently parsed token is a number.
     * @return true if the most recently parsed token is a number, or
     * false otherwise
     */
    public boolean tokenIsNumber() {
        return ttype == StreamTokenizer.TT_NUMBER;
    }
    
    /**
     * Returns the most recently parsed token as a number.
     * @return a number
     */
    public double getNumber() {
        return nval;
    }
    
    /**
     * Tests for whether the most recently parsed token is a word
     * (string of alphabetic characters).
     * @return true if the most recently parsed token is a word, or
     * false otherwise
     */
    public boolean tokenIsWord() {
        return ttype == StreamTokenizer.TT_WORD;
    }
    
    /**
     * Returns the most recently parsed token as a word.
     * @return a string of alphabetic characters
     */
    public String getWord() {
        return sval;
    }
    
    /**
     * Tests for whether the most recently parsed token is EOF 
     * (end of file).
     * @return true if the most recently parsed token is EOF, or
     * false otherwise
     */
     public boolean tokenIsEOF() {
        return ttype == StreamTokenizer.TT_EOF;
    }
    
    /**
     * Tests for whether the most recently parsed token is a 
     * binary operator (+, -, *, /).
     * @return true if the most recently parsed token is an operator, or
     * false otherwise
     */
    public boolean tokenIsOperator() {
        return (char)ttype == '+' ||
               (char)ttype == '-' ||
               (char)ttype == '*' ||
               (char)ttype == '/';
    }
    
    /**
     * Tests for whether the most recently parsed token is a 
     * left parenthesis.
     * @return true if the most recently parsed token is a left parenthesis, or
     * false otherwise
     */
    public boolean tokenIsLeftParen() {
        return (char)ttype == '(';
    }
    
    /**
     * Tests for whether the most recently parsed token is a 
     * right parenthesis.
     * @return true if the most recently parsed token is a right parenthesis, or
     * false otherwise
     */
    public boolean tokenIsRightParen() {
        return (char)ttype == ')';
    }
    
    /**
     * Returns the most recently parsed token as a single character.
     * @return a single character
     */
    public char getChar() {
        return (char) ttype;
    }
    
    public static final String EOF = "end of input";
}