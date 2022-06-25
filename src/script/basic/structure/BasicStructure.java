package script.basic.structure;

public interface BasicStructure {

    String[] structureString();

    String process(String line, int lineNumber);

    default boolean isInStructureString(String key) {
        for (String s : this.structureString()) {
            if(s.equals(key)) return true;
        }
        return false;
    }
}
