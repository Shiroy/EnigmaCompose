package machine

class Reflector(permutationMap: Map<Alphabet, Alphabet>): IReflector {
    val permutation: Map<Alphabet, Alphabet>

    init {
        val perm = mutableMapOf<Alphabet, Alphabet>()
        for((from, to) in permutationMap) {
            if (perm.containsKey(from) || perm.containsKey(to)) {
                throw IllegalArgumentException("Permutation defined twice")
            }
            perm[from] = to
            perm[to] = from
        }

        for (letter in Alphabet.values()) {
            if(!perm.containsKey(letter)) {
                throw IllegalArgumentException("Not all letter are permuted")
            }
        }

        permutation = perm
    }

    override fun permute(letter: Alphabet): Alphabet = permutation[letter]!!
}