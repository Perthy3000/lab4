package building;

import building.base.ElectricBuilding;
import building.base.ItemProducer;
import item.Item;
import item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import logic.GameState;
import ui.BuildingIcon;
import ui.ItemIcon;

public class Drill extends ElectricBuilding implements ItemProducer {

	private static final int HARVEST_INTERVAL = 4;
	private static final int ENERGY_CONSUMPTION = 5;
	private int currentCycle = 0;

	@Override
	public void render(StackPane target) {
		BuildingIcon icon = new BuildingIcon("file:res/drill.png");
		target.getChildren().add(icon);
		
		// TODO: fill this boolean
		boolean readyToProduceIron = canProduceItem();		// is this drill ready to produce iron?
		
		if(readyToProduceIron) {
			ItemIcon itemIcon = ItemType.IRON.toItemIcon();
			StackPane.setAlignment(itemIcon, Pos.TOP_CENTER);
			target.getChildren().add(itemIcon);
		}
	}

	@Override
	public void operate() {
		if(consumeElectricity() && currentCycle < HARVEST_INTERVAL) {
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
		currentCycle = 0;
		return Item.iron();
	}

	@Override
	protected boolean consumeElectricity() {
		if(GameState.instance.consumeElectricity(ENERGY_CONSUMPTION)) {
			return true;
		}
		return false;
	}
}
