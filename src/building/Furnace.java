package building;

import building.base.ItemProducer;
import building.base.ItemReceiver;
import building.base.ElectricBuilding;
import item.Item;
import item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import logic.GameState;
import ui.BuildingIcon;
import ui.ItemIcon;

public class Furnace extends ElectricBuilding implements ItemProducer, ItemReceiver {

	private static final int CRAFT_DELAY = 3;
	private static final int ENERGY_CONSUMPTION = 10;
	private int currentCycle = 0;
	private boolean hasIron = false;

	@Override
	public void render(StackPane target) {
		BuildingIcon icon = new BuildingIcon("file:res/furnace.png");
		target.getChildren().add(icon);
		
		// TODO: fill these booleans
		boolean readyToProduceIronPlate = canProduceItem();		// is this furnace ready to produce an iron plate?
		boolean hasIron = this.hasIron;						// does this furnace has iron in it?
		
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

	@Override
	public void operate() {
		if(currentCycle < CRAFT_DELAY && hasIron && consumeElectricity()) {
			currentCycle++;
		}
	}
	
	@Override
	public boolean canReceiveItem(ItemType oftype) {
		if(!hasIron && oftype == ItemType.IRON) {
			return true;
		}
		return false;
	}

	@Override
	public void receiveItem(Item item) {
		hasIron = true;
	}

	@Override
	public boolean canProduceItem() {
		if(currentCycle == CRAFT_DELAY && hasIron) {
			return true;
		}
		return false;
	}

	@Override
	public Item getProducedItem() {
		hasIron = false;
		currentCycle = 0;
		return Item.ironPlate();
	}

	@Override
	protected boolean consumeElectricity() {
		if(GameState.instance.consumeElectricity(ENERGY_CONSUMPTION)) {
			return true;
		}
		return false;
	}


}
