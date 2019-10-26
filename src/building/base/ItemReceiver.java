package building.base;

import item.Item;
import item.ItemType;

public interface ItemReceiver {
	
	boolean canReceiveItem(ItemType oftype);
	
	void receiveItem(Item item);
}