package chavez.alma.pokedexlist.navigation

import kotlinx.serialization.Serializable

@Serializable
object PokemonList

@Serializable
object  login


@Serializable
data class PokemonDetail(val pokemon: Int)

@Serializable
object PokemonHunt