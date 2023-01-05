package co.frog.pokedex.data.structures

abstract class CustomException : Throwable() {
}

class InvalidDataException : CustomException() {
}
