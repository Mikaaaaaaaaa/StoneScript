package script.basic.help;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;
import script.basic.service.InstanceService;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface GeneralHelp {

    default boolean hasSpecialCharacters(String content) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (char c : content.toCharArray()) {
            if(alphabet.contains(String.valueOf(c))) continue;
            return true;
        }
        return false;
    }

    default int getCharCount(String content, char c) {
        Pattern pattern = Pattern.compile(String.valueOf(c));
        Matcher matcher = pattern.matcher(content);
        return (int) matcher.results().count();
    }

    default boolean isEven(int number)
    {
        return number % 2 == 0;
    }

    default String getStringBetweenChar(String d, char c) {
        int i = d.indexOf(c);
        return d.substring(i+1, d.indexOf(c, i+1));
    }
    default boolean isStringDigit(String string) {
        return NumberUtils.isCreatable(string);
    }

    default String[] singleStringArray(String input) {
        return new String[] {input};
    }

}
