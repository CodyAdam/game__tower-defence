package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.StdDraw;

public class BlueAirBloon extends Bloon {

	public BlueAirBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 1.00;
		this.hp = 200;
		this.power = 616;
		this.imgPath = Assets.blueAirBloon;
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new CeramicBloon(pathing));
		this.spawnOnDeath.add(new CeramicBloon(pathing));
		this.spawnOnDeath.add(new CeramicBloon(pathing));
		this.spawnOnDeath.add(new CeramicBloon(pathing));

	}

	@Override
	public void draw() { // Turn the air balloon toward the next chackpoint and draw it
		if (!this.pathing.isEmpty()) {
			Position dir = this.pathing.getFirst().minus(this.pos);
			StdDraw.picture(this.pos.x, this.pos.y, this.imgPath, dir.angle() - 90);
		}
	}
}
