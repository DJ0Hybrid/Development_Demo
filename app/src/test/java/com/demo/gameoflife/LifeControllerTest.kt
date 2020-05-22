package com.demo.gameoflife

import com.demo.gameoflife.Constant.Companion.GAME_SIZE_X
import com.demo.gameoflife.Constant.Companion.GAME_SIZE_Y
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LifeControllerTest {

    @Before
    fun setUp() {

    }

    @Test
    fun applyGameTick_KeepsCellAlive_IfNeighborCountIsTwo() {
        val xPos = 4
        val yPos = 3
        val grid = generateEmptyGrid()
        grid.get(xPos).set(yPos, true)
        grid.get(xPos - 1).set(yPos, true)
        grid.get(xPos + 1).set(yPos, true)

        val lifeController = LifeController(grid)

        lifeController.applyGameTick()
        assertTrue(lifeController.lifeGrid.get(xPos).get(yPos))
    }

    @Test
    fun applyGameTick_KeepsCellAlive_IfNeighborCountIsThree() {
        val xPos = 4
        val yPos = 3
        val grid = generateEmptyGrid()
        grid.get(xPos).set(yPos, true)
        grid.get(xPos - 1).set(yPos, true)
        grid.get(xPos + 1).set(yPos, true)
        grid.get(xPos).set(yPos + 1, true)

        val lifeController = LifeController(grid)

        lifeController.applyGameTick()
        assertTrue(lifeController.lifeGrid.get(xPos).get(yPos))
    }

    @Test
    fun applyGameTick_KillsAliveCell_IfNeighborCountIsBelowTwo() {
        val xPos = 4
        val yPos = 3
        val grid = generateEmptyGrid()
        grid.get(xPos).set(yPos, true)
        grid.get(xPos - 1).set(yPos, true)

        val lifeController = LifeController(grid)

        lifeController.applyGameTick()
        assertFalse(lifeController.lifeGrid.get(xPos).get(yPos))
    }

    @Test
    fun applyGameTick_KillsAliveCell_IfNeighborCountIsAboveThree() {
        val xPos = 4
        val yPos = 3
        val grid = generateEmptyGrid()
        grid.get(xPos).set(yPos, true)
        grid.get(xPos - 1).set(yPos, true)
        grid.get(xPos + 1).set(yPos, true)
        grid.get(xPos).set(yPos - 1, true)
        grid.get(xPos).set(yPos + 1, true)

        val lifeController = LifeController(grid)

        lifeController.applyGameTick()
        assertFalse(lifeController.lifeGrid.get(xPos).get(yPos))
    }

    @Test
    fun applyGameTick_RevivesCell_IfNeighborCountIsThree() {
        val xPos = 4
        val yPos = 3
        val grid = generateEmptyGrid()
        grid.get(xPos - 1).set(yPos, true)
        grid.get(xPos + 1).set(yPos, true)
        grid.get(xPos).set(yPos + 1, true)

        val lifeController = LifeController(grid)

        lifeController.applyGameTick()
        assertTrue(lifeController.lifeGrid.get(xPos).get(yPos))
    }

    @Test
    fun applyGameTick_HandlesZeroX_AndZeroY() {
        val xPos = 0
        val yPos = 0
        val grid = generateEmptyGrid()
        grid.get(xPos).set(yPos, true)
        grid.get(GAME_SIZE_X - 1).set(GAME_SIZE_Y - 1, true)
        grid.get(xPos + 1).set(yPos, true)

        val lifeController = LifeController(grid)

        lifeController.applyGameTick()
        assertTrue(lifeController.lifeGrid.get(xPos).get(yPos))
    }

    @Test
    fun applyGameTick_HandlesLimitX_AndLimitY() {
        val xPos = GAME_SIZE_X - 1
        val yPos = GAME_SIZE_Y - 1
        val grid = generateEmptyGrid()
        grid.get(xPos).set(yPos, true)
        grid.get(0).set(0, true)
        grid.get(xPos - 1).set(yPos - 1, true)

        val lifeController = LifeController(grid)

        lifeController.applyGameTick()
        assertTrue(lifeController.lifeGrid.get(xPos).get(yPos))
    }

    private fun generateEmptyGrid(): MutableList<MutableList<Boolean>> {
        return mutableListOf<MutableList<Boolean>>().apply {
            for (x in 0..GAME_SIZE_X) {
                add(mutableListOf<Boolean>().apply {
                    for (y in 0..GAME_SIZE_Y) {
                        add(false)
                    }
                })
            }
        }
    }
}