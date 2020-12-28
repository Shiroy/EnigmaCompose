package machine

interface IRotor {
    fun init(position: Alphabet)
    fun substituteForward(letter: Alphabet): Alphabet
    fun substituteBackward(letter: Alphabet): Alphabet

    fun position(): Alphabet

    /**
     * Move the rotor forward. Return true if the rotor made a full rotation (every 27 `rotate()`)
     */
    fun rotate(): Boolean
}