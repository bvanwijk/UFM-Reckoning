package nl.joshuaslik.UFMReckoning.gui.game;

import java.io.IOException;

import nl.joshuaslik.UFMReckoning.gui.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Ranking {

	public static void start() throws IOException {
		Parent root = FXMLLoader.load(Class.class
				.getResource("/data/gui/pages-game/Competition.fxml"));
		Scene scene = new Scene(root, 1080, 1920);
		Main.stage.setScene(scene);
		Main.stage.setFullScreen(true);
	}
}
