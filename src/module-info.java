/**
 * 
 */
/**
 * 
 */
module app {
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	
//	opens app.ui to javafx.base, javafx.controls, javafx.fxml, javafx.graphics;
	opens app.controller to javafx.base, javafx.controls, javafx.fxml, javafx.graphics;
}