package cz.nevimjak.mathexpressionsolver.structure;

import java.util.Optional;
import java.util.Stack;

public enum Operation {
    /**
     * oddělení 2 hodnot
     */
    DELIMETER(",", 0, 0) {
        public double calc(Stack<Double> operands) {
            return 0;
        }
    },
    /**
     * Sčítání
     */
    PLUS("+", 2, 1) {
        public double calc(Stack<Double> operands) throws Exception {
            return operands.pop() + operands.pop();
        }
    },
    /**
     * Odčítání
     */
    MINUS("-", 2, 1) {
        public double calc(Stack<Double> operands) throws Exception {
            return - operands.pop() + operands.pop();
        }
    },
    /**
     * Násobení
     */
    TIMES("*", 2, 2) {
        public double calc(Stack<Double> operands) {
            return operands.pop() * operands.pop();
        }
    },
    /**
     * Dělění
     */
    DIVIDE("/", 2, 2) {
        public double calc(Stack<Double> operands) throws Exception {
            if(operands.peek() == 0) {
                throw new Exception(ErrorEnum.DIVIDE_ZERO.getErrorString());
            }

            double tmp = operands.pop();
            return operands.pop() / tmp;
        }
    },
    /**
     * Mocnina
     */
    POWER("^", 2, 3) {
        public double calc(Stack<Double> operands) throws Exception {
            double tmp = operands.pop();

            if(tmp == 0 && operands.peek() == 0) {
                throw new Exception(ErrorEnum.NOT_DEFINED_VALUE.getErrorString());
            }

            return Math.pow(operands.pop(), tmp);
        }
    },
    /**
     * Otevřená závorka
     */
    OPEN_BRACKET("(", 1, 3) {
        public double calc(Stack<Double> operands) {
            return operands.pop();
        }
    },
    /**
     * Uzavírací závorka
     */
    END_BRACKET(")", 1, 3) {
        public double calc(Stack<Double> operands) {
            return operands.pop();
        }
    },
    /**
     * Sinus
     */
    SIN("sin", 1, 4) {
        public double calc(Stack<Double> operands) {
            return Math.sin(operands.pop());
        }
    },
    /**
     * Cosinus
     */
    COS("cos", 1, 4) {
        public double calc(Stack<Double> operands) {
            return Math.cos(operands.pop());
        }
    },
    /**
     * Tangens
     */
    TAN("tan", 1, 4) {
        public double calc(Stack<Double> operands) throws Exception {
            try {
                return Math.tan(operands.pop());
            } catch (Exception ex) {
                throw new Exception(ErrorEnum.NOT_DEFINED_VALUE.getErrorString());
            }
        }
    },
    /**
     * Logaritmus
     */
    LOG("log", 1, 4) {
        public double calc(Stack<Double> operands) throws Exception {

            if(operands.peek() <= 0) {
                throw new Exception(ErrorEnum.NOT_DEFINED_VALUE.getErrorString());
            }

            return Math.log10(operands.pop());
        }
    },
    /**
     * Exponenciální funkce
     */
    EXP("exp", 1, 4) {
        public double calc(Stack<Double> operands) {
            return Math.exp(operands.pop());
        }
    },
    /**
     * Druhá odmocnina
     */
    SQRT("sqrt", 1, 4) {
        public double calc(Stack<Double> operands) throws Exception {

            if(operands.peek() < 0) {
                throw new Exception(ErrorEnum.NOT_DEFINED_VALUE.getErrorString());
            }

            return Math.sqrt(operands.pop());
        }
    },
    /**
     * Minimální hodnota
     */
    MIN("min", 2, 5) {
        public double calc(Stack<Double> operands) {
            return Math.min(operands.pop(), operands.pop());
        }
    },
    /**
     * Maxilání hodnota
     */
    MAX("max", 2, 5) {
        public double calc(Stack<Double> operands) {
            return Math.max(operands.pop(), operands.pop());
        }
    },
    /**
     * Největší společný dělitel
     */
    NSD("nsd", 2, 5) {
        public double calc(Stack<Double> operands) throws Exception {

            if(operands.peek() % 1 != 0) {
                throw new Exception(ErrorEnum.ONLY_INTEGERS_ALLOWED.getErrorString());
            }

            int number1 = operands.pop().intValue();

            if(operands.peek() % 1 != 0) {
                throw new Exception(ErrorEnum.ONLY_INTEGERS_ALLOWED.getErrorString());
            }

            int number2 = operands.pop().intValue();

            return Operation.gcd(number1, number2);
        }
    },
    /**
     * Nejmenší společný násobek
     */
    NSN("nsn", 2, 5) {

        public double calc(Stack<Double> operands) throws Exception {

            if(operands.peek() % 1 != 0) {
                throw new Exception(ErrorEnum.ONLY_INTEGERS_ALLOWED.getErrorString());
            }

            int number1 = operands.pop().intValue();

            if(operands.peek() % 1 != 0) {
                throw new Exception(ErrorEnum.ONLY_INTEGERS_ALLOWED.getErrorString());
            }

            int number2 = operands.pop().intValue();

            return Operation.lcm(number1, number2);
        }
    };

    /**
     * Metoda vypočítá výsledek dané operace
     * @param operands operand
     * @return výsledek
     * @throws Exception
     */
    public abstract double calc(Stack<Double> operands) throws Exception;

    private final int parameterCount;
    private final String operationString;
    private final int depthLevel;

    /**
     *  Vytvoření instance operace
     * @param operationString Označení operace
     * @param parameterCount kolik parametrů si vyžaduje daná funkce
     * @param depthLevel přednost operace
     */
    Operation(String operationString, int parameterCount, int depthLevel) {
        this.operationString = operationString;
        this.parameterCount = parameterCount;
        this.depthLevel = depthLevel;
    }

    public static Operation parse(String operationString) {
        for(Operation operation : Operation.values()) {
            if (operationString.equals(operation.operationString)) {
                return operation;
            }
        }

        return null;
    }

    public static Operation parse(char operationCharacter) {
        return parse(String.valueOf(operationCharacter));
    }

    public int getDepthLevel() {
        return this.depthLevel;
    }

    public int getParameterCount() {
        return this.parameterCount;
    }

    /**
     * Největší společný dělitel
     * @param number1 první hodnota
     * @param number2 druhá hodnota
     * @return největší společný dělitel number1 a number2
     */
    private static int gcd(int number1, int number2) {
        if (number1 == 0 || number2 == 0) {
            return number1 + number2;
        } else {
            int absNumber1 = Math.abs(number1);
            int absNumber2 = Math.abs(number2);
            int biggerValue = Math.max(absNumber1, absNumber2);
            int smallerValue = Math.min(absNumber1, absNumber2);
            return gcd(biggerValue % smallerValue, smallerValue);
        }
    }

    /**
     * Nejmenší společný násobek
     * @param number1 první hodnota
     * @param number2 druhá hodnota
     * @return nejmenší společný násobek number1 a number2
     */
    private static int lcm(int number1, int number2) {
        if (number1 == 0 || number2 == 0)
            return 0;
        else {
            int gcd = gcd(number1, number2);
            return Math.abs(number1 * number2) / gcd;
        }
    }
}
