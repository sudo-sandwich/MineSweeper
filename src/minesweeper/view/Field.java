package minesweeper.view;

import javafx.beans.binding.IntegerBinding;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import minesweeper.model.FieldModel;
import minesweeper.model.GameConstants;

public class Field extends GridPane {
    
    FieldModel fieldModel;
    
    public Field(GameConstants gameConstants, IntegerBinding size) {
        newGame(gameConstants, size);
    }
    
    public void newGame(GameConstants gameConstants, IntegerBinding size) {
        fieldModel = new FieldModel(gameConstants);
        
        for (int x = 0; x < fieldModel.cells().size(); x++) {
            for (int y = 0; y < fieldModel.cells().get(x).size(); y++) {
                Cell cell = new Cell(size, fieldModel.cells().get(x).get(y));
                setMargin(cell, new Insets(1));
                add(cell, x, y);
            }
        }
        
        fieldModel.gameOverProperty().addListener(o -> {
            if (fieldModel.getWin()) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Game Won");
                alert.setHeaderText(null);
                alert.setContentText("You win!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Game Lost");
                alert.setHeaderText(null);
                alert.setContentText("You lose.");
                alert.showAndWait();
            }
        });
    }
    
    public FieldModel model() {
        return fieldModel;
    }
}