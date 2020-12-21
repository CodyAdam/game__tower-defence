package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;
import warcraftTD.Position;
import warcraftTD.StdDraw;

public class RedAirBloon extends Bloon {

	public RedAirBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 0.25;
		this.hp = 700;
		this.power = 3164;
		this.imgPath = "src/Assets/Sprites/Bloons/RedAirBloon.png";
		this.center = new Position(0.5, 0.5);
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new BlueAirBloon(pathing));
		this.spawnOnDeath.add(new BlueAirBloon(pathing));
		this.spawnOnDeath.add(new BlueAirBloon(pathing));
		this.spawnOnDeath.add(new BlueAirBloon(pathing));
	}

	@Override
	public void draw() { // Turn the air balloon toward the next chackpoint and draw it
		if (!this.pathing.isEmpty()) {
			Position dir = this.pathing.getFirst().minus(this.pos);
			StdDraw.picture(this.pos.x, this.pos.y, this.imgPath, dir.angle() - 90);
		}
	}
}
