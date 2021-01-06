package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;

public class BlueBloon extends Bloon {

	public BlueBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 1.4;
		this.hp = 1;
		this.power = 2;
		this.sprite = Assets.blueBloon;
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new RedBloon(pathing));
	}
}
