package com.application.tictactoe.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isMoveFormatValid(String move) {
        if (move == null) {
            return false;
        }
        return move.matches("^[0-9]+,[0-9]+$");
    }
}
