package building;

import building.base.ItemProducer;
import building.base.ItemReceiver;
import building.base.ElectricBuilding;
import item.Item;
import item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import logic.GameState;
import ui.BuildingIcon;

public class Anvil extends ElectricBuilding implements ItemProducer, ItemReceiver {
	private static final int CRAFT_DELAY = 5;
	private static final int ENERGY_CONSUMPTION = 15;
	private int currentCycle = 0;
	private boolean hasWood = false, hasIronPlate = false;
	
	@Override
	public void render(StackPane target) {
		BuildingIcon icon = new BuildingIcon("file:res/anvil.png");
		target.getChildren().add(icon);
		
		
		HBox status = new HBox();
		StackPane.setAlignment(status, Pos.TOP_CENTER);
		
		// TODO: fill this booleans
		boolean ready = canProduceItem();			// is this anvil ready (finished crafting iron sword)?
		boolean hasWood = this.hasWood;		// does this anvil has wood on it?
		boolean hasIronPlate = this.hasIronPlate;	// does this anvil has an iron plate on it?
		
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
	
	@Override
	public void operate() {
		if(consumeElectricity() && currentCycle < CRAFT_DELAY) {
			currentCycle++;
		}
	}

	@Override
	public boolean canReceiveItem(ItemType oftype) {
		switch (oftype) {
		case WOOD:
			if(!hasWood) {
				return true;
			}
		case IRON_PLATE:
			if(!hasIronPlate) {
				return true;
			}
		default:
			return false;
		}
	}

	@Override
	public void receiveItem(Item item) {
		ItemType type = item.getType();
		switch (type) {
		case WOOD:
			hasWood = true;
			break;
		case IRON_PLATE:
			hasIronPlate = true;
			break;
		}
	}

	@Override
	public boolean canProduceItem() {
		if(currentCycle == CRAFT_DELAY) {
			return true;
		}
		return false;
	}

	@Override
	public Item getProducedItem() {
		currentCycle = 0;
		hasWood = false;
		hasIronPlate = false;
		return Item.ironSword();
	}

	@Override
	protected boolean consumeElectricity() {
		return GameState.instance.consumeElectricity(ENERGY_CONSUMPTION);
	}
}
