package building;

import java.util.HashMap;
import java.util.Map;
import building.base.Building;
import building.base.ItemReceiver;
import item.Item;
import item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import ui.BuildingIcon;

public class Chest extends Building implements ItemReceiver {
	
	Map<ItemType, Integer> itemStored = new HashMap<ItemType, Integer>();

	@Override
	public void render(StackPane target) {
		BuildingIcon icon = new BuildingIcon("file:res/chest.png");
		target.getChildren().add(icon);
		
		Label numberOfItemsLabel = new Label();
		String numberOfItemsText = Integer.toString(this.getInventory().values().stream().reduce(0, (a, b) -> a+b));
		numberOfItemsLabel.setText(numberOfItemsText);
		StackPane.setAlignment(numberOfItemsLabel, Pos.TOP_CENTER);

		target.getChildren().add(numberOfItemsLabel);
	}
	
	/* getters & setters */
	
	public Map<ItemType, Integer> getInventory() {
		return itemStored;
	}

	@Override
	public void operate() {

	}
	
	@Override
	public boolean canReceiveItem(ItemType oftype) {
		if(oftype != null) {
			return true;			
		}
		return false;
	}

	@Override
	public void receiveItem(Item item) {
		if(itemStored.get(item.getType()) == null) {
			itemStored.put(item.getType(), 1);			
		} else {
			Integer stored = itemStored.get(item.getType());			
			itemStored.replace(item.getType(), stored+1);
		}
	}


}