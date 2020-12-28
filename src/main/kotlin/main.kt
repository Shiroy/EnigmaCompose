import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import machine.*

fun createMachine(): Enigma {
    val rotor1 = Rotor(rotor1Sub)
    rotor1.init(Alphabet.T)
    val rotor2 = Rotor(rotor2Sub)
    rotor2.init(Alphabet.K)
    val rotor3 = Rotor(rotor3Sub)
    rotor3.init(Alphabet.T)

    return Enigma(
        rotor1,
        rotor2,
        rotor3,
        Reflector(permutation)
    )
}


val machine = createMachine()

fun Alphabet.Companion.fromKeyEvent(key: Key): Alphabet? = when (key) {
    Key.A -> Alphabet.A
    Key.B -> Alphabet.B
    Key.C -> Alphabet.C
    Key.D -> Alphabet.D
    Key.E -> Alphabet.E
    Key.F -> Alphabet.F
    Key.G -> Alphabet.G
    Key.H -> Alphabet.H
    Key.I -> Alphabet.I
    Key.J -> Alphabet.J
    Key.K -> Alphabet.K
    Key.L -> Alphabet.L
    Key.M -> Alphabet.M
    Key.N -> Alphabet.N
    Key.O -> Alphabet.O
    Key.P -> Alphabet.P
    Key.Q -> Alphabet.Q
    Key.R -> Alphabet.R
    Key.S -> Alphabet.S
    Key.T -> Alphabet.T
    Key.U -> Alphabet.U
    Key.V -> Alphabet.V
    Key.W -> Alphabet.W
    Key.X -> Alphabet.X
    Key.Y -> Alphabet.Y
    Key.Z -> Alphabet.Z
    Key.Spacebar -> Alphabet.SPACE
    Key.Period -> Alphabet.POINT
    Key.Number0 -> Alphabet.ZERO
    Key.Number1 -> Alphabet.UN
    Key.Number2 -> Alphabet.DEUX
    Key.Number3 -> Alphabet.TROIS
    Key.Number4 -> Alphabet.QUATRE
    Key.Number5 -> Alphabet.CINQ
    Key.Number6 -> Alphabet.SIX
    Key.Number7 -> Alphabet.SEPT
    Key.Number8 -> Alphabet.HUIT
    Key.Number9 -> Alphabet.NEUF
    else -> null
}

fun main() = Window(title = "Enigma") {
    val (letter, setLetter) = remember { mutableStateOf<String?>(null) }
    val (rotor1, setRotor1) = remember { mutableStateOf(machine.rotor1.position().toString()) }
    val (rotor2, setRotor2) = remember { mutableStateOf(machine.rotor2.position().toString()) }
    val (rotor3, setRotor3) = remember { mutableStateOf(machine.rotor3.position().toString()) }

    fun onKeyDown(key: Key) {
        val enigmaLetter = Alphabet.fromKeyEvent(key)
        setLetter(
            if (enigmaLetter != null) {
                machine.encrypt(enigmaLetter).toString()
            } else {
                " "
            }
        )
    }

    fun onKeyUp() {
        setLetter(null)
        machine.rotate()
        setRotor1(machine.rotor1.position().toString())
        setRotor2(machine.rotor2.position().toString())
        setRotor3(machine.rotor3.position().toString())
    }

    MaterialTheme {
        Column {
            Row {
                Text(letter ?: " ", fontSize = 10.em, textAlign = TextAlign.Center)
            }
            Row {
                TextField("", onValueChange = {}, Modifier.onKeyEvent { keyEvent ->
                    when (keyEvent.type) {
                        KeyEventType.KeyDown -> {
                            onKeyDown(keyEvent.key)
                            false
                        }
                        KeyEventType.KeyUp -> {
                            onKeyUp()
                            false
                        }
                        else -> {
                            false
                        }
                    }
                })
            }
            Row {
                Text(rotor1)
                Spacer(Modifier.width(10.dp))
                Text(rotor2)
                Spacer(Modifier.width(10.dp))
                Text(rotor3)
            }

//            Row {
//                AlphabetSelector()
//                Spacer(Modifier.width(10.dp))
//                AlphabetSelector()
//                Spacer(Modifier.width(10.dp))
//                AlphabetSelector()
//            }
        }
    }
}

//@Composable
//fun AlphabetSelector() {
//    val (showMenu, setShowMenu) = remember { mutableStateOf(false) }
//    val (selectedIndex, setSelectedIndex) = remember { mutableStateOf(0) }
//
//    DropdownMenu(toggle = {
//        Button(onClick = {setShowMenu(true)}) {
//            Text(Alphabet.values()[selectedIndex].toString())
//        }
//    }, expanded = showMenu, onDismissRequest = {
//        setShowMenu(false)
//    },
//    ) {
//        Alphabet.values().forEachIndexed { index, letter ->
//            DropdownMenuItem(onClick = {
//                setSelectedIndex(index)
//                setShowMenu(false)
//            }) {
//                Text(letter.toString())
//            }
//        }
//    }
//}
