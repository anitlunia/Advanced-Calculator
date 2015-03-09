package com.example.borja.AdvancedCalculator;

import java.math.BigDecimal;
import java.math.MathContext;


/**
 * Created by jvilar on 14/01/15.
 */
public class CalculatorArithmetic {
    final static int MAX_SCREEN_DIGITS = 8;
    public static enum Operations {
        Addition, Subtraction, Product, Division
    }

    public static enum Functions {
        Sin, Cos, Tan, Sqrt,
        DegSin, DegCos, DegTan
    }

    public static BigDecimal operate (BigDecimal arg1, Operations op, BigDecimal arg2) {
        if (arg1 == null || arg2 == null || op == null)
            return null;
        BigDecimal result = BigDecimal.ZERO;
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
                if (arg2.equals(BigDecimal.ZERO))
                    result = null;
                else
                    result = arg1.divide(arg2, MathContext.DECIMAL64);
                break;
        }
        return result;
    }

    public static BigDecimal applyFunction (Functions function, BigDecimal argument) {
        if (argument == null || function == null)
            return null;
        double value = argument.doubleValue();
        double result = 0;
        switch (function) {
            case Sin:
                result = Math.sin(value);
                break;
            case DegSin:
                result = Math.sin(value * Math.PI / 180);
                break;
            case Cos:
                result = Math.cos(value);
                break;
            case DegCos:
                result = Math.cos(value * Math.PI / 180);
                break;
            case Tan:
                result = Math.tan(value);
                break;
            case DegTan:
                result = Math.tan(value * Math.PI / 180);
                break;
            case Sqrt:
                result = Math.sqrt(value);
                break;
        }
        if (Double.isInfinite(result) || Double.isNaN(result))
            return null;
        return new BigDecimal(result);
    }

    public static String toString(BigDecimal value, String errorString) {
        if (value == null)
            return errorString;
        String integerPart = value.toBigInteger().toString();
        final int maxIntegerLength = MAX_SCREEN_DIGITS + (value.signum() == -1 ? 1 : 0);
        final int integerLength = integerPart.length();
        if (integerLength > maxIntegerLength) {
            return errorString;
        }
        String completeNumber = value.toPlainString();
        int maxLength = maxIntegerLength;
        final boolean hasPoint = value.scale() > 0 && integerLength != maxIntegerLength;
        if (hasPoint)
            maxLength++;
        int finalLength = Math.min(completeNumber.length(), maxLength);
        if (hasPoint) { // Trim trailing zeroes
            //noinspection StatementWithEmptyBody
            for (; finalLength > integerLength && completeNumber.charAt(finalLength - 1) == '0';
                 finalLength--)
                ;
            if (completeNumber.charAt(finalLength - 1) == '.')
                finalLength --;
        }
        return completeNumber.substring(0, finalLength);
    }
}
