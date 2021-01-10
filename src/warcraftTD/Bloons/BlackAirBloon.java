package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.StdDraw;

public class BlackAirBloon extends Bloon {

	public BlackAirBloon(List<Position> pathing) {
		super(pathing);
		speed *= 0.18;
		hp = 4000;
		power = 16656;
		sprite = Assets.blackAirBloon;
		hitboxRadius *= 6;
		spawnOnDeath = new ArrayList<Bloon>();

		spawnOnDeath.add(new RedAirBloon(pathing));
		spawnOnDeath.add(new RedAirBloon(pathing));
		spawnOnDeath.add(new RedAirBloon(pathing));
		spawnOnDeath.add(new RedAirBloon(pathing));
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
