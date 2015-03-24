import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.util.Duration;

public class Canvas extends Group {

	public Tile tempStart;
	public Tile tempEnd;
	public int victory;
	public Tile table[][];
	public Path path;

	public Canvas() {
	}

	public void initGame() {
		this.getChildren().clear();
		this.create();
	}

	public void create() {

		table = new Tile[8][10];

		victory = 48;

		setTranslateX(150);
		setTranslateY(100);

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 10; col++) {

				if (table[row][col] != null) {
					continue;
				}

				Tile tile;

				if (0 == row || 7 == row || 0 == col || 9 == col) {

					tile = new Tile();
					table[row][col] = tile;

				} else {

					int random1 = new Random().nextInt(9);

					tile = new Tile(this, random1);
					tile.setLayoutX(col * 50);
					tile.setLayoutY(row * 50);
					getChildren().add(tile);
					table[row][col] = tile;

					int sit = (row - 1) * 8 + col;
					int random2 = new Random().nextInt(48 - sit);
					int rrow = (random2 + sit) / 8 + 1;
					int rcol = (random2 + sit) % 8 + 1;

					while (null != table[rrow][rcol]) {

						rcol++;
						if (9 == rcol) {
							rcol = 1;
							rrow++;
						}
						if (7 == rrow)
							rrow = 1;
					}

					Tile tile2 = new Tile(this, random1);
					tile2.setLayoutX(rcol * 50);
					tile2.setLayoutY(rrow * 50);
					getChildren().add(tile2);
					table[rrow][rcol] = tile2;

				}

			}
		}

	}

	public void refresh() {

		for (int row = 1; row < 7; row++) {
			for (int col = 1; col < 9; col++) {

				Tile temp = table[row][col];

				int randomr = new Random().nextInt(48);
				int rrow = randomr / 8 + 1;
				int rcol = randomr % 8 + 1;

				table[rrow][rcol].setLayoutX(col * 50);
				table[rrow][rcol].setLayoutY(row * 50);

				table[row][col].setLayoutX(rcol * 50);
				table[row][col].setLayoutY(rrow * 50);

				table[row][col] = table[rrow][rcol];
				table[rrow][rcol] = temp;

			}
		}

	}

	public void hint() {

	}

	public void find(Tile tile1, Tile tile2) {

		path = new Path();

		Tile tileA = tile1;
		Tile tileB = tile2;

		Point pointA;
		Point pointB;

		int flagx = 0;
		int flagy = 0;

		int indexx;
		int indexy;

		int rowA = (int) tileA.getLayoutY() / 50;
		int colA = (int) tileA.getLayoutX() / 50;

		int rowB = (int) tileB.getLayoutY() / 50;
		int colB = (int) tileB.getLayoutX() / 50;

		// Í¬Ò»ÐÐ

		if (rowA == rowB) {

			if (colA < colB) {
				pointA = new Point(rowA, colA);
				pointB = new Point(rowB, colB);
			} else {
				pointA = new Point(rowB, colB);
				pointB = new Point(rowA, colA);
			}

			// ºáÁ¬Ïß

			indexx = pointA.x;
			indexy = pointA.y + 1;

			while (flagy == 0 && indexy < pointB.y) {

				if (table[indexx][indexy].type != -1) {
					flagy = 1;
				} else {
					indexy++;
				}

			}

			if (1 == pointB.y - pointA.y) {
				flagy = 0;
			}

			if (flagy == 0) {
				Path tpath = new Path(pointA, pointB);
				path.PathCompare(tpath);
				return;
			}

			// ÉÏËÑË÷

			indexx = pointA.x - 1;
			indexy = pointA.y;

			flagx = 0;
			flagy = 0;

			while (indexx >= 0 && flagx == 0) {

				if (table[indexx][pointA.y].type != -1
						|| table[indexx][pointB.y].type != -1)
					break;
				if (findrow(indexx, pointA.y + 1, pointB.y - 1)) {
					Path tpath = new Path(pointA, new Point(indexx, pointA.y),
							new Point(indexx, pointB.y), pointB);
					path.PathCompare(tpath);
					break;
				}
				indexx--;

			}

			// ÏÂËÑË÷

			indexx = pointA.x + 1;
			indexy = pointA.y;

			flagx = 0;
			flagy = 0;

			while (indexx <= 8 && flagx == 0) {

				if (table[indexx][pointA.y].type != -1
						|| table[indexx][pointB.y].type != -1)
					break;
				if (findrow(indexx, pointA.y + 1, pointB.y - 1)) {
					Path tpath = new Path(pointA, new Point(indexx, pointA.y),
							new Point(indexx, pointB.y), pointB);
					path.PathCompare(tpath);
					break;
				}
				indexx++;

			}

		}

		// Í¬Ò»ÁÐ

		if (colA == colB) {

			if (rowA < rowB) {
				pointA = new Point(rowA, colA);
				pointB = new Point(rowB, colB);
			} else {
				pointA = new Point(rowB, colB);
				pointB = new Point(rowA, colA);
			}

			// ÊúÁ¬Ïß

			indexx = pointA.x + 1;
			indexy = pointA.y;

			flagx = 0;
			flagy = 0;

			while (flagx == 0 && indexx < pointB.x) {

				if (table[indexx][indexy].type != -1) {
					flagx = 1;
				} else {
					indexx++;
				}

			}

			if (1 == pointB.x - pointA.x) {
				flagx = 0;
			}

			if (flagx == 0) {
				Path tpath = new Path(pointA, pointB);
				path.PathCompare(tpath);
				return;
			}

			// ×óËÑË÷

			indexx = pointA.x;
			indexy = pointA.y - 1;

			flagx = 0;
			flagy = 0;

			while (indexy >= 0 && flagy == 0) {

				if (table[pointA.x][indexy].type != -1
						|| table[pointB.x][indexy].type != -1)
					break;
				if (findcol(indexy, pointA.x + 1, pointB.x - 1)) {
					Path tpath = new Path(pointA, new Point(pointA.x, indexy),
							new Point(pointB.x, indexy), pointB);
					path.PathCompare(tpath);
					break;
				}
				indexy--;

			}

			// ÓÒËÑË÷

			indexx = pointA.x;
			indexy = pointA.y + 1;

			flagx = 0;
			flagy = 0;

			while (indexy <= 10 && flagy == 0) {

				if (table[pointA.x][indexy].type != -1
						|| table[pointB.x][indexy].type != -1)
					break;
				if (findcol(indexy, pointA.x + 1, pointB.x - 1)) {
					Path tpath = new Path(pointA, new Point(pointA.x, indexy),
							new Point(pointB.x, indexy), pointB);
					path.PathCompare(tpath);
					break;
				}
				indexy++;

			}

		}

		// ¶Ô½Ç

		if ((rowA < rowB && colA < colB) || (rowA > rowB && colA > colB)) {

			if (rowA < rowB && colA < colB) {
				pointA = new Point(rowA, colA);
				pointB = new Point(rowB, colB);
			} else {
				pointA = new Point(rowB, colB);
				pointB = new Point(rowA, colA);
			}

			// ¶Ô½ÇËÑË÷

			indexx = pointA.x;
			indexy = pointA.y;

			flagx = 0;
			flagy = 0;

			if (findrow(pointA.x, pointA.y + 1, pointB.y)
					&& findcol(pointB.y, pointA.x, pointB.x - 1)) {
				Path tpath = new Path(pointA, new Point(pointA.x, pointB.y),
						pointB);
				path.PathCompare(tpath);
				return;
			}
			if (findcol(pointA.y, pointA.x + 1, pointB.x)
					&& findrow(pointB.x, pointA.y, pointB.y - 1)) {
				Path tpath = new Path(pointA, new Point(pointB.x, pointA.y),
						pointB);
				path.PathCompare(tpath);
				return;
			}

			// ÄÚËÑË÷

			for (indexy = pointA.y + 1; indexy < pointB.y; indexy++) {
				if (findrow(pointA.x, pointA.y + 1, indexy)
						&& findcol(indexy, pointA.x, pointB.x)
						&& findrow(pointB.x, indexy, pointB.y - 1)) {
					Path tpath = new Path(pointA, new Point(pointA.x, indexy),
							new Point(pointB.x, indexy), pointB);
					path.PathCompare(tpath);
					return;
				}
			}

			for (indexx = pointA.x + 1; indexx < pointB.x; indexx++) {
				if (findcol(pointA.y, pointA.x + 1, indexx)
						&& findrow(indexx, pointA.y, pointB.y)
						&& findcol(pointB.y, indexx, pointB.x - 1)) {
					Path tpath = new Path(pointA, new Point(indexx, pointA.y),
							new Point(indexx, pointB.y), pointB);
					path.PathCompare(tpath);
					return;
				}
			}

			// ÉÏËÑË÷

			indexx = pointA.x - 1;
			indexy = pointA.y;

			flagx = 0;
			flagy = 0;

			if (findcol(pointB.y, pointA.x, pointB.x - 1)) {

				while (indexx >= 0 && flagx == 0) {

					if (table[indexx][pointA.y].type != -1
							|| table[indexx][pointB.y].type != -1)
						break;
					if (findrow(indexx, pointA.y + 1, pointB.y - 1)) {
						Path tpath = new Path(pointA, new Point(indexx,
								pointA.y), new Point(indexx, pointB.y), pointB);
						path.PathCompare(tpath);
						break;
					}
					indexx--;

				}

			}

			// ÏÂËÑË÷

			indexx = pointB.x + 1;
			indexy = pointA.y;

			flagx = 0;
			flagy = 0;

			if (findcol(pointA.y, pointA.x + 1, pointB.x)) {

				while (indexx <= 8 && flagx == 0) {

					if (table[indexx][pointA.y].type != -1
							|| table[indexx][pointB.y].type != -1)
						break;
					if (findrow(indexx, pointA.y + 1, pointB.y - 1)) {
						Path tpath = new Path(pointA, new Point(indexx,
								pointA.y), new Point(indexx, pointB.y), pointB);
						path.PathCompare(tpath);
						break;
					}
					indexx++;

				}

			}

			// ×óËÑË÷

			indexx = pointA.x;
			indexy = pointA.y - 1;

			flagx = 0;
			flagy = 0;

			if (findrow(pointB.x, pointA.y, pointB.y - 1)) {

				while (indexy >= 0 && flagy == 0) {

					if (table[pointA.x][indexy].type != -1
							|| table[pointB.x][indexy].type != -1)
						break;
					if (findcol(indexy, pointA.x + 1, pointB.x - 1)) {
						Path tpath = new Path(pointA, new Point(pointA.x,
								indexy), new Point(pointB.x, indexy), pointB);
						path.PathCompare(tpath);
						break;
					}
					indexy--;

				}
			}

			// ÓÒËÑË÷

			indexx = pointA.x;
			indexy = pointB.y + 1;

			flagx = 0;
			flagy = 0;

			if (findrow(pointA.x, pointA.y + 1, pointB.y)) {

				while (indexy <= 10 && flagy == 0) {

					if (table[pointA.x][indexy].type != -1
							|| table[pointB.x][indexy].type != -1)
						break;
					if (findcol(indexy, pointA.x + 1, pointB.x - 1)) {
						Path tpath = new Path(pointA, new Point(pointA.x,
								indexy), new Point(pointB.x, indexy), pointB);
						path.PathCompare(tpath);
						break;
					}
					indexy++;

				}
			}

		}

		// ·´¶Ô½Ç

		if ((rowA < rowB && colA > colB) || (rowA > rowB && colA < colB)) {

			if (rowA < rowB && colA > colB) {
				pointA = new Point(rowA, colA);
				pointB = new Point(rowB, colB);
			} else {
				pointA = new Point(rowB, colB);
				pointB = new Point(rowA, colA);
			}

			// ·´¶Ô½ÇËÑË÷

			indexx = pointA.x;
			indexy = pointB.y;

			flagx = 0;
			flagy = 0;

			if (findrow(pointA.x, pointB.y, pointA.y - 1)
					&& findcol(pointB.y, pointA.x, pointB.x - 1)) {
				Path tpath = new Path(pointA, new Point(pointA.x, pointB.y),
						pointB);
				path.PathCompare(tpath);
				return;
			}
			if (findcol(pointA.y, pointA.x + 1, pointB.x)
					&& findrow(pointB.x, pointB.y + 1, pointA.y)) {
				Path tpath = new Path(pointA, new Point(pointB.x, pointA.y),
						pointB);
				path.PathCompare(tpath);
				return;
			}

			// ÄÚËÑË÷

			for (indexy = pointB.y + 1; indexy < pointA.y; indexy++) {
				if (findrow(pointB.x, pointB.y + 1, indexy)
						&& findcol(indexy, pointA.x, pointB.x)
						&& findrow(pointA.x, indexy, pointA.y - 1)) {
					Path tpath = new Path(pointA, new Point(pointA.x, indexy),
							new Point(pointB.x, indexy), pointB);
					path.PathCompare(tpath);
					return;
				}
			}

			for (indexx = pointA.x + 1; indexx < pointB.x; indexx++) {
				if (findcol(pointA.y, pointA.x + 1, indexx)
						&& findrow(indexx, pointB.y, pointA.y)
						&& findcol(pointB.y, indexx, pointB.x - 1)) {
					Path tpath = new Path(pointA, new Point(indexx, pointA.y),
							new Point(indexx, pointB.y), pointB);
					path.PathCompare(tpath);
					return;
				}
			}

			// ÉÏËÑË÷

			indexx = pointA.x - 1;
			indexy = pointA.y;

			flagx = 0;
			flagy = 0;

			if (findcol(pointB.y, pointA.x, pointB.x - 1)) {

				while (indexx >= 0 && flagx == 0) {

					if (table[indexx][pointA.y].type != -1
							|| table[indexx][pointB.y].type != -1)
						break;
					if (findrow(indexx, pointB.y + 1, pointA.y - 1)) {
						Path tpath = new Path(pointA, new Point(indexx,
								pointA.y), new Point(indexx, pointB.y), pointB);
						path.PathCompare(tpath);
						break;
					}
					indexx--;

				}

			}

			// ÏÂËÑË÷

			indexx = pointB.x + 1;
			indexy = pointA.y;

			flagx = 0;
			flagy = 0;

			if (findcol(pointA.y, pointA.x + 1, pointB.x)) {

				while (indexx <= 8 && flagx == 0) {

					if (table[indexx][pointA.y].type != -1
							|| table[indexx][pointB.y].type != -1)
						break;
					if (findrow(indexx, pointB.y + 1, pointA.y - 1)) {
						Path tpath = new Path(pointA, new Point(indexx,
								pointA.y), new Point(indexx, pointB.y), pointB);
						path.PathCompare(tpath);
						break;
					}
					indexx++;

				}

			}

			// ×óËÑË÷

			indexx = pointA.x;
			indexy = pointB.y - 1;

			flagx = 0;
			flagy = 0;

			if (findrow(pointA.x, pointB.y, pointA.y - 1)) {

				while (indexy >= 0 && flagy == 0) {

					if (table[pointA.x][indexy].type != -1
							|| table[pointB.x][indexy].type != -1)
						break;
					if (findcol(indexy, pointA.x + 1, pointB.x - 1)) {
						Path tpath = new Path(pointA, new Point(pointA.x,
								indexy), new Point(pointB.x, indexy), pointB);
						path.PathCompare(tpath);
						break;
					}
					indexy--;

				}
			}

			// ÓÒËÑË÷

			indexx = pointA.x;
			indexy = pointA.y + 1;

			flagx = 0;
			flagy = 0;

			if (findrow(pointB.x, pointB.y + 1, pointA.y)) {

				while (indexy <= 10 && flagy == 0) {

					if (table[pointA.x][indexy].type != -1
							|| table[pointB.x][indexy].type != -1)
						break;
					if (findcol(indexy, pointA.x + 1, pointB.x - 1)) {
						Path tpath = new Path(pointA, new Point(pointA.x,
								indexy), new Point(pointB.x, indexy), pointB);
						path.PathCompare(tpath);
						break;
					}
					indexy++;

				}
			}

		}

	}

	public boolean findrow(int row, int colstart, int colend) {

		int col = colstart;

		while (col <= colend) {

			if (table[row][col].type != -1)
				return false;
			col++;

		}

		return true;

	}

	public boolean findcol(int col, int rowstart, int rowend) {

		int row = rowstart;

		while (row <= rowend) {

			if (table[row][col].type != -1)
				return false;
			row++;

		}

		return true;

	}

	public void draw() {

		LLine lline = new LLine(path);
		this.getChildren().add(lline);
		FadeTransition fadeTransition = new FadeTransition(
				Duration.millis(2000), lline);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.play();

	}

}
