package machine

class RotorChain(val rotorList: List<IRotor>) {
    fun rotor(index: Int) = rotorList[index]
    fun substituteForward(letter: Alphabet): Alphabet {
        var currentLetter = letter

        for (rotor in rotorList) {
            currentLetter = rotor.substituteForward(currentLetter)
        }

        return currentLetter
    }

    fun substituteBackward(letter: Alphabet): Alphabet {
        var currentLetter = letter

        for(rotor in rotorList.reversed()) {
            currentLetter = rotor.substituteBackward(currentLetter)
        }

        return currentLetter
    }

    fun rotate() {
        var rotorToRotate = 0
        var rotateNext = false

        do {
            if(rotorToRotate >= rotorList.size) {
                break;
            }

            rotateNext = rotorList[rotorToRotate].rotate();
            rotorToRotate++
        } while (rotateNext)
    }
}
