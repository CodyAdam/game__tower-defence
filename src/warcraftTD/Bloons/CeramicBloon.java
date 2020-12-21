package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;
import warcraftTD.Position;

public class CeramicBloon extends Bloon {

	public CeramicBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 2.5;
		this.hp = 10;
		this.power = 95;
		this.imgPath = "/Assets/Sprites/Bloons/Ceramic_0.png";
		this.center = new Position(0.5, 0.5);
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new RaimbowBloon(pathing));
		this.spawnOnDeath.add(new RaimbowBloon(pathing));
	}
}
