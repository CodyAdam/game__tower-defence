package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;

public class RedBloon extends Bloon {

	public RedBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 1;
		this.hp = 1;
		this.power = 1;
		this.imgPath = Assets.redBloon;
		this.spawnOnDeath = new ArrayList<Bloon>();
	}

}
