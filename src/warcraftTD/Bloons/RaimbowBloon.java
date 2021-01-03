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
		this.imgPath = Assets.raimbowBloon;
		this.center = new Position(0.5, 0.5);
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new ZebraBloon(pathing));
		this.spawnOnDeath.add(new ZebraBloon(pathing));
	}
}
