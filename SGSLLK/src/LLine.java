import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;

public class LLine extends Group {

	public LLine(Path path) {

		if (0 == path.type) {
			Line line0 = LineBuilder.create()
					.startX(path.startpoint.y * 50 + 25)
					.startY(path.startpoint.x * 50 + 25)
					.endX(path.endpoint.y * 50 + 25)
					.endY(path.endpoint.x * 50 + 25).fill(Color.RED)
					.strokeWidth(3).stroke(Color.RED).build();
			this.getChildren().add(line0);
		}

		if (1 == path.type) {
			Line line0 = LineBuilder.create()
					.startX(path.startpoint.y * 50 + 25)
					.startY(path.startpoint.x * 50 + 25)
					.endX(path.turn1.y * 50 + 25).endY(path.turn1.x * 50 + 25)
					.fill(Color.RED).strokeWidth(3).stroke(Color.RED).build();
			Line line1 = LineBuilder.create().startX(path.turn1.y * 50 + 25)
					.startY(path.turn1.x * 50 + 25)
					.endX(path.endpoint.y * 50 + 25)
					.endY(path.endpoint.x * 50 + 25).fill(Color.RED)
					.strokeWidth(3).stroke(Color.RED).build();
			this.getChildren().addAll(line0, line1);
		}

		if (2 == path.type) {
			Line line0 = LineBuilder.create()
					.startX(path.startpoint.y * 50 + 25)
					.startY(path.startpoint.x * 50 + 25)
					.endX(path.turn1.y * 50 + 25).endY(path.turn1.x * 50 + 25)
					.fill(Color.RED).strokeWidth(3).stroke(Color.RED).build();
			Line line1 = LineBuilder.create().startX(path.turn1.y * 50 + 25)
					.startY(path.turn1.x * 50 + 25)
					.endX(path.turn2.y * 50 + 25).endY(path.turn2.x * 50 + 25)
					.fill(Color.RED).strokeWidth(3).stroke(Color.RED).build();
			Line line2 = LineBuilder.create().startX(path.turn2.y * 50 + 25)
					.startY(path.turn2.x * 50 + 25)
					.endX(path.endpoint.y * 50 + 25)
					.endY(path.endpoint.x * 50 + 25).fill(Color.RED)
					.strokeWidth(3).stroke(Color.RED).build();
			this.getChildren().addAll(line0, line1, line2);
		}

	}
}
