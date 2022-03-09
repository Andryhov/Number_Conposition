package com.andriukhov.numberconposition.domain.repository

import com.andriukhov.numberconposition.domain.entity.GameSettings
import com.andriukhov.numberconposition.domain.entity.Level
import com.andriukhov.numberconposition.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings
}