package view;

import javafx.scene.layout.VBox;

public class RootPane extends VBox {

	public RootPane() {
		super();
		
		setBackground(Style.WINDOW_BACKGROUND);
		setBorder(Style.WINDOW_BORDER);
		setPadding(Style.WINDOW_PADDING);
	}
}
