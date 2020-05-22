package com.demo.gameoflife

import kotlin.random.Random

class RandomWrapper {
    fun getBooleanWithProbability(probability: Double): Boolean {
        return Random.nextDouble(1.0) >= probability
    }
}