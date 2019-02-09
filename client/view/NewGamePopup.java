package view;

import control.Inbox;
import control.Message;
import control.MessageType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

/**
 * Popup which creates a selection screen for different AI strategies and
 * creates a new game once the selection has been made.
 * 
 * @author David Boivin (absynth) ID = 40004941
 */
public class NewGamePopup extends Popup {
	
	public static final String STRAT_RANDOM = "Random";
	public static final String STRAT_SEQUENTIAL = "Sequential";

	private final Inbox mInbox;
	private final SelectorPane mBlueSelect;
	private final SelectorPane mRedSelect;

	public NewGamePopup(Inbox inbox) {
		super();

		mInbox = inbox;
		mBlueSelect = new SelectorPane(true);
		mRedSelect = new SelectorPane(false);
		
		RootPane root = new RootPane();

		TitleLabel title = new TitleLabel();
		ControlButton continueButton = new ControlButton("Continue");
		ControlButton quitButton = new ControlButton("Quit");

		continueButton.setOnAction(new ContinueHandler());
		quitButton.setOnAction(new QuitHandler());

		// selector container setup
		HBox selectorContainer = new HBox();
		VBox.setMargin(selectorContainer, Style.POPUP_SELECTION_MARGIN );

		// buff setup
		Pane buff1 = new Pane();
		buff1.setPrefWidth(Style.POPUP_SELECTION_SPACING);
		selectorContainer.getChildren().addAll(mBlueSelect, buff1, mRedSelect);

		// control bar setup
		HBox controlbar = new HBox();

		// buffer setup
		Pane buff2 = new Pane();
		HBox.setHgrow(buff2, Priority.ALWAYS);

		controlbar.getChildren().addAll(continueButton, buff2, quitButton);

		root.getChildren().addAll(title, selectorContainer, controlbar);

		getContent().add(root);
	}

	// ------------------- Inner elements -------------------------

	/**
	 * Styling class for title element
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 *
	 */
	private class TitleLabel extends Label {

		private TitleLabel() {
			super("New Game");

			setPadding(Style.POPUP_TITLE_PADDING);
			setBackground(Style.POPUP_TITLE_BACKGROUND);
			VBox.setMargin(this, Style.POPUP_TITLE_MARGIN);

			setFont(Style.WINDOW_FONT_HEADER);
			setTextFill(Style.WINDOW_COLOR_TEXT_DARK);
		}
	}

	/**
	 * Stylized pane which contains a button group designed to control which
	 * strategy a team uses.
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class SelectorPane extends VBox {
		
		ToggleGroup mGroup;

		public SelectorPane(boolean isBlue) {
			super();

			//logical grouping of buttons
			mGroup = new ToggleGroup();
			mGroup.selectedToggleProperty().addListener(new ToggleListener());
			
			setPadding(Style.POPUP_SELECTION_PADDING);

			Label selectorTitle = new Label();
			selectorTitle.setFont(Style.WINDOW_FONT_DEFAULT);
			selectorTitle.setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);

			
			
			// selection buttons
			SelectorButton random = new SelectorButton(STRAT_RANDOM);
			SelectorButton sequential = new SelectorButton(STRAT_SEQUENTIAL);
			random.setUserData(STRAT_RANDOM);
			sequential.setUserData(STRAT_SEQUENTIAL);

			// links selection buttons into one button group
			random.setToggleGroup(mGroup);
			sequential.setToggleGroup(mGroup);
			
			//sets the default to be the random strat
			mGroup.selectToggle(random);

			Pane buff1 = new Pane();
			buff1.setPrefHeight(Style.POPUP_SPACING);
			Pane buff2 = new Pane();
			buff2.setPrefHeight(Style.POPUP_SPACING);

			if (isBlue) {
				selectorTitle.setText("Blue Strategy");
				setBackground(Style.POPUP_START_BACKGROUND_BLUE);
			} else {
				selectorTitle.setText("Red Strategy");
				setBackground(Style.POPUP_START_BACKGROUND_RED);
			}

			getChildren().addAll(selectorTitle, buff1, random, buff2, sequential);

		}
		
		/**
		 * Method for accessing selected stratagy
		 * @return
		 */
		public String getSelectedStratagy() {
			return (String) mGroup.getSelectedToggle().getUserData();
		}
		
		
		/**
		 * Listener for toggle which changes the Toggle's style
		 * @author David Boivin (absynth) ID = 40004941
		 */
		private class ToggleListener implements ChangeListener<Toggle>{

			@Override
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, 
					Toggle new_toggle) {
				//makes sure to have a button selected at all times
				if(old_toggle != null && mGroup.getSelectedToggle() == null) {
					((SelectorButton) old_toggle).setSelected(true);
				}
				
				//makes sure only one button appears selected (changes old one back to default styling)
				for(Toggle t: mGroup.getToggles()) {
					if(t.isSelected()) {
						((SelectorButton) t).selectedStyle();
					}else {
						((SelectorButton) t).defaultStyle();
					}
				}
			}
			
		}

		// Selector Buttons
		private class SelectorButton extends ToggleButton {

			public SelectorButton(String name) {
				super(name);// button styling

				setPrefHeight(Style.CONTROL_ELEMENT_HEIGHT);
				setMinHeight(Style.CONTROL_ELEMENT_HEIGHT);
				setPrefWidth(Style.CONTROL_ELEMENT_WIDTH);
				setMaxWidth(Style.CONTROL_ELEMENT_WIDTH);
				setBorder(Style.CONTROL_ELEMENT_BORDER_DEFAULT);
				setFont(Style.WINDOW_FONT_DEFAULT);
				defaultStyle();
				
				setOnMouseEntered(new EnterHandler());
				setOnMouseExited(new ExitHandler());
			}

			// ---- style helper methods (for handlers)

			private void exitStyle() {
				setBorder(Style.CONTROL_ELEMENT_BORDER_DEFAULT);
			}
			
			private void enterStyle() {
				setBorder(Style.CONTROL_ELEMENT_BORDER_ENTERED);
			}
			
			public void defaultStyle() {
				setBackground(Style.CONTROL_ELEMENT_BACKGROUND_DEFAULT);
				setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
			}

			public void selectedStyle() {
				setBackground(Style.CONTROL_ELEMENT_BACKGROUND_PRESSED);
				setTextFill(Style.WINDOW_COLOR_TEXT_DARK);
			}

			// handles mouse entered event
			private class EnterHandler implements EventHandler<MouseEvent> {

				@Override
				public void handle(MouseEvent event) {
					enterStyle();
				}

			}

			// handles mouse exit event (back to default)
			private class ExitHandler implements EventHandler<MouseEvent> {

				@Override
				public void handle(MouseEvent event) {
					exitStyle();
				}
			}
		}
	}

	// -------------------- Handler classes ----------------------

	/**
	 * Handler which sends out a new game message based on the selected
	 * configuration.
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class ContinueHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			System.out.println(mBlueSelect.getSelectedStratagy());
			System.out.println(mRedSelect.getSelectedStratagy());
			//mInbox.sendMessage(new Message(MessageType.NEW_GAME, 0));
			hide();
		}

	}

	/**
	 * handler which hides the window and shuts the application down
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class QuitHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			hide();
			System.exit(0);
		}

	}
}
