package script.basic.syntax;

import script.basic.error.scriptErrors.SyntaxCharacterMissingError;
import script.basic.help.GeneralHelp;


public class SyntaxReviewer implements GeneralHelp {

    private String line;
    private int lineNumber;

    public void reviewSyntax()
    {
        if (line.startsWith("var")) {
            reviewVariable();
        }
    }

    private void reviewVariable()
    {
        if (this.getCharCount(this.line, '\"') % 2 != 0) {
            new SyntaxCharacterMissingError('\"', this.getLineNumber()).throwError();
        }
    }

    public int getLineNumber()
    {
        return lineNumber;
    }

    public void updateReviewer(String line, int lineNumber) {
        this.line = line;
        this.lineNumber = lineNumber;
    }
}
