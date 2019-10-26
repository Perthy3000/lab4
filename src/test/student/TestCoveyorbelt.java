package test.student;

import static org.junit.jupiter.api.Assertions.*;
import test.base.GameTest;
import org.junit.jupiter.api.Test;

import building.Sawmill;
import building.ConveyorBelt;
import item.Item;
import logic.Direction;
import logic.Field;
import logic.GameState;

class TestCoveyorbelt extends GameTest {

	@Test
	void testConveyorBeltMoveOneItemInCycle() {
		ConveyorBelt cb1 = new ConveyorBelt(Direction.UP);
		ConveyorBelt cb2 = new ConveyorBelt(Direction.RIGHT);
		ConveyorBelt cb3 = new ConveyorBelt(Direction.DOWN);
		ConveyorBelt cb4 = new ConveyorBelt(Direction.LEFT);
		

		Field.instance.at(0, 1).placeBuildingOnCell(cb1);
		Field.instance.at(0, 0).placeBuildingOnCell(cb2);
		Field.instance.at(1, 0).placeBuildingOnCell(cb3);
		Field.instance.at(1, 1).placeBuildingOnCell(cb4);
		
		cb1.receiveItem(Item.wood());
				
		assertNotNull(cb1.getItemOnBelt());
		GameState.instance.update();
		assertNotNull(cb2.getItemOnBelt());
		GameState.instance.update();
		assertNotNull(cb3.getItemOnBelt());
		GameState.instance.update();
		assertNotNull(cb4.getItemOnBelt());
	}
}
