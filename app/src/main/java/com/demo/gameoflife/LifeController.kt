package com.demo.gameoflife

import com.demo.gameoflife.Constant.Companion.GAME_SIZE_X
import com.demo.gameoflife.Constant.Companion.GAME_SIZE_Y
import com.demo.gameoflife.Constant.Companion.INITIAL_SEED_PROBABILITY
import kotlin.random.Random

class LifeController {
    lateinit var lifeGrid: List<List<Boolean>>
        private set

    constructor() {
        generateInitialLifeGrid()
    }

    constructor(lifeGrid : List<List<Boolean>>) {
        this.lifeGrid = lifeGrid
    }

    private fun generateInitialLifeGrid() {
        lifeGrid = List(GAME_SIZE_X) {
            List(GAME_SIZE_Y) {
                Random.nextDouble(1.0) >= INITIAL_SEED_PROBABILITY
            }
        }
    }

    fun applyGameTick() {
        val tempNewGrid = mutableListOf<MutableList<Boolean>>()

        for (x in 0 until GAME_SIZE_X) {
            tempNewGrid.add(mutableListOf())

            for (y in 0 until GAME_SIZE_Y) {
                tempNewGrid.get(x).add(false)

                if (lifeGrid.get(x).get(y)) {
                    when (getLivingNeighborsCount(x, y)) {
                        2, 3 -> tempNewGrid.get(x).set(y, true)
                    }
                } else if (getLivingNeighborsCount(x, y) == 3) {
                    // If you have 3 living neighbors, then a dead cell becomes living.
                    tempNewGrid.get(x).set(y, true)
                }
            }
        }

        lifeGrid = tempNewGrid
    }

    fun getLivingNeighborsCount(x: Int, y: Int): Int {
        var count = 0
        for (xOffset in -1..1) {
            var xOffPos = x + xOffset
            if (xOffPos < 0) xOffPos = GAME_SIZE_X - 1
            else if (xOffPos >= GAME_SIZE_X) xOffPos = 0

            for (yOffset in -1..1) {
                var yOffPos = y + yOffset
                if (yOffPos < 0) yOffPos = GAME_SIZE_Y - 1
                else if (yOffPos >= GAME_SIZE_Y) yOffPos = 0

                if (lifeGrid.get(xOffPos).get(yOffPos) && !(xOffset == 0 && yOffset == 0)) count++
            }
        }
        return count
    }
}