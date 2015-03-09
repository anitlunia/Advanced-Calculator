package com.example.borja.AdvancedCalculator.ProgrammerCalculator;

import java.math.BigInteger;


/**
 * Created by jvilar on 14/01/15.
 */
public class ProgrammerArithmetic {
    final static int MAX_SCREEN_DIGITS = 8;
    public static enum Operations {

        Addition, Subtraction, Product, Division, LogicalAnd, LogicalOr
    }

    public static BigInteger operate (BigInteger arg1, Operations op, BigInteger arg2) {
        if (arg1 == null || arg2 == null || op == null)
            return null;
        BigInteger result = BigInteger.ZERO;
        switch (op) {
            case Addition:
                result = arg1.add(arg2);
                break;
            case Subtraction:
                result = arg1.subtract(arg2);
                break;
            case Product:
                result = arg1.multiply(arg2);
                break;
            case Division:
                if (arg2.equals(BigInteger.ZERO))
                    result = null;
                else
                    result = arg1.divide(arg2);
                break;
            case LogicalAnd:
                result = arg1.and(arg2);
                break;
            case LogicalOr:
                result = arg1.or(arg2);
                break;
        }
        return result;
    }

    public static String toString(BigInteger value, int radix, String errorString) {
        if (value == null)
            return errorString;
        String integerPart = value.toString(radix);
        int maxLength = value.signum() == -1 ? MAX_SCREEN_DIGITS + 1 : MAX_SCREEN_DIGITS;
        if (integerPart.length() > maxLength)
            return errorString;
        return integerPart;
    }
}
