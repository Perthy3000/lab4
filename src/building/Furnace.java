package building;

import building.base.ItemProducer;
import building.base.ItemReceiver;
import item.Item;
import item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import ui.BuildingIcon;
import ui.ItemIcon;

public class Furnace extends Building implements ItemProducer, ItemReceiver {

	private static final int CRAFT_DELAY = 3;
	private static final int ENERGY_CONSUMPTION = 10;
		
	@Override
	public void render(StackPane target) {
		BuildingIcon icon = new BuildingIcon("file:res/furnace.png");
		target.getChildren().add(icon);
		
		// TODO: fill these booleans
		boolean readyToProduceIronPlate;		// is this furnace ready to produce an iron plate?
		boolean hasIron;						// does this furnace has iron in it?
		
		ItemIcon itemIcon = null;
		if (readyToProduceIronPlate) {
			itemIcon = ItemType.IRON_PLATE.toItemIcon();
		} else if(hasIron) {
			itemIcon = ItemType.IRON.toItemIcon();
		}
		
		if(itemIcon != null) {
			StackPane.setAlignment(itemIcon, Pos.TOP_CENTER);
			target.getChildren().add(itemIcon);
		}
	}

}
