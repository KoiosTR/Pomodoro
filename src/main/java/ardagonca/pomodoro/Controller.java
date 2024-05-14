package ardagonca.pomodoro;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.lang.management.PlatformManagedObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private Button beHappy;

    @FXML
    private ImageView happyImg;

    @FXML
    private ImageView sadImg;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button main_btn;

    @FXML
    private AnchorPane user_form;

    @FXML
    private Label main_min;

    @FXML
    private Label main_sec;

    @FXML
    private Button settings_btn;

    @FXML
    private Button settings_col_blue;

    @FXML
    private Button settings_col_cyan;

    @FXML
    private Button settings_col_green;

    @FXML
    private Button settings_col_orange;

    @FXML
    private Button settings_col_pink;

    @FXML
    private Button settings_col_red;

    @FXML
    private Button settings_col_white;

    @FXML
    private Button settings_col_yellow;

    @FXML
    private AnchorPane settings_form;

    @FXML
    private ComboBox<Integer> settings_min;

    @FXML
    private Button settings_save;

    @FXML
    private ComboBox<Integer> settings_sec;

    @FXML
    private Button user_btn;

    @FXML
    private AnchorPane daire1;

    @FXML
    private AnchorPane daire2;


    public void minList() {
        ArrayList<Integer> minList = new ArrayList<>();
        for (int i = 0 ; i < 60 ; i++) {
            String min = String.format("%02d", i);
            minList.add(Integer.parseInt(min));
        }

        ObservableList listData = FXCollections.observableArrayList(minList);
        settings_min.setItems(listData);
    }


    public void secList () {
        ArrayList<Integer> secList = new ArrayList<>();
        for (int i = 0 ; i < 60 ; i++) {
            String sec = String.format("%02d", i);
            secList.add(Integer.parseInt(sec));
        }

        ObservableList listData = FXCollections.observableArrayList(secList);
        settings_sec.setItems(listData);
    }


    public void switchForm ( ActionEvent event ) {
        if (event.getSource() == main_btn) {
            main_form.setVisible(true);
            settings_form.setVisible(false);
            user_form.setVisible(false);
            quickAbdestReturn();

        } else if (event.getSource() == settings_btn) {
            main_form.setVisible(false);
            settings_form.setVisible(true);
            user_form.setVisible(false);
            secList();
            minList();
            quickAbdestReturn();
        } else if (event.getSource() == user_btn) {
            main_form.setVisible(false);
            settings_form.setVisible(false);
            user_form.setVisible(true);
        }
    }

    static int sure ;

    public void quickAbdestReturn () {
        sadImg.setVisible(true);
        happyImg.setVisible(false);
    }

    public void quickAbdest () {
        sadImg.setVisible(false);
        happyImg.setVisible(true);
    }

    public void settingsSave () {
        if (settings_min.getSelectionModel().getSelectedItem() < 10 && settings_sec.getSelectionModel().getSelectedItem() < 10) {
            main_min.setText("0" + settings_min.getSelectionModel().getSelectedItem().toString());
            main_sec.setText("0" + settings_sec.getSelectionModel().getSelectedItem().toString());
        } else if (settings_sec.getSelectionModel().getSelectedItem() < 10 ) {
            main_sec.setText("0" + settings_sec.getSelectionModel().getSelectedItem().toString());
        } else if (settings_min.getSelectionModel().getSelectedItem() < 10) {
            main_min.setText("0" + settings_min.getSelectionModel().getSelectedItem().toString());
        } else {
            main_min.setText(settings_min.getSelectionModel().getSelectedItem().toString());
            main_sec.setText(settings_sec.getSelectionModel().getSelectedItem().toString());
        }
        prevMin = main_min.getText().toString();
        prevSec = main_sec.getText().toString();
    }



    private boolean flag = false;

    public static String prevMin;
    public static String prevSec;
    public void defaultTime () {
        prevMin = main_min.getText().toString();
        prevSec = main_sec.getText().toString();
    }

    public void timerRepeat () {
        if (!flag) {
            main_min.setText(prevMin);
            main_sec.setText(prevSec);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata Mesajı");
            alert.setHeaderText(null);
            alert.setContentText("Timer'ı durdurun.");
            alert.showAndWait();
        }
    }

    public void timerStart () {

        sure = Integer.parseInt(main_min.getText())*60 + Integer.parseInt(main_sec.getText());
        flag = true;
        new Thread(() -> {
            while (sure >= 0 && flag) {
                int dakika = sure / 60;
                int saniye = sure % 60;

                Platform.runLater(() -> {
                    main_min.setText(String.format("%02d", dakika));
                    main_sec.setText(String.format("%02d", saniye));
                });

                sure--;

                try {
                    Thread.sleep(1000); // Her saniye bir kez güncelle
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        if (sure == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bilgilendirme Mesaji");
            alert.setHeaderText(null);
            alert.setContentText("Süre Doldu");
            alert.showAndWait();
        }
    }

    public void timerStop () {
        flag = false;
    }



    public void switchColor ( ActionEvent event ) {
        if (event.getSource() == settings_col_blue) {
            daire1.setStyle("-fx-background-color:  #E6BCB9;");
            daire2.setStyle("-fx-background-color:  #8E5915;");
        } else if (event.getSource() == settings_col_cyan) {
            daire1.setStyle("-fx-background-color: #662549;");
            daire2.setStyle("-fx-background-color: #8E5915;");
        } else if (event.getSource() == settings_col_green) {
            daire1.setStyle("-fx-background-color: #662549;");
            daire2.setStyle("-fx-background-color: #AE445A;");
        } else if (event.getSource() == settings_col_orange) {
            daire1.setStyle("-fx-background-color: #31599E;");
            daire2.setStyle("-fx-background-color: #F40076;");
        } else if (event.getSource() == settings_col_pink) {
            daire1.setStyle("-fx-background-color: #E6BCB9;");
            daire2.setStyle("-fx-background-color: #F40076;");
        } else if (event.getSource() == settings_col_red) {
            daire1.setStyle("-fx-background-color: #FFA200;");
            daire2.setStyle("-fx-background-color: #F39F5A;");
        } else if (event.getSource() == settings_col_white) {
            daire1.setStyle("-fx-background-color: #31599E;");
            daire2.setStyle("-fx-background-color: #FFA200;");
        } else if (event.getSource() == settings_col_yellow) {
            daire1.setStyle("-fx-background-color: #662549;");
            daire2.setStyle("-fx-background-color: #AE445A;");
        }
    }


    public void initialize(URL location, ResourceBundle resources) {
        defaultTime();
        secList();
        minList();

    }

}
