package co.frog.pokedex.data.structures

// https://medium.com/swlh/kotlin-sealed-class-for-success-and-error-handling-d3054bef0d4e
sealed class ResultOf<out T> {
    data class Loading(val string: String = ""): ResultOf<Nothing>()
    data class Success<out R>(val value: R): ResultOf<R>()
    data class Failure(
        val message: String?,
        val throwable: Throwable?
    ): ResultOf<Nothing>()
}
