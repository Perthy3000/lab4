package building;

import building.base.Building;
import building.base.ItemProducer;
import item.Item;
import item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import ui.BuildingIcon;
import ui.ItemIcon;

public class Sawmill extends Building implements ItemProducer {

	private static final int HARVEST_INTERVAL = 3;
	private int currentCycle = 0;
	//private int woodInStock = 0;
	
	@Override
	public void render(StackPane target) {
		BuildingIcon icon = new BuildingIcon("file:res/sawmill.png");
		target.getChildren().add(icon);
		
		// TODO: fill this boolean
		boolean readyToProduceWood = canProduceItem();		// is this sawmill ready to produce wood?
		
		if(readyToProduceWood) {
			ItemIcon itemIcon = ItemType.WOOD.toItemIcon();
			StackPane.setAlignment(itemIcon, Pos.TOP_CENTER);
			target.getChildren().add(itemIcon);
		}
	}
	
	@Override
	public void operate() {
		if(currentCycle < HARVEST_INTERVAL) {
			currentCycle++;			
		}
	}

	@Override
	public boolean canProduceItem() {
		if(currentCycle == HARVEST_INTERVAL) {
			return true;
		}
		return false;
	}

	@Override
	public Item getProducedItem() {
		return Item.wood();
	}

}
