package script.basic.error;

public interface ScriptError {

    String message();

    String description();

    String solution();

    int getLine();

    default void throwError() {
        System.err.println(message() + description() + solution());
        System.exit(0);
    }

}
