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
        String nameAccess = new String();
        String nameReturn = new String();
        String nameOfMethod = new String();

        for (String names : firstPart) {
            if (Arrays.asList(modifiers).contains(names)) {
                nameAccess = names;
            } else if (Arrays.asList(returnTypes).contains(names)) {
                nameReturn = names;
            } else {
                nameOfMethod = names;
            }
        }
        List<MethodSignature.Argument> current = new ArrayList<>();
        if (st.hasMoreTokens()) {
            String[] secondPart = st.nextToken().split(",");
            for (String typo : secondPart) {
                String[] typos = typo.split(" ");
                MethodSignature.Argument temp = new MethodSignature.Argument(typos[0], typos[1]);
                current.add(temp);
            }
        }
        MethodSignature checkMethod = new MethodSignature(nameOfMethod, current);
        if (nameAccess.length() > 0) {
            checkMethod.setAccessModifier(nameAccess);
        } else {
            checkMethod.setAccessModifier(null);
        }
        if (nameReturn.length() > 0) {
            checkMethod.setReturnType(nameReturn);
        } else {
            checkMethod.setReturnType(null);
        }
        checkMethod.setMethodName(nameOfMethod);
        return checkMethod;

    }
}
