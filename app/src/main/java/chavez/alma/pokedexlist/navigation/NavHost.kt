package chavez.alma.pokedexlist.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import chavez.alma.pokedexlist.view.screens.LoginScreen
import chavez.alma.pokedexlist.view.screens.MenuPokedexScreen
import chavez.alma.pokedexlist.view.screens.PokemonDetailScreen
import chavez.alma.pokedexlist.view.screens.PokemonHuntScreen
import chavez.alma.pokedexlist.view.screens.ProfileScreen
import chavez.alma.pokedexlist.viewmodel.PokemonViewModel
import chavez.alma.pokedexlist.viewmodel.UserViewModel

@Composable
fun AppNavigation(
    innerPadding: PaddingValues,
    userViewModel: UserViewModel,
    pokemonViewModel: PokemonViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Routes.Login.route) {
            LoginScreen(
                userViewModel = userViewModel,
                onLoginSuccess = {
                    navController.navigate(Routes.Pokedex.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.Pokedex.route) {
            MenuPokedexScreen(
                innerPadding = PaddingValues(),
                onNavigateToDetail = { pokemonNumber ->
                    navController.navigate(Routes.Detail.createRoute(pokemonNumber))
                },
                onNavigateToHunt = {
                    navController.navigate(Routes.Hunt.route)
                }
            )
        }
        composable(
            route = Routes.Detail.route,
            arguments = listOf(navArgument("pokemonNumber") { type = NavType.StringType })
        ) { backStackEntry ->
            val pokemonNumber = backStackEntry.arguments?.getString("pokemonNumber") ?: ""
            PokemonDetailScreen(
                pokemonNumber = pokemonNumber,
                pokemonViewModel = pokemonViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.Profile.route) {
            ProfileScreen(
                navController = navController,
                pokemonViewModel = pokemonViewModel
            )
        }
        composable(Routes.Hunt.route) {
            PokemonHuntScreen(
                innerPadding = PaddingValues(),
                viewModel = pokemonViewModel
            )
        }
    }
}
