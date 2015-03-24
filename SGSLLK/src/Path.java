public class Path {

	public int type;

	public Point startpoint;
	public Point turn1;
	public Point turn2;
	public Point endpoint;

	public int dic = 18;

	public Path() {

		this.type = -1;

	}

	public Path(Point sp, Point ep) {

		this.type = 0;
		this.startpoint = sp;
		this.endpoint = ep;

		dic = Math.abs(sp.x - ep.x) + Math.abs(sp.y - ep.y);
	}

	public Path(Point sp, Point t1, Point ep) {

		this.type = 1;
		this.startpoint = sp;
		this.turn1 = t1;
		this.endpoint = ep;

		dic = Math.abs(sp.x - t1.x) + Math.abs(sp.y - t1.y)
				+ Math.abs(t1.x - ep.x) + Math.abs(t1.y - ep.y);

	}

	public Path(Point sp, Point t1, Point t2, Point ep) {

		this.type = 2;
		this.startpoint = sp;
		this.turn1 = t1;
		this.turn2 = t2;
		this.endpoint = ep;

		dic = Math.abs(sp.x - t1.x) + Math.abs(sp.y - t1.y)
				+ Math.abs(t1.x - t2.x) + Math.abs(t1.y - t2.y)
				+ Math.abs(t2.x - ep.x) + Math.abs(t2.y - ep.y);

	}

	public void PathCompare(Path path) {

		if (this.dic > path.dic || this.type == -1) {

			this.type = path.type;
			this.startpoint = path.startpoint;
			this.turn1 = path.turn1;
			this.turn2 = path.turn2;
			this.endpoint = path.endpoint;
			this.dic = path.dic;

		}

	}

}