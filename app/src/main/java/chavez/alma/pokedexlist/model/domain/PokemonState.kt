package chavez.alma.pokedexlist.model.domain

data class PokemonState(
    val team: List<Pokemon> = emptyList(),

    val lastCaptured: Pokemon?= null
)
