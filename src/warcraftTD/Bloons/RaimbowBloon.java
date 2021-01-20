package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;

public class RaimbowBloon extends Bloon {

	public RaimbowBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 2.2;
		this.hp = 1;
		this.power = 47;
		this.sprite = Assets.raimbowBloon;
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new ZebraBloon(pathing));
		this.spawnOnDeath.add(new ZebraBloon(pathing));
	}

	@Override
	public Bloon copy() {
		return new RaimbowBloon(this.pathingSave);
	}
}
