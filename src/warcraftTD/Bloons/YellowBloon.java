package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;

public class YellowBloon extends Bloon {

	public YellowBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 3.2;
		this.hp = 1;
		this.power = 4;
		this.sprite = Assets.yellowBloon;
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new GreenBloon(pathing));
	}

	@Override
	public Bloon copy() {
		return new YellowBloon(this.pathingSave);
	}
}
