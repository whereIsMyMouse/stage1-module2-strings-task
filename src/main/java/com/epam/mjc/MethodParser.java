package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        ArrayList<String> modifiers = new ArrayList<>(Arrays.asList("private", "public"));
        ArrayList<String> returnTypes = new ArrayList<>(Arrays.asList("String", "void", "float", "int", "double"));
        StringTokenizer st = new StringTokenizer(signatureString, "(.)");

        String[] firstPart = st.nextToken().split(" ");

        List<MethodSignature.Argument> current = new ArrayList<>();
        if (st.hasMoreTokens()) {
            StringTokenizer st2 = new StringTokenizer(st.nextToken(),",");
            while (st2.hasMoreTokens()){

                String[] currentPart = st2.nextToken().split(" ");
                MethodSignature.Argument temp = currentPart.length == 2?
                        new MethodSignature.Argument(currentPart[0], currentPart[1]) :
                        new MethodSignature.Argument(currentPart[1], currentPart[2]);
                current.add(temp);
            }
        }

        MethodSignature checkMethod = new MethodSignature("currentMethod", current);
        for (String names : firstPart) {
            if (modifiers.contains(names)) {
                checkMethod.setAccessModifier(names);
            } else if (returnTypes.contains(names)) {
                checkMethod.setReturnType(names);
            } else {
                checkMethod.setMethodName(names);
            }
        }

        return checkMethod;

    }
}
