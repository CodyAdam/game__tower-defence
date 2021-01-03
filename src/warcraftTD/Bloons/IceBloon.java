package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;

public class IceBloon extends Bloon {

	public IceBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 2.00;
		this.hp = 1;
		this.power = 11;
		this.imgPath = Assets.iceBloon;
		this.center = new Position(0.5, 0.5);
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new PinkBloon(pathing));
		this.spawnOnDeath.add(new PinkBloon(pathing));
	}
}
