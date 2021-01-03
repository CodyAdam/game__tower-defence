package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;

public class PinkBloon extends Bloon {

	public PinkBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 3.5;
		this.hp = 1;
		this.power = 5;
		this.imgPath = Assets.pinkBloon;
		this.center = new Position(0.5, 0.5);
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new YellowBloon(pathing));
	}
}
