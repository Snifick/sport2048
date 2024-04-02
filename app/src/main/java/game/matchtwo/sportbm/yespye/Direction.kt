package game.matchtwo.sportbm.yespye

sealed class Direction {
    data object TOP : Direction()
    data object RIGHT : Direction()
    data object BOT : Direction()
    data object LEFT : Direction()
    data object NONE : Direction()
}