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
		this.sprite = Assets.pinkBloon;
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new YellowBloon(pathing));
	}

	@Override
	public Bloon copy() {
		return new PinkBloon(this.pathingSave);
	}
}
