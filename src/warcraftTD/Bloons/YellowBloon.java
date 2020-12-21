package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;
import warcraftTD.Position;

public class YellowBloon extends Bloon {

	public YellowBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 3.2;
		this.hp = 1;
		this.power = 4;
		this.imgPath = "src/Assets/Sprites/Bloons/Yellow.png";
		this.center = new Position(0.5, 0.5);
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new GreenBloon(pathing));
	}
}
