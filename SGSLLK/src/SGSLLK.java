import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.media.AudioClip;
import javafx.scene.media.AudioClipBuilder;
import javafx.stage.Stage;

public class SGSLLK extends Application {

	public static class Background {

		public static AudioClip bgSound;
		public static ImageView bgView;

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {

		primaryStage.setTitle("三国杀连连看——By 命運ぺ晨");
		primaryStage.setWidth(Arg.GAMEWIDTH);
		primaryStage.setHeight(Arg.GAMEHEIGHT);

		Group root = new Group();

		Background.bgSound = AudioClipBuilder
				.create()
				.source(getClass().getResource("audios/sgsbg.mp3")
						.toExternalForm()).cycleCount(AudioClip.INDEFINITE)
				.build();

		Background.bgSound.play();

		Background.bgView = ImageViewBuilder.create()
				.image(new Image("pics/sgsbg.jpg")).fitWidth(Arg.GAMEWIDTH)
				.fitHeight(Arg.GAMEHEIGHT).build();

		root.getChildren().add(Background.bgView);

		Canvas canvas = new Canvas();

		ButtonPanel panel = new ButtonPanel(canvas, Background.bgView);

		root.getChildren().addAll(canvas, panel);

		Scene scene = new Scene(root, Arg.GAMEWIDTH, Arg.GAMEHEIGHT);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		// primaryStage.getIcons().add();
		primaryStage.show();

	}

}