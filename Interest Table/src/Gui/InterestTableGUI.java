package Gui;


import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class InterestTableGUI extends Application{
	BorderPane borderPane;
	private TextArea displayArea;
	private TextField princ,rate;
	private Slider slider;
	 
	
	public void start(Stage primaryStage) {
			int sceneWidth = 600, sceneHeight = 350;
			displayArea = new TextArea();
			displayArea.setEditable(false);
			displayArea.setWrapText(true);
		    ScrollPane scrollPane = new ScrollPane(displayArea);
			scrollPane.setFitToWidth(true);
			
			
			GridPane pane = new GridPane();
			pane.setHgap(8);
			pane.setVgap(8);
			pane.setPadding(new Insets(20,20,20,20));
			Label princLabel = new Label("Principal: ");
			pane.add(princLabel, 0, 0);
			princ = new TextField();
			pane.add(princ, 1, 0);
			Label rateLabel = new Label("Rate(Percentage): ");
			pane.add(rateLabel, 2, 0);
			rate = new TextField();
			pane.add(rate, 3, 0);
			
			slider = new Slider();
			slider.setMin(1);
			slider.setMax(25);
			slider.setValue(1);
			slider.setShowTickLabels(true);
			slider.setShowTickMarks(true);
			slider.setMajorTickUnit(4);
			
			
			Label numofy = new Label("Number of Years:	 ");
			GridPane.setConstraints(slider, 2,2,10,1);
			pane.add(numofy, 1, 2);
			pane.add(slider, 2, 2);
		
			Button SI =new Button ("SimpleInterest");
			Button CI =new Button ("CompoundInterest");
			Button BI =new Button ("BothInterests");
			pane.add(SI, 1, 3);
			pane.add(CI, 2, 3);
			pane.add(BI, 3, 3);
			
			SI.setOnAction(new SIhandler());
			
			CI.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
				  double Principal = Double.parseDouble(princ.getText());
				  double Rate = Double.parseDouble(rate.getText());
				  String display="Principal: $"+String.format( "%.2f",Principal)+", Rate: "+Rate+"\nYear, Compound Interest Amount";
				  for (int i= 1; i<=slider.getValue();i++) {
					display+="\n"+i+"-->$"+String.format( "%.2f",Principal*Math.pow(1 + Rate/100,i));
				  }
				  displayArea.setText(display);
				}
			});
			
			BI.setOnAction(e->{
				 double Principal = Double.parseDouble(princ.getText());
				 double Rate = Double.parseDouble(rate.getText());
				 String display="Principal: $"+String.format( "%.2f",Principal)+", Rate: "+Rate+"\nYear, Simple Interest Amount, Compound Interest Amount";
				 for (int i= 1; i<=slider.getValue();i++) {
						display+="\n"+i+"-->$"+String.format( "%.2f",(Principal +(Principal* (Rate/100) * i)))
								+"-->$"+String.format( "%.2f",Principal*Math.pow(1 + Rate/100,i));
					  }
					  displayArea.setText(display);
			});
			
			borderPane= new BorderPane();
			borderPane.setTop(scrollPane);
			borderPane.setCenter(pane);
			
			
			
			Scene scene = new Scene(borderPane, sceneWidth, sceneHeight);
			primaryStage.setTitle("Interest Table Calculator");
			primaryStage.setScene(scene);
			primaryStage.show();	
			
	}
		
	private class SIhandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			double Principal = Double.parseDouble(princ.getText());
			double Rate = Double.parseDouble(rate.getText());
			String display="Principal: $"+String.format( "%.2f",Principal)+", Rate: "+Rate+"\nYear, Simple Interest Amount";
			
			for (int i= 1; i<=slider.getValue();i++) {
				display+="\n"+i+"-->$"+String.format( "%.2f",(Principal +(Principal* (Rate/100) * i)));
			}
			displayArea.setText(display);
		}
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
