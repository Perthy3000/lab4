package building;

import building.base.ItemProducer;
import building.base.ItemReceiver;
import item.Item;
import item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import ui.BuildingIcon;

public class Anvil extends Building implements ItemProducer, ItemReceiver {
	private static final int CRAFT_DELAY = 5;
	private static final int ENERGY_CONSUMPTION = 15;
	
	@Override
	public void render(StackPane target) {
		BuildingIcon icon = new BuildingIcon("file:res/anvil.png");
		target.getChildren().add(icon);
		
		
		HBox status = new HBox();
		StackPane.setAlignment(status, Pos.TOP_CENTER);
		
		// TODO: fill this booleans
		boolean ready;			// is this anvil ready (finished crafting iron sword)?
		boolean hasWood;		// does this anvil has wood on it?
		boolean hasIronPlate;	// does this anvil has an iron plate on it?
		
		if(ready) {
			status.getChildren().add(ItemType.IRON_SWORD.toItemIcon());
		} else {
			if(hasWood) {
				status.getChildren().add(ItemType.WOOD.toItemIcon());
			}
			if(hasIronPlate) {
				status.getChildren().add(ItemType.IRON_PLATE.toItemIcon());
			}
		}
		target.getChildren().add(status);
	}
}
