package chavez.alma.pokedexlist.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import chavez.alma.pokedexlist.model.data.UserViewModel
import chavez.alma.pokedexlist.model.data.getPokemonByNumber
import chavez.alma.pokedexlist.view.screens.LoginScreen
import chavez.alma.pokedexlist.view.screens.MenuPokedexScreen
import chavez.alma.pokedexlist.view.screens.PokemonDetailScreen
import chavez.alma.pokedexlist.view.screens.PokemonHuntScreen
import kotlinx.serialization.Serializable

// Definición de las rutas seguras (Type-Safe Routes)
@Serializable
object Login

@Serializable
object PokemonList

@Serializable
data class PokemonDetail(val pokemon: Int)

@Serializable
object PokemonHunt

@Composable
fun MyApp(
    innerPadding: PaddingValues,
    userViewModel: UserViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(
                innerPadding = innerPadding,
                navigateToMenu = {
                    navController.navigate(PokemonList) {
                        // Limpia Login del historial para no regresar al presionar atrás
                        popUpTo<Login> { inclusive = true }
                    }
                },
                userViewModel = userViewModel
            )
        }

        composable<PokemonList> {
            MenuPokedexScreen(
                innerPadding = innerPadding,
                onNavigateToDetail = { id ->
                    navController.navigate(route = PokemonDetail(id))
                },
                onNavigateToHunt = {
                    navController.navigate(route = PokemonHunt)
                },
                onLogout = {
                    userViewModel.logout()
                    navController.navigate(Login) {
                        // Limpia el menú del historial para volver al Login limpiamente
                        popUpTo<PokemonList> { inclusive = true }
                    }
                }
            )
        }

        composable<PokemonDetail> { backStackEntry ->
            val detail: PokemonDetail = backStackEntry.toRoute()
            val pokemon = getPokemonByNumber(detail.pokemon)
            PokemonDetailScreen(innerPadding = innerPadding, pokemon = pokemon)
        }

        composable<PokemonHunt> {
            PokemonHuntScreen(innerPadding)
        }
    }
}