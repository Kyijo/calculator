package cz.nevimjak.mathexpressionsolver.ui;

import cz.nevimjak.mathexpressionsolver.parser.ExpressionParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class CalculatorController {

    @FXML
    public TextField inputTextField;
    @FXML
    public TextField outputTextField;
    @FXML
    public TextArea resultListArea;

    private ExpressionParser expressionParser;

    private static DecimalFormat decimalFormat;

    @FXML
    public void initialize() {
        expressionParser = new ExpressionParser();
        decimalFormat = new DecimalFormat("#.###############");
    }

    /**
     * Metoda pro získání výsledku zadaného výrazu
     */
    @FXML
    public void handleCalculateButton() {

        try {
            double result = expressionParser.evaluate(inputTextField.getText());
            String resultString = decimalFormat.format(result);
            if(resultString.equals("-0")) {
                resultString = "0";
            }
            outputTextField.setText(resultString);

            if(inputTextField.getText().length() != 0) {
                if(resultListArea.getText().length() != 0) {
                    resultListArea.appendText(String.format("%n%n%s%n> %s", inputTextField.getText(), resultString));
                } else {
                    resultListArea.appendText(String.format("%s%n> %s", inputTextField.getText(), resultString));
                }
            }
        } catch (Exception ex) {
            outputTextField.setText(ex.getMessage());
            //ex.printStackTrace();
        }
    }

    @FXML
    public void handleButton0() {
        insert("0");
    }

    @FXML
    public void handleButton1() {
        insert("1");
    }

    @FXML
    public void handleButton2() {
        insert("2");
    }

    @FXML
    public void handleButton3() {
        insert("3");
    }

    @FXML
    public void handleButton4() {
        insert("4");
    }

    @FXML
    public void handleButton5() {
        insert("5");
    }

    @FXML
    public void handleButton6() {
        insert("6");
    }

    @FXML
    public void handleButton7() {
        insert("7");
    }

    @FXML
    public void handleButton8() {
        insert("8");
    }

    @FXML
    public void handleButton9() {
        insert("9");
    }

    @FXML
    public void handleButtonPoint() {
        insert(".");
    }

    @FXML
    public void handleButtonPlus() {
        insert("+");
    }

    @FXML
    public void handleButtonMinus() {
        insert("-");
    }

    @FXML
    public void handleButtonMultiply() {
        insert("*");
    }

    @FXML
    public void handleButtonDivide() {
        insert("/");
    }

    @FXML
    public void handleButtonSin() {
        insert("sin(");
    }

    @FXML
    public void handleButtonCos() {
        insert("cos(");
    }

    @FXML
    public void handleButtonTan() {
        insert("tan(");
    }

    @FXML
    public void handleButtonLog() {
        insert("log(");
    }

    @FXML
    public void handleButtonExp() {
        insert("exp(");
    }

    @FXML
    public void handleButtonSqrt() {
        insert("sqrt(");
    }

    @FXML
    public void handleButtonMin() {
        insert("min(");
    }

    @FXML
    public void handleButtonMax() {
        insert("max(");
    }

    @FXML
    public void handleButtonNsn() {
        insert("nsn(");
    }

    @FXML
    public void handleButtonNsd() {
        insert("nsd(");
    }

    @FXML
    public void handleButtonBracketOpen() {
        insert("(");
    }

    @FXML
    public void handleButtonBracketClose() {
        insert(")");
    }

    @FXML
    public void handleButtonDelimeter() {
        insert(",");
    }

    private void insert(String text) {

        this.inputTextField.appendText(text);
    }

}
