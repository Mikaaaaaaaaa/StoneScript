package script.basic.error.scriptErrors;

import script.basic.error.ScriptError;

public class SyntaxCharacterMissingError implements ScriptError {

    private final char missingCharacter;
    private final int line;

    public SyntaxCharacterMissingError(char missingCharacter, int line) {
        this.missingCharacter = missingCharacter;
        this.line = line;
    }

    @Override
    public String message()
    {
        return "You have illegally forgotten a character, to protect the software, the process will be aborted.";
    }

    @Override
    public String description()
    {
        return "Before you start the program again, make sure that you have fixed the error, otherwise the program will quit again.";
    }

    @Override
    public String solution()
    {
        return "Go to the line: " + getLine() + " and make sure that you have the character (" + getChar() + ") in the line.";
    }

    @Override
    public int getLine()
    {
        return line;
    }

    public char getChar()
    {
        return missingCharacter;
    }
}
