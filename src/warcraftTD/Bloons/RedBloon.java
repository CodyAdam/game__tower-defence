package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;
import warcraftTD.Position;

public class RedBloon extends Bloon {

	public RedBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 1;
		this.hp = 1;
		this.power = 1;
		this.imgPath = "src/Assets/Sprites/Bloons/Red.png";
		this.center = new Position(0.5, 0.5);
		this.spawnOnDeath = new ArrayList<Bloon>();
	}

}
