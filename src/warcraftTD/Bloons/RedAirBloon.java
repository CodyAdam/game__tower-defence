package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.StdDraw;

public class RedAirBloon extends Bloon {

	public RedAirBloon(List<Position> pathing) {
		super(pathing);
		speed *= 0.25;
		hp = 700;
		power = 3164;
		hitboxRadius *= 3.5;
		sprite = Assets.redAirBloon;
		spawnOnDeath = new ArrayList<Bloon>();

		spawnOnDeath.add(new BlueAirBloon(pathing));
		spawnOnDeath.add(new BlueAirBloon(pathing));
		spawnOnDeath.add(new BlueAirBloon(pathing));
		spawnOnDeath.add(new BlueAirBloon(pathing));
	}

	@Override
	public void draw(boolean debug) { // Turn the air balloon toward the next chackpoint and draw it
		if (!this.pathing.isEmpty() && targetable) {
			Position dir = this.pathing.getFirst().minus(this.pos);
			StdDraw.picture(this.pos.x, this.pos.y, this.sprite, dir.inGridSpace(false).angle(new Position(0, 1)));
		}
		if (debug)
			drawHitbox();
	}

	@Override
	public Bloon copy() {
		return new RedAirBloon(this.pathingSave);
	}
}
