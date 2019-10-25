package building.base;

import item.Item;

public interface ItemProducer {
	
	boolean canProduceItem();
	
	Item getProducedItem();
	
}
