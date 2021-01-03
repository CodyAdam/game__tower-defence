package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;

public class GreenBloon extends Bloon {

	public GreenBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 1.8;
		this.hp = 1;
		this.power = 1;
		this.imgPath = Assets.greenBloon;
		this.center = new Position(0.5, 0.5);
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new BlueBloon(pathing));
	}
}
