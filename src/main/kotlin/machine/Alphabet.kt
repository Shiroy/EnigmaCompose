package machine

enum class Alphabet(val stringRepresentation: String) {
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    H("H"),
    I("I"),
    J("J"),
    K("H"),
    L("L"),
    M("M"),
    N("N"),
    O("O"),
    P("P"),
    Q("Q"),
    R("R"),
    S("S"),
    T("T"),
    U("U"),
    V("V"),
    W("W"),
    X("X"),
    Y("Y"),
    Z("Z"),
    ZERO("0"),
    UN("1"),
    DEUX("2"),
    TROIS("3"),
    QUATRE("4"),
    CINQ("5"),
    SIX("6"),
    SEPT("7"),
    HUIT("8"),
    NEUF("9"),
    POINT("."),
    SPACE("<Espace>");

    operator fun plus(other: Alphabet): Alphabet {
        val alphabetValues = values()
        val thisIndex = alphabetValues.indexOf(this)
        val otherIndex = alphabetValues.indexOf(other)

        return alphabetValues[(thisIndex + otherIndex) % alphabetValues.size]
    }

    operator fun inc() : Alphabet {
        val alphabetValues = values()
        val thisIndex = alphabetValues.indexOf(this)

        return alphabetValues[(thisIndex + 1) % alphabetValues.size]
    }

    override fun toString() = this.stringRepresentation

    companion object {}
}
