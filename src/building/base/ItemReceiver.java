package building.base;

import item.Item;
import item.ItemType;

public interface ItemReceiver {
	
	boolean canRecieveItem(ItemType oftype);
	
	void recieveItem(Item item);
}