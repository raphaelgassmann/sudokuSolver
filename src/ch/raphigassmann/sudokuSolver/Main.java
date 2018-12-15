package ch.raphigassmann.sudokuSolver;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.EventHandler;


import java.util.Arrays;



public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui.fxml"));
        primaryStage.setTitle("Sudoku Solver");
        primaryStage.setScene(new Scene(root, 500, 275));

        VBox vbNumbers = new VBox();

        String[] rows = { "a", "b", "c", "d", "e", "f", "g", "h", "i"};
        int columns = 9; //actually 9

        for (int i = 0; i < rows.length; i++) {

            HBox hbRow = new HBox(); //Creating HB
            hbRow.setId("hbox"+rows[i]);

            for (int j = 1; j < columns+1; j++) {
                TextField cell = new TextField(rows[i]+""+j);
                cell.setMaxWidth(27);
                cell.setText("");
                cell.setId("txt"+rows[i]+""+j);
                cell.setAlignment(Pos.CENTER);
                hbRow.getChildren().add(cell);
                if ((j % 3) == 0 && j != columns)
                {
                    Label lblSeparator = new Label(rows[i]+""+j);
                    lblSeparator.setText("|");
                    lblSeparator.setPadding(new Insets(0, 5, 0, 5));
                    hbRow.getChildren().add(lblSeparator);
                }
            }
            vbNumbers.getChildren().add(hbRow);
            int row = Arrays.asList(rows).indexOf(rows[i])+1;
            if(Arrays.asList(rows).indexOf(rows[i]) !=0 &&(row % 3) == 0 && row != rows.length){
                Label lblSeparatorVertical = new Label(rows[i]);
                lblSeparatorVertical.setText("-----------------------------------------");
                vbNumbers.getChildren().add(lblSeparatorVertical);
            }

        }

        Button start;
        start = new Button();
        start.setText("Solve");
        start.setPrefSize(225, 25);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                int input[][] = new int[8][8];

                for (int i = 0; i < rows.length; i++) {

                    for (int j = 1; j < columns; j++) {
                        Node cell = vbNumbers.lookup("#txt" + rows[i] + "" + j);
                        TextField txtCell = (TextField) cell;

                        if (txtCell.getText().length() == 1) {
                            input[i][j - 1] = Integer.parseInt(txtCell.getText());
                            //System.out.println(input[i][j - 1]);
                        }
                    }
                }
                Controller.solve(input);
            }
        });

        vbNumbers.setAlignment(Pos.CENTER);
        vbNumbers.setPadding(new Insets(20, 20, 20, 20));
        vbNumbers.getChildren().add(start);

        StackPane layout = new StackPane(); //Method ConnectionStart() need knowledge about this
        layout.getChildren().add(vbNumbers);

        Scene scene = new Scene(layout);

        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
