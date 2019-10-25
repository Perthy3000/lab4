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
	public ConveyorBelt(Direction direction) {
	}

	
	@Override
	public void beforeCycle() {
	}
	
	/* getters & setters */
	public Item getItemOnBelt() {
	}
	
	public Direction getDirection() {
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
}
