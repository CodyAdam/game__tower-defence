package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;

public class MetalBloon extends Bloon {

	public MetalBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 1.00;
		this.hp = 1;
		this.power = 23;
		this.sprite = Assets.metalBloon;
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new BlackBloon(pathing));
		this.spawnOnDeath.add(new BlackBloon(pathing));
	}

	@Override
	public Bloon copy() {
		return new MetalBloon(this.pathingSave);
	}
}
