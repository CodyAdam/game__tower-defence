package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;
import warcraftTD.Position;
import warcraftTD.StdDraw;

public class BlackAirBloon extends Bloon {

	public BlackAirBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 0.18;
		this.hp = 4000;
		this.power = 16656;
		this.imgPath = "src/Assets/Sprites/Bloons/BlackAirBloon.png";
		this.center = new Position(0.5, 0.5);
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new RedAirBloon(pathing));
		this.spawnOnDeath.add(new RedAirBloon(pathing));
		this.spawnOnDeath.add(new RedAirBloon(pathing));
		this.spawnOnDeath.add(new RedAirBloon(pathing));
	}

	@Override
	public void draw() { // Turn the air balloon toward the next chackpoint and draw it
		if (!this.pathing.isEmpty()) {
			Position dir = this.pathing.getFirst().minus(this.pos);
			StdDraw.picture(this.pos.x, this.pos.y, this.imgPath, dir.angle() - 90);
		}
	}
}
