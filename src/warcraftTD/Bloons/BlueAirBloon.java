package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.StdDraw;

public class BlueAirBloon extends Bloon {

	public BlueAirBloon(List<Position> pathing) {
		super(pathing);
		speed *= 1.00;
		hp = 200;
		power = 616;
		hitboxRadius *= 3.8;
		sprite = Assets.blueAirBloon;
		spawnOnDeath = new ArrayList<Bloon>();

		spawnOnDeath.add(new CeramicBloon(pathing));
		spawnOnDeath.add(new CeramicBloon(pathing));
		spawnOnDeath.add(new CeramicBloon(pathing));
		spawnOnDeath.add(new CeramicBloon(pathing));

	}

	@Override
	public void draw(boolean debug) { // Turn the air balloon toward the next chackpoint and draw it
		if (!this.pathing.isEmpty() && targetable) {
			Position dir = this.pathing.getFirst().minus(this.pos);
			StdDraw.picture(this.pos.x, this.pos.y, this.sprite, dir.angle() - 90);
		}
		if (debug)
			drawHitbox();
	}
}
