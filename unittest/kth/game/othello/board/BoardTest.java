package kth.game.othello.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kth.game.othello.MockingBase;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest extends MockingBase {

	@Test
	public void testBoardCreation() {
		List<Node> boardNodes = new ArrayList<>();
		List<Node> sortedNodes = new ArrayList<>();

		for (int node = 0; node < 10; node++) {
			boardNodes.add(createMockedNode(9 - node, 9 - node));
			sortedNodes.add(createMockedNode(node, node));
		}
		Board board = new BasicBoard(boardNodes);
		boardNodes = board.getNodes();
		for (int node = 0; node < 10; node++) {
			Node boardNode = boardNodes.get(node);
			Node sortedNode = sortedNodes.get(node);
			assertEquals(sortedNode.getXCoordinate(), boardNode.getXCoordinate());
			assertEquals(sortedNode.getYCoordinate(), boardNode.getYCoordinate());
			assertEquals(sortedNode.getId(), boardNode.getId());
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetNodeReturnsNullWhenFaultyCoordinates() {
		Board board = new BasicBoard(new ArrayList<Node>());
		board.getNode(0, 0);
		Assert.fail();
	}

	@Test
	public void testGetNode() {
		Node xy22 = createMockedNode(2, 2, "22", "p1");
		Node xy33 = createMockedNode(3, 3, "33", "p2");
		Node xy44 = createMockedNode(4, 4, "44", "p3");
		Node xy70 = createMockedNode(7, 0, "70", "p4");

		Board board = new BasicBoard(Arrays.asList(xy22, xy33, xy44, xy70));
		assertEquals("22", board.getNode(2, 2).getId());
		assertEquals("33", board.getNode(3, 3).getId());
		assertEquals("44", board.getNode(4, 4).getId());
		assertEquals("70", board.getNode(7, 0).getId());

		assertEquals("p1", board.getNode(2, 2).getOccupantPlayerId());
		assertEquals("p2", board.getNode(3, 3).getOccupantPlayerId());
		assertEquals("p3", board.getNode(4, 4).getOccupantPlayerId());
		assertEquals("p4", board.getNode(7, 0).getOccupantPlayerId());

		assertNull(board.getNode(3, 4));
		assertNull(board.getNode(1, 7));
		assertNull(board.getNode(3123, 23));
		assertNull(board.getNode(0, 0));
	}
}
