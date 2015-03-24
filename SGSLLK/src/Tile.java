import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.AudioClipBuilder;
import javafx.util.Duration;

public class Tile extends Parent {

	public int type;
	ImageView img;
	public Canvas canvas;

	public Tile() {
		type = -1;
		this.prefWidth(50);
		this.prefHeight(50);
	}

	public Tile(Canvas canvas, int type) {

		this.canvas = canvas;
		this.type = type;
		this.prefWidth(50);
		this.prefHeight(50);
		this.img = ImageViewBuilder.create().image(new Image(Arg.PIC_NO[type]))
				.fitWidth(50).fitHeight(50).build();
		this.getChildren().add(this.img);

		this.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				handleAction();
			}
		});

	}

	private void handleAction() {

		if (canvas.tempStart == null) {

			canvas.tempStart = this;
			AudioClip click = AudioClipBuilder
					.create()
					.source(getClass().getResource("audios/kill.mp3")
							.toExternalForm()).cycleCount(1).build();
			click.play();

			this.setEffect(new Lighting());

			return;
		} else {

			if (canvas.tempStart == this) {

				canvas.tempStart = null;
				AudioClip click = AudioClipBuilder
						.create()
						.source(getClass().getResource("audios/dodge.mp3")
								.toExternalForm()).cycleCount(1).build();
				click.play();

				this.setEffect(null);

				return;
			} else {

				canvas.tempEnd = this;

				if (canvas.tempStart.type == canvas.tempEnd.type) {

					canvas.find(canvas.tempStart, canvas.tempEnd);

					if (canvas.path.type != -1) {

						canvas.draw();

						canvas.victory -= 2;

						AudioClip click = AudioClipBuilder
								.create()
								.source(getClass().getResource(
										"audios/dead.mp3").toExternalForm())
								.cycleCount(1).build();
						click.play();

						final Tile tileA = canvas.tempStart;
						final Tile tileB = canvas.tempEnd;

						tileA.type = -1;
						tileB.type = -1;

						FadeTransition fadeTransition = new FadeTransition(
								Duration.millis(2000), tileA);
						fadeTransition.setFromValue(1);
						fadeTransition.setToValue(0);
						fadeTransition.play();
						fadeTransition
								.setOnFinished(new EventHandler<ActionEvent>() {
									public void handle(ActionEvent e) {
										tileA.getChildren().clear();

									}
								});
						FadeTransition fadeTransition2 = new FadeTransition(
								Duration.millis(2000), tileB);
						fadeTransition2.setFromValue(1);
						fadeTransition2.setToValue(0);
						fadeTransition2.play();
						fadeTransition2
								.setOnFinished(new EventHandler<ActionEvent>() {
									public void handle(ActionEvent e) {
										tileB.getChildren().clear();

									}
								});
						canvas.tempStart = null;
						canvas.tempEnd = null;

						if (0 == canvas.victory) {

							System.out.println("Victory !");

						}

					} else {

						AudioClip click = AudioClipBuilder
								.create()
								.source(getClass().getResource(
										"audios/dodge.mp3").toExternalForm())
								.cycleCount(1).build();
						click.play();

						canvas.tempStart.setEffect(null);
						canvas.tempEnd.setEffect(null);
						canvas.tempStart = null;
						canvas.tempEnd = null;

					}

				} else {

					AudioClip click = AudioClipBuilder
							.create()
							.source(getClass().getResource("audios/dodge.mp3")
									.toExternalForm()).cycleCount(1).build();
					click.play();

					canvas.tempStart.setEffect(null);
					canvas.tempEnd.setEffect(null);
					canvas.tempStart = null;
					canvas.tempEnd = null;

				}

			}

		}

	}

}
