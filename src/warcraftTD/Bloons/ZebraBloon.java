package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;
import warcraftTD.Position;

public class ZebraBloon extends Bloon {

	public ZebraBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 1.8;
		this.hp = 1;
		this.power = 23;
		this.imgPath = "src/Assets/Sprites/Bloons/Zebra.png";
		this.center = new Position(0.5, 0.5);
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new IceBloon(pathing));
		this.spawnOnDeath.add(new BlackBloon(pathing));
	}
}
