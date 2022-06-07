module cz.nevimjak.mathexpressionsolver {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens cz.nevimjak.mathexpressionsolver to javafx.fxml;
    exports cz.nevimjak.mathexpressionsolver.ui;
}