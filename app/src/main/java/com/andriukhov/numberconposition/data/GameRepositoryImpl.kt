package com.andriukhov.numberconposition.data

import com.andriukhov.numberconposition.domain.entity.GameSettings
import com.andriukhov.numberconposition.domain.entity.Level
import com.andriukhov.numberconposition.domain.entity.Question
import com.andriukhov.numberconposition.domain.repository.GameRepository
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val listOfOptions = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        listOfOptions.add(rightAnswer)

        while (listOfOptions.size != countOfOptions) {
            listOfOptions.add(Random.nextInt(MIN_ANSWER_VALUE, maxSumValue))
        }

        return Question(sum, visibleNumber, listOfOptions.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> GameSettings(10, 3, 50, 8)
            Level.EASY -> GameSettings(10, 10, 70, 60)
            Level.NORMAL -> GameSettings(20, 20, 80, 40)
            Level.HARD -> GameSettings(30, 30, 90, 40)
        }
    }
}