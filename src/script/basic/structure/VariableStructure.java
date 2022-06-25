package script.basic.structure;


import script.basic.help.GeneralHelp;
import script.basic.help.VariableFormatter;
import script.basic.service.InstanceService;
import script.basic.syntax.SyntaxReviewer;

public class VariableStructure implements BasicStructure, GeneralHelp {

    /** VARIABLES **/

    /** var name  = "steinzeiten";      **/
    String[] compartments;

    /** var name                        **/
    String[] baseSubdivision;

    /** "steinzeiten";                       **/
    String[] valueSubdivision;

    VariableFormatter variableFormatter;

    SyntaxReviewer syntaxReviewer;

    public VariableStructure() {
        variableFormatter = new VariableFormatter();
        syntaxReviewer = new SyntaxReviewer();
    }

    @Override
    public String process(String line, int lineNumber) {
        syntaxReviewer.updateReviewer(line, lineNumber);
        syntaxReviewer.reviewSyntax();
        variableFormatter.setLine(line);
        initLine(line);
        if(!isLineUsable()) return "error";
        String currentVariable = compartments[1];
        try {
            InstanceService.getInstanceService().getStorageService().addValue(baseSubdivision[1], currentVariable.replaceAll("\"", ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return variableFormatter.formats();
    }

    private void initLine(String line) {
        compartments = line.split("=", 2);
        baseSubdivision = compartments[0].split(" ");
        valueSubdivision = compartments[1].split(" ");
    }

    private boolean isLineUsable() {
        return compartments.length == 2 && baseSubdivision.length == 2 && !hasSpecialCharacters(baseSubdivision[1]) && checkChar('"') && checkChar('\'');
    }

    private boolean checkChar(char c) {
        return !(compartments[1].contains("\'") && !isEven(getCharCount(compartments[1], c)));
    }

    private void debug() {
        System.out.println(compartments.length + " should be 2");
        System.out.println(baseSubdivision.length + " should be 2");
        System.out.println(!hasSpecialCharacters(baseSubdivision[1]) + " should be true");
        System.out.println(checkChar('"') + " should be true");
        System.out.println(checkChar('\'') + " should be true");
    }

    @Override
    public String[] structureString() {
        return new String[] {"var"};
    }

}
