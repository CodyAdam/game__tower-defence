package warcraftTD.Bloons;

import java.util.ArrayList;
import java.util.List;

import warcraftTD.Assets;
import warcraftTD.Position;
import warcraftTD.StdDraw;

public class CeramicBloon extends Bloon {

	public CeramicBloon(List<Position> pathing) {
		super(pathing);
		this.speed *= 2.5;
		this.hp = 10;
		this.power = 95;
		this.money = 10;
		this.sprite = Assets.ceramicBloon0;
		this.spawnOnDeath = new ArrayList<Bloon>();

		this.spawnOnDeath.add(new RaimbowBloon(pathing));
		this.spawnOnDeath.add(new RaimbowBloon(pathing));
	}

	@Override
	public void draw() {
		if (targetable) { // affiche un Bloon plus ou moins dégradé en fonction de ses points de vie
			String image;
			if (hp > 8)
				image = sprite;
			else if (hp > 6)
				image = Assets.ceramicBloon1;
			else if (hp > 4)
				image = Assets.ceramicBloon2;
			else if (hp > 2)
				image = Assets.ceramicBloon3;
			else
				image = Assets.ceramicBloon4;

			StdDraw.picture(this.pos.x, this.pos.y, image);
		}
	}
}
