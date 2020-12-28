package machine

class Enigma(rotor1: IRotor, rotor2: IRotor, rotor3: IRotor, val reflector: IReflector) {
    val rotorChain = RotorChain(listOf(rotor1, rotor2, rotor3))

    fun encrypt(letter: Alphabet): Alphabet {
        print("${letter} -> ")
        val step1 = rotorChain.substituteForward(letter)
        print("${step1} -> ")
        val step2 = reflector.permute(step1)
        print("${step2} -> ")
        val step3 = rotorChain.substituteBackward(step2)
        println("${step3}")

        return step3
    }

    fun rotate() = rotorChain.rotate()

    val rotor1: IRotor get() = rotorChain.rotor(0)
    val rotor2: IRotor get() = rotorChain.rotor(1)
    val rotor3: IRotor get() = rotorChain.rotor(2)
}