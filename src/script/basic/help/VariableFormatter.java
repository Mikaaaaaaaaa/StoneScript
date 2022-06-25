package script.basic.help;

import script.basic.service.InstanceService;
import script.basic.service.StorageService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class VariableFormatter implements GeneralHelp {

    private final StorageService storageService;

    private String line;

    public VariableFormatter()
    {
        this.storageService = InstanceService.getInstanceService().getStorageService();
    }

    /**
     * Receives input from the script and formats it to be used in the program.
     * Can not handle [null] input.
     *
     * @return the formatted line for the output.
     */
    public String formats()
    {
        if(!line.contains("=")) return line;
        if(line.split("=")[1].contains("+")) {
            return listToString(compartments(line.split("=")[1].split("\\+")));
        }
        return listToString(compartments(this.singleStringArray(line.split("=")[1])));
    }

    private List<String> compartments(String[] blocks) {
        List<String> newCompartments = new ArrayList<>();
        for (String block : blocks) {
            if(block.startsWith("var")) continue;
            newCompartments.add(block.contains("\"") ?
                    getStringBetweenChar(block, '\"') :
                    isStringDigit(block.replace(" ", "")) ?
                            block.replace(" ", "") :
                            getValue(block.replace(" ", "")));
        }
        return newCompartments;
    }

    /**
     * Converts a list of strings to a one line string.
     *
     * @param list the list of strings.
     * @return the value of the one line string.
     */
    private String listToString(List<String> list)
    {
        StringBuilder sb = new StringBuilder();
        for (String s1 : list) {
            sb.append(s1);
        }
        return sb.toString();
    }

    /**
     * Gets the value of a variable and automatically handles the {@link ClassNotFoundException}.
     *
     * @param variable the variable to get the value of.
     * @return the value of the variable.
     */
    public String getValue(String variable)
    {
        try {
            return (String) storageService.getValue(variable, storageService.getType(variable));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Update the line to be formatted.
     *
     * @param line the line to be formatted.
     */
    public void setLine(String line)
    {
        this.line = line;
    }
}
