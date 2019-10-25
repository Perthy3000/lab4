package building;

import building.base.Building;
import building.base.ItemProducer;
import item.Item;
import item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import ui.BuildingIcon;
import ui.ItemIcon;

public class Drill extends Building implements ItemProducer {

	private static final int HARVEST_INTERVAL = 4;
	private static final int ENERGY_CONSUMPTION = 5;
	

	@Override
	public void render(StackPane target) {
		BuildingIcon icon = new BuildingIcon("file:res/drill.png");
		target.getChildren().add(icon);
		
		// TODO: fill this boolean
		boolean readyToProduceIron;		// is this drill ready to produce iron?
		
		if(readyToProduceIron) {
			ItemIcon itemIcon = ItemType.IRON.toItemIcon();
			StackPane.setAlignment(itemIcon, Pos.TOP_CENTER);
			target.getChildren().add(itemIcon);
		}
	}
}
