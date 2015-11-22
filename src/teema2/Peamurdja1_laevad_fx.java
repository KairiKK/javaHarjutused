package teema2;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Joonista laevade pommitamine kasutades JavaFXi.
 */
public class Peamurdja1_laevad_fx extends Application {
    Stage lava;
    GridPane laud;
    int lauaPikkusLaevades = 4;
    int laevaServPikslites = 100;
    int lauaServPikslites = lauaPikkusLaevades * laevaServPikslites;
    StackPane maailm;


    @Override
    public void start(Stage primaryStage) throws Exception {
        lava = primaryStage;
        seadistaLava();
        paigutaLaevad();
        reageeriKlikile();
        KasOnLaevu();
        gameover();
    }

    private void gameover() {
        Label lopp = new Label("Võitsid");
        lopp.setFont(new Font(30));
        lopp.setTextFill(Color.WHITE);
        maailm.getChildren().add(lopp);
    }



    private boolean KasOnLaevu() {
        for (Node ruut : laud.getChildren())
            if (ruut.getId().equals("laev")) {
                return true;

        }
        return false;}





    private void reageeriKlikile() {
        laud.setOnMouseClicked(event -> {
            Rectangle ruut = (Rectangle) event.getTarget();
            String ruuduId = ruut.getId();
            if (ruuduId.equals("laev")) {
                ruut.setFill(Color.RED);
                ruut.setId("Pihtas!");

            } else if (ruuduId.equals("meri")) {
                System.out.println("Mööda!!!!");
            } else if (ruuduId.equals("Pihtas!"))
                System.out.println("Juba põhjas");


        });


    }


    private void paigutaLaevad() {
        for (int i = 0; i < lauaPikkusLaevades; i++) {
            for (int j = 0; j < lauaPikkusLaevades ; j++) {
                Rectangle ruut = new Rectangle(laevaServPikslites, laevaServPikslites);
                int random = (int) (Math.random() * 2);
                if (random == 1) {
                    ruut.setId("laev");
                }
                else if (random == 0) {
                    ruut.setId("meri");
                }

                ruut.setStroke(Color.BLACK);
                ruut.setFill(Color.BLUE);
                    laud.add(ruut, i, j);
            }
        }
    }


    private void seadistaLava() {
        laud = new GridPane();
        StackPane maailm = new StackPane();
        maailm.getChildren().add(laud);
        Scene manguStseen = new Scene(laud, lauaPikkusLaevades, laevaServPikslites);
        lava.setScene(manguStseen);
        lava.show();
        lava.setOnCloseRequest(event -> System.exit(0));


    }


}
