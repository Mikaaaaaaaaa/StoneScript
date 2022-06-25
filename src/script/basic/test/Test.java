package script.basic.test;

import script.basic.error.ScriptError;
import script.basic.structure.ScriptStructure;
import script.basic.structure.VariableStructure;

public class Test {


    public static void main(String[] args) {
        ScriptStructure scriptStructure = new ScriptStructure();

        //lines of code
        String line1 = "var a = 1";
        String line2 = "var b = 2";
        String line3 = "var c = a + b";

        //store lines of code in scriptStructure
        scriptStructure.processLine(line1, 1);
        scriptStructure.processLine(line2, 2);
        System.out.println(scriptStructure.processLine(line3, 3));

    }
}