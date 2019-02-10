package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ControlButton extends Button {

	public ControlButton(String text) {
		super(text);
		
		//button styling
		setPrefHeight(Style.CONTROL_ELEMENT_HEIGHT);
		setMinHeight(Style.CONTROL_ELEMENT_HEIGHT);
		setPrefWidth(Style.CONTROL_ELEMENT_WIDTH);
		setMaxWidth(Style.CONTROL_ELEMENT_WIDTH);
		setBorder(Style.CONTROL_ELEMENT_BORDER_DEFAULT);
		setFont(Style.WINDOW_FONT_DEFAULT);
		setBackground(Style.CONTROL_ELEMENT_BACKGROUND_DEFAULT);
		setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
		
		//setting event handlers
		setOnMouseEntered(new EnterHandler());
		setOnMouseExited(new ExitHandler());
		setOnMousePressed(new PressHandler());
		setOnMouseReleased(new ReleaseHandler());
	}
	
	// ---- style helper methods (for handlers)

	private void defaultStyle() {
		setBorder(Style.CONTROL_ELEMENT_BORDER_DEFAULT);
		setBackground(Style.CONTROL_ELEMENT_BACKGROUND_DEFAULT);
		setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
	}
			
	//handles mouse entered event
	private class EnterHandler implements EventHandler<MouseEvent>{
		
		@Override
		public void handle(MouseEvent event) {
			setBackground(Style.CONTROL_ELEMENT_BACKGROUND_DEFAULT);
			setBorder(Style.CONTROL_ELEMENT_BORDER_ENTERED);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
		}
				
	}
	
	//handles mouse exit event (back to default)
	private class ExitHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			defaultStyle();
		}
				
	}
	
	//handles mouse press event
	private class PressHandler implements EventHandler<MouseEvent>{
		
		@Override
		public void handle(MouseEvent event) {
			setBackground(Style.CONTROL_ELEMENT_BACKGROUND_PRESSED);
			setBorder(Style.CONTROL_ELEMENT_BORDER_ENTERED);
			setTextFill(Style.WINDOW_COLOR_TEXT_DARK);
		}
				
	}
			
	//handles mouse release event (back to default)
	private class ReleaseHandler implements EventHandler<MouseEvent>{
		
		@Override
		public void handle(MouseEvent event) {
			defaultStyle();
		}
		
				
	}

}
