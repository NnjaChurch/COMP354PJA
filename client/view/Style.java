package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * An interface with all the styling constants for the program.
 * Provides an easy place to look for and modify styling constants.
 * 
 * @author David Boivin (absynth) ID = 40004941
 */
public interface Style {

	//-----------------   Window Styling   -----------------
	public static final double WINDOW_SIZE_WIDTH = 1000.0;
	public static final double WINDOW_SIZE_HEIGHT = 800.0;

	public static final Color WINDOW_COLOR_BACKGROUND_DARK = Color.color(0.09804, 0.09804, 0.09804);
	public static final Color WINDOW_COLOR_BACKGROUND_LIGHT = Color.color(0.9, 0.9, 0.9);
	public static final Color WINDOW_COLOR_BORDER = Color.color(0.3, 0.3, 0.3);
	public static final Color WINDOW_COLOR_TEXT_LIGHT = Color.color(0.8, 0.8, 0.8);
	public static final Color WINDOW_COLOR_TEXT_DARK = Color.color(0.1, 0.1, 0.1);
	public static final Color WINDOW_COLOR_CARD_BLACK = Color.color(0.0, 0.0, 0.0);
	public static final Color WINDOW_COLOR_CARD_YELLOW = Color.color(1.0, 0.67843, 0.0);
	public static final Color WINDOW_COLOR_CARD_RED = Color.color(1.0,0.0,0.0);
	public static final Color WINDOW_COLOR_CARD_BLUE = Color.color(0.10588,0.10588,0.70196);
	public static final Color WINDOW_COLOR_CARD_HIDDEN = Color.color(0.4, 0.4, 0.4);
	
	
	public static final int WINDOW_FONT_SIZE_DEFAULT = 16;
	public static final int WINDOW_FONT_SIZE_HEADER = 22;
	public static final String WINDOW_FONT_FAMILY = "Arial Black";
	public static final FontWeight WINDOW_FONT_WEIGHT = FontWeight.BOLD;
	public static final Font WINDOW_FONT_DEFAULT = Font.font(WINDOW_FONT_FAMILY, WINDOW_FONT_WEIGHT, WINDOW_FONT_SIZE_DEFAULT);
	public static final Font WINDOW_FONT_HEADER = Font.font(WINDOW_FONT_FAMILY, WINDOW_FONT_WEIGHT, WINDOW_FONT_SIZE_HEADER);
	

	
	//-----------------   Card Styling   ---------------------
	public static final double CARD_WIDTH = 150.0;
	public static final double CARD_HEIGHT = 100.0;
	
	public static final CornerRadii CARD_CORNER = new CornerRadii(5);
	
	public static final Border CARD_BORDER_DEFAULT = null; 		//no border
	public static final Border CARD_BORDER_ENTERED = new Border(new BorderStroke(WINDOW_COLOR_BACKGROUND_LIGHT, BorderStrokeStyle.SOLID, CARD_CORNER, new BorderWidths(3)));
	
	public static final Background CARD_BACKGROUND_HIDDEN = new Background(new BackgroundFill(WINDOW_COLOR_CARD_HIDDEN, CARD_CORNER, null));
	public static final Background CARD_BACKGROUND_YELLOW = new Background(new BackgroundFill(WINDOW_COLOR_CARD_YELLOW, CARD_CORNER, null));
	public static final Background CARD_BACKGROUND_BLACK = new Background(new BackgroundFill(WINDOW_COLOR_CARD_BLACK, CARD_CORNER, null));
	public static final Background CARD_BACKGROUND_BLUE = new Background(new BackgroundFill(WINDOW_COLOR_CARD_BLUE, CARD_CORNER, null));
	public static final Background CARD_BACKGROUND_RED = new Background(new BackgroundFill(WINDOW_COLOR_CARD_RED, CARD_CORNER, null));

	
	
	//-----------------   HQ Styling   --------------------
	public static final Border HQ_BORDER = new Border(new BorderStroke(WINDOW_COLOR_BORDER, BorderStrokeStyle.SOLID, null, new BorderWidths(0, 0, 5, 0)));
	public static final Background HQ_BACKGROUND_RED_DEFAULT = new Background(new BackgroundFill(Color.color(1.0, 0.5, 0.5), null, null));
	public static final Background HQ_BACKGROUND_RED_ON = new Background(new BackgroundFill(WINDOW_COLOR_CARD_RED, null, null));
	public static final Background HQ_BACKGROUND_BLUE_DEFAULT = new Background(new BackgroundFill(Color.color(0.3098, 0.3098, 0.85098), null, null));
	public static final Background HQ_BACKGROUND_BLUE_ON = new Background(new BackgroundFill(WINDOW_COLOR_CARD_BLUE, null, null));
	
	public static final Insets HQ_TEAM_PADDING = new Insets(10, 10, 10, 10);
	public static final Pos HQ_TEAM_ALIGNMENT = Pos.CENTER;
	
//	public static final int HQ_CLUE_WIDTH = 200;
	public static final int HQ_CLUE_HEIGHT = 50;
	public static final Insets HQ_CLUE_PADDING = new Insets(0, 20, 0, 20);
	public static final Pos HQ_CLUE_ALIGNMENT = Pos.CENTER;
	public static final CornerRadii HQ_CLUE_CORNER_LEFT = new CornerRadii(15, 15, 15, 0, false);
	public static final CornerRadii HQ_CLUE_CORNER_RIGHT = new CornerRadii(15, 15, 0, 15, false);
	public static final Background HQ_CLUE_BACKGROUND_LEFT = new Background(new BackgroundFill(WINDOW_COLOR_BACKGROUND_DARK, HQ_CLUE_CORNER_LEFT, null));
	public static final Background HQ_CLUE_BACKGROUND_RIGHT = new Background(new BackgroundFill(WINDOW_COLOR_BACKGROUND_DARK,HQ_CLUE_CORNER_RIGHT, null));

	public static final int HQ_SCORE_WIDTH = 70;
	public static final int HQ_SCORE_HEIGHT = 70;
	public static final Pos HQ_SCORE_ALIGNMENT = Pos.CENTER;
	public static final Insets HQ_SCORE_MARGIN = new Insets(10, 30, 10, 30);
	public static final Border HQ_SCORE_BORDER = new Border(new BorderStroke(WINDOW_COLOR_BACKGROUND_DARK, BorderStrokeStyle.SOLID, null, new BorderWidths(3)));
	public static final Background HQ_SCORE_BACKGROUND_LEFT = CARD_BACKGROUND_BLUE;
	public static final Background HQ_SCORE_BACKGROUND_RIGHT = CARD_BACKGROUND_RED;
	
	public static final int HQ_IMAGE_WIDTH = 150;
	public static final int HQ_IMAGE_HEIGHT = 150;
	//TODO need to change HQ_IMAGE to better looking ones (not pressing at all though)
	public static final Image HQ_IMAGE_LEFT = new Image("view/incognito-black.png", HQ_IMAGE_WIDTH, HQ_IMAGE_HEIGHT, true, true);
	public static final Image HQ_IMAGE_RIGHT = new Image("view/incognito-black.png", HQ_IMAGE_WIDTH, HQ_IMAGE_HEIGHT, true, true);
	
	
	//-----------------   Field Styling   --------------------
	public static final double FIELD_GAP_H = 5.0;
	public static final double FIELD_GAP_V = 5.0;
	
	public static final Pos FIELD_ALIGNMENT = Pos.CENTER;

	public static final Insets FIELD_PADDING = new Insets(10.0);
	
	public static final Background FIELD_BACKGROUND = new Background(new BackgroundFill(WINDOW_COLOR_BACKGROUND_DARK, null, null));

	
	
	//-----------------   Controlbar Styling   ------------------
	public static final int CONTROL_HEIGHT = 75;
	public static final Background CONTROL_BACKGROUND = new Background(new BackgroundFill(WINDOW_COLOR_BACKGROUND_DARK, null, null));
	public static final Border CONTROL_BORDER = new Border(new BorderStroke(WINDOW_COLOR_BORDER, BorderStrokeStyle.SOLID, null, new BorderWidths(5, 0, 0, 0)));

	public static final int CONTROL_ELEMENT_HEIGHT = CONTROL_HEIGHT;
	public static final int CONTROL_ELEMENT_WIDTH = 150;
	public static final Insets CONTROL_ELEMENT_MARGIN = new Insets(0, 0, 0, 0);
	public static final Border CONTROL_ELEMENT_BORDER_DEFAULT = new Border(new BorderStroke(WINDOW_COLOR_BORDER, BorderStrokeStyle.SOLID, null, new BorderWidths(0, 2, 2, 2)));
	public static final Border CONTROL_ELEMENT_BORDER_ENTERED = new Border(new BorderStroke(WINDOW_COLOR_BORDER, BorderStrokeStyle.SOLID, null, new BorderWidths(0, 5, 5, 5)));
	public static final Background CONTROL_ELEMENT_BACKGROUND_DEFAULT = new Background(new BackgroundFill(WINDOW_COLOR_BACKGROUND_DARK, null, null));;
	public static final Background CONTROL_ELEMENT_BACKGROUND_PRESSED = new Background(new BackgroundFill(WINDOW_COLOR_BORDER, null, null));
	
	
	//-----------------   Popup Styling -------------------------
	public static final Insets POPUP_PADDING = new Insets(30, 75, 30, 75);
	public static final CornerRadii POPUP_CORNER = new CornerRadii(10);
	public static final Background POPUP_BACKGROUND = new Background(new BackgroundFill(WINDOW_COLOR_BACKGROUND_DARK, null, null));
	public static final Border POPUP_BORDER = new Border(new BorderStroke(WINDOW_COLOR_BORDER, BorderStrokeStyle.SOLID, null, new BorderWidths(5)));
	
	public static final Insets POPUP_TITLE_PADDING = new Insets(10, 50, 10, 20);
	public static final Insets POPUP_TITLE_MARGIN = new Insets(5, 0, 5, 20);
	public static final Background POPUP_TITLE_BACKGROUND = new Background(new BackgroundFill(WINDOW_COLOR_BACKGROUND_LIGHT, POPUP_CORNER, null));
	
	public static final double POPUP_GRID_GAP_H = 7.0;
	public static final double POPUP_GRID_GAP_V = 7.0;
	public static final Insets POPUP_GRID_PADDING = new Insets(10);
	public static final Insets POPUP_GRID_MARGIN = new Insets(10, 0, 10, 0);
	public static final Border POPUP_GRID_BORDER = new Border(new BorderStroke(WINDOW_COLOR_BORDER, BorderStrokeStyle.SOLID, null, new BorderWidths(3)));
	public static final int POPUP_GRID_ELEMENT_CORNER = 10;
	public static final int POPUP_GRID_ELEMENT_HEIGHT = 100;
	public static final int POPUP_GRID_ELEMENT_WIDTH = 100;
	
	public static final Insets POPUP_START_PADDING = new Insets(10, 20, 10, 50); 
	public static final Insets POPUP_START_MARGIN = new Insets(5, 0, 5, 150);
	public static final Background POPUP_START_BACKGROUND_BLUE = new Background(new BackgroundFill(WINDOW_COLOR_CARD_BLUE, POPUP_CORNER, null));
	public static final Background POPUP_START_BACKGROUND_RED = new Background(new BackgroundFill(WINDOW_COLOR_CARD_RED, POPUP_CORNER, null));

	public static final Insets POPUP_CLOSE_MARGIN = new Insets(5, 0, 5, 20);
	public static final int POPUP_CLOSE_HEIGHT = 75;
	public static final int POPUP_CLOSE_WIDTH = 125;
	public static final Border POPUP_CLOSE_BORDER_DEFAULT = new Border(new BorderStroke(WINDOW_COLOR_BORDER, BorderStrokeStyle.SOLID, POPUP_CORNER, new BorderWidths(3)));
	public static final Border POPUP_CLOSE_BORDER_ENTERED = new Border(new BorderStroke(WINDOW_COLOR_BORDER, BorderStrokeStyle.SOLID, POPUP_CORNER, new BorderWidths(5)));
	public static final Background POPUP_CLOSE_BACKGROUND_DEFAULT = new Background(new BackgroundFill(WINDOW_COLOR_BACKGROUND_DARK, POPUP_CORNER, null));
	public static final Background POPUP_CLOSE_BACKGROUND_PRESSED = new Background(new BackgroundFill(WINDOW_COLOR_BORDER, POPUP_CORNER, null));
	
}


