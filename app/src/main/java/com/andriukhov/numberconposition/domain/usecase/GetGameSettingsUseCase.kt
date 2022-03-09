package com.andriukhov.numberconposition.domain.usecase

import com.andriukhov.numberconposition.domain.entity.GameSettings
import com.andriukhov.numberconposition.domain.entity.Level
import com.andriukhov.numberconposition.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}