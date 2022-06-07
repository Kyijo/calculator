package cz.nevimjak.mathexpressionsolver.parser;

import cz.nevimjak.mathexpressionsolver.structure.ErrorEnum;
import cz.nevimjak.mathexpressionsolver.structure.Operation;

import java.text.DecimalFormat;
import java.util.Stack;

public class ExpressionParser {
    /**
     * Metoda pro ověřování výsledků pro testování
     * @param expression výraz
     * @return výsledek výrazu
     */

    public static double calculateDouble(String expression) {
        try {
            return evaluate(expression);
        } catch (Exception ex) {
            System.out.println("Vyskytla se chyba");
        }

        return 0;
    }

    /**
     * Metoda pro řešení výrazů
     * @param expression výraz
     * @return výsledek výrazu
     * @throws Exception
     */
    public static double evaluate(String expression) throws Exception {
        char[] tokens = expression.toCharArray();

        Stack<Double> values = new Stack<Double>();

        Stack<Operation> ops = new Stack<Operation>();

        for (int i = 0; i < tokens.length; i++) {

            if (tokens[i] == ' ') {
                continue;
            }

            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuffer sbuf = new StringBuffer();

                while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')) {
                    sbuf.append(tokens[i]);
                    i++;
                }

                values.push(Double.parseDouble(sbuf.toString()));
                i--;

            } else if(tokens[i] >= 'a' && tokens[i] <= 'z') {
                StringBuffer sbuf = new StringBuffer();

                while(i < tokens.length && tokens[i] >= 'a' && tokens[i] <= 'z') {
                    sbuf.append(tokens[i]);
                    i++;
                }

                Operation op = Operation.parse(sbuf.toString());
                if(op == null) {
                    throw new Exception(ErrorEnum.NOT_VALID_EXPRESSION.getErrorString());
                }

                ops.push(op);
                i--;

            } else {
                Operation op = Operation.parse(tokens[i]);

                if (op != null) {
                    if (op == Operation.OPEN_BRACKET) {
                        ops.push(op);
                    } else if (op == Operation.END_BRACKET) {

                        while (ops.peek() != Operation.OPEN_BRACKET) {
                            calculate(ops, values);
                        }

                        ops.pop();

                    } else {
                        while (!ops.empty() && hasPrecedence(op, ops.peek())) {
                            calculate(ops, values);
                        }

                        ops.push(op);
                    }

                }
            }
        }

        while (!ops.empty()) {
            calculate(ops, values);
        }

        if(values.size() == 1) {
            return values.pop();
        } else {
            throw new Exception(ErrorEnum.NOT_VALID_EXPRESSION.getErrorString());
        }
    }

    /**,
     *  Výpočet operace
     * @param ops operátorya
     * @param values hodnoty
     * @throws Exception
     */
    public static void calculate(Stack<Operation> ops, Stack<Double> values) throws Exception {

        if(!ops.empty() && ops.peek() == null) {
            ops.pop();
            return;
        }

        if(ops.peek() == Operation.DELIMETER) {
            ops.pop();
        } else if(ops.peek().getParameterCount() <= values.size()) {
            values.push(ops.pop().calc(values));
        } else {
            throw new Exception(ErrorEnum.PARAMETER_COUNT_ERROR.getErrorString());
        }
    }

/**
     * Metoda pro určení přednosti operací
     * @param op1 První operátor
     * @param op2 Druhý operátor
     * @return stav nadřazení
     */

    public static boolean hasPrecedence(Operation op1, Operation op2) {
        if (op2 == Operation.OPEN_BRACKET || op2 == Operation.END_BRACKET) {
            return false;
        }

        if(op1.getDepthLevel() > op2.getDepthLevel()) {
            return false;
        } else {
            return true;
        }

    }
}
