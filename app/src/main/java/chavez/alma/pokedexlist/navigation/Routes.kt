package chavez.alma.pokedexlist.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login_screen")
    object Pokedex : Routes("pokedex_screen")
    object Profile : Routes("profile_screen")
    object Hunt : Routes("hunt_screen")
    object Detail : Routes("detail_screen/{pokemonNumber}") {
        fun createRoute(pokemonNumber: String) = "detail_screen/$pokemonNumber"
        fun createRoute(pokemonNumber: Int) = "detail_screen/$pokemonNumber"
    }
}
