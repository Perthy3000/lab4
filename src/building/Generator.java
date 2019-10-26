package building;

import building.base.Building;
import building.base.ItemReceiver;
import item.Item;
import item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import logic.GameState;
import ui.BuildingIcon;
import ui.ItemIcon;

public class Generator extends Building implements ItemReceiver {
	
	private static final int GENERATION_DELAY = 3;
	private static final int GENERATED_ELECTRICITY = 5;
	private int currentCycle = 0;
	private boolean hasWood = false;

	@Override
	public void render(StackPane target) {
		BuildingIcon icon = new BuildingIcon("file:res/generator.png");
		target.getChildren().add(icon);
		
		// TODO: fill this boolean
		boolean hasWood = this.hasWood;		// does this generator has wood in it?
		
		if(hasWood) {
			ItemIcon itemIcon = ItemType.WOOD.toItemIcon();
			StackPane.setAlignment(itemIcon, Pos.TOP_CENTER);
			target.getChildren().add(itemIcon);
		}
	}
	
	@Override
	public void operate() {
		if(hasWood) {
			currentCycle++;
		}
		generateElectricity();
	}

	@Override
	public boolean canReceiveItem(ItemType oftype) {
		if(!hasWood && oftype == ItemType.WOOD) {
			return true;
		}
		return false;
	}

	@Override
	public void receiveItem(Item item) {
		hasWood = true;
	}
	
	private void generateElectricity() {
		if(currentCycle == GENERATION_DELAY) {
			GameState.instance.generateElectricity(GENERATED_ELECTRICITY);
			currentCycle = 0;
			hasWood = false;
		}
	}

}
