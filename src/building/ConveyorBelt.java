package building;

import building.base.Building;
import building.base.ItemProducer;
import building.base.ItemReceiver;
import item.Item;
import item.ItemType;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import logic.Direction;
import logic.Field;
import ui.BuildingIcon;
import ui.ItemIcon;

public class ConveyorBelt extends Building implements ItemReceiver, ItemProducer {	
	private Item onBelt = null;
	private Direction direction;
	private boolean operated = false;
	
	public ConveyorBelt(Direction direction) {
		this.direction = direction;
	}

	
	@Override
	public void beforeCycle() {
		operated = false;
	}
	
	/* getters & setters */
	public Item getItemOnBelt() {
		return onBelt;
	}
	
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void render(StackPane target) {
		
		BuildingIcon icon = new BuildingIcon("file:res/belt.png");
		
		switch(this.getDirection()) {
		case RIGHT:
			icon.setRotate(90);
			break;
		case LEFT:
			icon.setRotate(270);
			break;
		case DOWN:
			icon.setRotate(180);
			break;
		default:
			break;
		}
		
		target.getChildren().add(icon);
		
		Item itemOnBelt = this.getItemOnBelt();
		
		if(itemOnBelt != null) {
			ItemIcon itemIcon = itemOnBelt.getType().toItemIcon();
			StackPane.setAlignment(itemIcon, Pos.TOP_CENTER);
			target.getChildren().add(itemIcon);
		}
	}

	@Override
	public void operate() {
		Building front = Field.instance.getBuildingInFront(direction);
		Building back = Field.instance.getBuildingInBack(direction);
		if(canProduceItem()) {
			if(front instanceof ItemReceiver) {
				if(((ItemReceiver) front).canReceiveItem(getItemOnBelt().getType())) {
					((ItemReceiver) front).receiveItem(getProducedItem());
				}
			}
		}
		if(back instanceof ItemProducer) {
			if(((ItemProducer) back).canProduceItem()) {
				if(onBelt == null && !operated) {
					Item item = ((ItemProducer) back).getProducedItem();
					if(canReceiveItem(item.getType())) {
						receiveItem(item);
					}					
				}
			}
		}
	}

	@Override
	public boolean canProduceItem() {
		if(onBelt != null && !operated) {
			return true;
		}
		return false;
	}


	@Override
	public Item getProducedItem() {
		operated = true;
		Item sentOut = onBelt;
		onBelt = null;
		return sentOut;
	}


	@Override
	public boolean canReceiveItem(ItemType oftype) {
		if(onBelt == null && !operated && oftype != null) {
			return true;
		}
		return false;
	}


	@Override
	public void receiveItem(Item item) {
		operated = true;
		onBelt = item;
	}


}
