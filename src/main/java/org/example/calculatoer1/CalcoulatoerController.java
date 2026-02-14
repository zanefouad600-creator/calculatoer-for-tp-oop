package org.example.calculatoer1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CalcoulatoerController {

    @FXML private Label displayCurrent;
    @FXML private Label displayPrevious;

    private String operator = "";
    private double firstNumber = 0;
    private boolean startNewNumber = true;

    @FXML
    void handleNumber(ActionEvent event) {
        String number = ((Button) event.getSource()).getText();
        if (startNewNumber) {
            displayCurrent.setText(number);
            startNewNumber = false;
        } else {

            if (displayCurrent.getText().equals("0")) {
                displayCurrent.setText(number);
            } else {
                displayCurrent.setText(displayCurrent.getText() + number);
            }
        }
    }

    @FXML
    void handleOperation(ActionEvent event) {
        operator = ((Button) event.getSource()).getText();
        firstNumber = Double.parseDouble(displayCurrent.getText());
        displayPrevious.setText(firstNumber + " " + operator);
        startNewNumber = true;
    }

    @FXML
    void handleEquals(ActionEvent event) {
        if (operator.isEmpty()) return;

        double secondNumber = Double.parseDouble(displayCurrent.getText());
        double result = 0;

        switch (operator) {
            case "+" -> result = firstNumber + secondNumber;
            case "-" -> result = firstNumber - secondNumber;
            case "*" -> result = firstNumber * secondNumber;
            case "/" -> result = (secondNumber != 0) ? firstNumber / secondNumber : 0;
            case "mod" -> result = firstNumber % secondNumber;
        }

        displayPrevious.setText(firstNumber + " " + operator + " " + secondNumber + " =");
        displayCurrent.setText(String.valueOf(result));
        operator = "";
        startNewNumber = true;
    }

    @FXML
    void handleClear(ActionEvent event) {
        displayCurrent.setText("0");
        displayPrevious.setText("");
        firstNumber = 0;
        operator = "";
        startNewNumber = true;
    }

    @FXML
    void handleBackspace(ActionEvent event) {
        String current = displayCurrent.getText();
        if (current.length() > 1) {
            displayCurrent.setText(current.substring(0, current.length() - 1));
        } else {
            displayCurrent.setText("0");
            startNewNumber = true;
        }
    }

    @FXML
    void handleDecimal(ActionEvent event) {
        if (!displayCurrent.getText().contains(".")) {
            displayCurrent.setText(displayCurrent.getText() + ".");
            startNewNumber = false;
        }
    }

    @FXML
    void handleNegate(ActionEvent event) {
        double current = Double.parseDouble(displayCurrent.getText());
        displayCurrent.setText(String.valueOf(current * -1));
    }

    @FXML
    void handlePercent(ActionEvent event) {
        double current = Double.parseDouble(displayCurrent.getText());
        displayCurrent.setText(String.valueOf(current / 100));
        startNewNumber = true;
    }
}