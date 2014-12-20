package nl.joshuaslik.UFMReckoning.gui;

import java.io.IOException;
import java.util.ArrayList;

import nl.joshuaslik.UFMReckoning.backend.Game;
import nl.joshuaslik.UFMReckoning.backend.Save;
import nl.joshuaslik.UFMReckoning.backend.Team;
import nl.joshuaslik.UFMReckoning.gui.game.Competition;
import nl.joshuaslik.UFMReckoning.gui.game.MainGame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class NewGameController {
	
		private Team choosenteam;
	
	 	@FXML
	    private TableView<Team> teamtable;
	    @FXML
	    private TableColumn<Team, String> teamColumn;
	    @FXML
	    private TableColumn<Team, String> coachColumn;
	    @FXML
	    private TableColumn<Team, String> averagedefence;
	    @FXML
	    private TableColumn<Team, String> averagestamina;
	    @FXML
	    private TableColumn<Team, String> averageattack;
	    
	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    	
	    	ObservableList<Team> teams = FXCollections.observableArrayList(Save.loadTeams());
	    	teamtable.setItems(teams);
	    	
	    	teamColumn.setCellValueFactory(new PropertyValueFactory<Team, String>(
					"teamName"));
			coachColumn.setCellValueFactory(new PropertyValueFactory<Team, String>(
					"coachName"));
			averagedefence.setCellValueFactory(new PropertyValueFactory<Team, String>(
					"averageDefencePower"));
			averagestamina.setCellValueFactory(new PropertyValueFactory<Team, String>(
					"averageStamina"));
			averageattack.setCellValueFactory(new PropertyValueFactory<Team, String>(
					"averageAttackPower"));
	    
	        // Listen for selection changes 
			teamtable.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> selectedTeam(newValue));
	    }
	    
	    
	    @FXML
		protected void handleChooseTeam(ActionEvent event) throws IOException {
	    	if (choosenteam != null) {
	    		String username = "Bryan";
				Team chosenTeam = teamtable.getSelectionModel().getSelectedItem();
				Game Game1 = Save.newGame(chosenTeam, username);
				MainGame.setGame(Game1);
				MainGame.initialize();
				try {
					MainGame.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	    
	    public void selectedTeam(Team team){
	    	choosenteam = team;
	    }
	    
	    public static void start() throws IOException {
	    	AnchorPane topmenu = (AnchorPane) FXMLLoader.load(Class.class.getResource("/data/gui/pages-menu/TopMenu.fxml"));
			Label label = (Label) topmenu.lookup("#title");
			label.setText("Loading....");
			Main.setTop(topmenu);
			AnchorPane scene = (AnchorPane) FXMLLoader.load(Class.class.getResource("/data/gui/pages-menu/NewGame.fxml"));
			label.setText("Choose a Team");
			Main.setCenter(scene);
		}

}
