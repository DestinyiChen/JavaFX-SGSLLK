import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ButtonPanel extends HBox {

	private Canvas canvas;
	private ImageView bgView;

	public ButtonPanel(Canvas canvas, ImageView bgView) {

		this.canvas = canvas;
		this.bgView = bgView;

		final Button start = createButton(" ��    ʼ ");
		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				ButtonPanel.this.canvas.initGame();
				ButtonPanel.this.bgView.setOpacity(0.75);
				start.setText("���¿�ʼ");
			}
		});

		Button refresh = createButton(" ˢ    �� ");
		refresh.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				ButtonPanel.this.canvas.refresh();
			}
		});

		Button hint = createButton(" ��    ʾ ");
		hint.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				ButtonPanel.this.canvas.hint();
			}
		});

		Button clear = createButton(" ��    �� ");
		clear.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				ButtonPanel.this.canvas.getChildren().clear();
				ButtonPanel.this.bgView.setOpacity(1);
				start.setText(" ��    ʼ ");
			}
		});

		this.getChildren().addAll(start, refresh, hint, clear);
		this.setLayoutX(240);

	}

	private Button createButton(String text) {
		Button button = new Button(text);
		button.setPrefWidth(80);
		return button;
	}

}