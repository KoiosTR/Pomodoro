module ardagonca.pomodoro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.management;


    opens ardagonca.pomodoro to javafx.fxml;
    exports ardagonca.pomodoro;
}