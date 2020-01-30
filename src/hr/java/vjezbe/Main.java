package hr.java.vjezbe;

import hr.java.vjezbe.niti.DatumObjaveNit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	private static BorderPane mainPane;

	@Override
	public void start(Stage primaryStage) {
		try {
			mainPane = (BorderPane) FXMLLoader.load(getClass().getResource("Pocetna.fxml"));
			Scene scene = new Scene(mainPane, 600, 500);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Timeline prikazNajnovijeProdaje = new Timeline(
				new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Platform.runLater(new DatumObjaveNit());
					}
				}));
		prikazNajnovijeProdaje.setCycleCount(Timeline.INDEFINITE);
		prikazNajnovijeProdaje.play();
	}

	public static void setMainPage(BorderPane center) {
		mainPane.setCenter(center);
	}

	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * #!/bin/bash java -cp h2*.jar org.h2.tools.Server
	 * 
	 */
}
