package machine

import java.util.*

class Rotor(private val substitutionForward: List<Alphabet>): IRotor {
    private var rotationIndex = 0
    private var substitutionBackward: List<Alphabet> = calculateBackwardSubstitution()

    private fun calculateBackwardSubstitution() = Alphabet.values()
            .zip(substitutionForward)
            .sortedBy { (_, to) ->
                to
            }.map { (from, _) -> from }

    override fun substituteForward(letter: Alphabet): Alphabet {
        val index = Alphabet.values().indexOf(letter)
        return substitutionForward[index]
    }

    override fun substituteBackward(letter: Alphabet): Alphabet {
        val index = Alphabet.values().indexOf(letter)
        return substitutionBackward[index]
    }

    override fun position(): Alphabet = Alphabet.values()[rotationIndex]

    override fun rotate(): Boolean {
        rotationIndex = (rotationIndex+1) % substitutionForward.size
        Collections.rotate(substitutionForward, 1)
        substitutionBackward = calculateBackwardSubstitution()
        return rotationIndex == 0
    }

    override fun init(position: Alphabet) {
        val index = Alphabet.values().indexOf(position)
        rotationIndex = index
        Collections.rotate(substitutionForward, index)
        substitutionBackward = calculateBackwardSubstitution()

    }
}