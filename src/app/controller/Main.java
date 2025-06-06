package app.controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		Application.launch();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		test fentest = new test();
		fentest.show();
		
	}
}
