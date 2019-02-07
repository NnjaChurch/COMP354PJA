package view;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Game element which provides all the game information from the current keyword
 * to the score of each team. It also provides an indicator of who's turn it is. <br>
 * This method provides useful methods to update scores, clues and turn.
 * 
 * @author David Boivin (absynth) ID = 40004941
 */
public class HQPane extends HBox {

	private static TeamPane mBlueTeam;
	private static TeamPane mRedTeam;
	
	/**
	 * Creates both team containers and sets the correct one in starting state.
	 */
	public HQPane(boolean isStartBlue) {
		super();
		
		//hq styling
		VBox.setVgrow(this, Priority.ALWAYS);
		
		//creates teams
		mBlueTeam = new TeamPane(true, isStartBlue);
		mRedTeam = new TeamPane(false, !isStartBlue);
		
		//adds teams to the hq
		getChildren().addAll(mBlueTeam, mRedTeam);
		
	}
	
	/**
	 * Sets the clue to the given team <br><code>true</code> for blue, <code>false</code> red.
	 */
	public void setClue(boolean isBlue, String clue) {
		if(isBlue) {
			mBlueTeam.setClue(clue);
		}else {
			mRedTeam.setClue(clue);
		}
	}
	
	/**
	 * Changes the score of the given team <br><code>true</code> for blue, <code>false</code> red.
	 */
	public void setScore(boolean isBlue, int score) {
		if(isBlue) {
			mBlueTeam.setScore(score);
		}else {
			mRedTeam.setScore(score);
		}
	}
	
	/**
	 * Changes the color on the teams to display who's turn it is, 
	 * determined by which team as the darker color.
	 */
	public void setTurn(boolean isBlue) {
		mBlueTeam.setOn(isBlue);
		mRedTeam.setOn(!isBlue);
	}
	
	
	
	
	//------- Private Inner Elements ---------------// 
	
	/**
	 * Container which represents each team in the game, one by blue (on the left)
	 * and the other by red (on  the right).
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class TeamPane extends HBox{
		
		private final boolean mIsBlue;
		private Clue mClue;
		private Score mScore;
		
		
		public TeamPane(boolean isBlue, boolean isStart) {
			super();
			
			mIsBlue = isBlue;
			
			//Team styling
			HBox.setHgrow(this, Priority.ALWAYS);
			setPadding(Style.HQ_TEAM_PADDING);
			setAlignment(Style.HQ_TEAM_ALIGNMENT);
			
			setBackground(mIsBlue ? 
					Style.HQ_BACKGROUND_BLUE_DEFAULT : Style.HQ_BACKGROUND_RED_DEFAULT);
			
			//inner components
			SpyImage imgView = new SpyImage(mIsBlue);
			mClue = new Clue(mIsBlue);
			mScore = new Score(mIsBlue);
			
			//buffer setup
			Pane buff = new Pane();
			HBox.setHgrow(buff, Priority.ALWAYS);
			
			//team alignment
			if(mIsBlue) {
				getChildren().addAll(imgView, mClue, buff, mScore);
				
			}else{
				getChildren().addAll(mScore, buff, mClue, imgView);
			}
			
			if(isStart) {
				setOn(true);
			}
		}
		
		/**
		 * Sets the clue for the team.
		 */
		public void setClue(String clue) {
			mClue.setText(clue);
		}

		/**
		 * Sets the score for the team.
		 */
		public void setScore(int score) {
			mScore.setText(String.valueOf(score));
		}

		/**
		 * Chooses appropriate color based on team and "on" or "off" -ness.<br>
		 * "On" signifies that it is this team's turn to play.<br>
		 * "Off" signifies that it is the other team's turn to play
		 * 
		 * @param isOn 
		 */
		public void setOn(boolean isOn) {
			
			if(isOn) {								//Case : team's turn
				if(mIsBlue) {
					setBackground(Style.HQ_BACKGROUND_BLUE_ON);
				}else {
					setBackground(Style.HQ_BACKGROUND_RED_ON);
				}
				
			}else if(!isOn) {						//case : not team's turn
				if(mIsBlue) {
					setBackground(Style.HQ_BACKGROUND_BLUE_DEFAULT);
				}else {
					setBackground(Style.HQ_BACKGROUND_RED_DEFAULT);
				}
			}
		}
		
	}
	
	/**
	 * The Clue element which displays the "spymaster's" clue (word referencing Card(s) in the Field).
	 * This class is only used to set the styling based on team.
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 *
	 */
	private class Clue extends Label{
		
		private Clue(boolean isBlue) {
			super();
			
			// clue styling 
			setPrefHeight(Style.HQ_CLUE_HEIGHT);
			setAlignment(Style.HQ_CLUE_ALIGNMENT);
			
			setBackground(isBlue ? 
					Style.HQ_CLUE_BACKGROUND_LEFT : Style.HQ_CLUE_BACKGROUND_RIGHT);
			
			setPadding(Style.HQ_CLUE_PADDING);
			setFont(Style.WINDOW_FONT_DEFAULT);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
		}
	}
	
	/**
	 * The Score element which displays the team's score countdown to 0
	 * This class is only used to set the styling based on team.
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class Score extends Label {
		
		private Score(boolean isBlue) {
			super();
			
			// score styling
			setPrefHeight(Style.HQ_SCORE_HEIGHT);
			setPrefWidth(Style.HQ_SCORE_WIDTH);
			setMinWidth(Style.HQ_SCORE_WIDTH);
			
			setAlignment(Style.HQ_SCORE_ALIGNMENT);
			setMargin(this, Style.HQ_SCORE_MARGIN);
			
			setBorder(Style.HQ_SCORE_BORDER);
			setBackground(isBlue? 
					Style.HQ_SCORE_BACKGROUND_LEFT : Style.HQ_SCORE_BACKGROUND_RIGHT);
			
			setFont(Style.WINDOW_FONT_HEADER);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
			
			//Default text for testing
			setText("0");
		}
	}
	
	/**
	 * The Image element which displays the team's "spymaster" in red or blue.
	 * This class is only used to set the styling based on team.
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class SpyImage extends ImageView{
		
		private SpyImage(boolean isBlue) {
			super();
			
			// Image styling
			setPrefHeight(Style.HQ_IMAGE_HEIGHT);
			setPrefWidth(Style.HQ_IMAGE_WIDTH);
			
			//image setting
			setImage(isBlue ? 
					Style.HQ_IMAGE_LEFT : Style.HQ_IMAGE_RIGHT);
		}
	}
}
