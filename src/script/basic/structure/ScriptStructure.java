package script.basic.structure;

import java.util.ArrayList;
import java.util.List;

public class ScriptStructure {

    private List<BasicStructure> structureList;

    public ScriptStructure()
    {
        this.structureList = new ArrayList<>();

        /**
         *  Add {@link BasicStructure} classes to make them accessible and executable by the script.
         */
        this.structureList.add(new VariableStructure());
    }

    public String processLine(String line, int lineNumber) {
        BasicStructure structure = getStructure(line);
        return structure.process(line, lineNumber);
    }

    public BasicStructure getStructure(String line)
    {
        String key = line.split(" ")[0];
        for (BasicStructure basicStructure : structureList) {
            if(basicStructure.isInStructureString(key)) return basicStructure;
        }
        return null;
    }

}
