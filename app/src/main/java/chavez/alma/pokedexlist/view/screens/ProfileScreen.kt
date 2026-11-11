package chavez.alma.pokedexlist.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import chavez.alma.pokedexlist.viewmodel.PokemonViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    pokemonViewModel: PokemonViewModel
) {
    val companionState by pokemonViewModel.companionState.collectAsState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Perfil de Entrenador", fontSize = 22.sp)

            Spacer(modifier = Modifier.height(32.dp))

            val companion = companionState.companionPokemon
            if (companion != null) {
                Image(
                    painter = painterResource(id = companion.image),
                    contentDescription = companion.name,
                    modifier = Modifier.size(150.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = companion.name, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Tiene ${companionState.daysTogether} días de ser tu compañero",
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                Text(
                    text = "Aún no has elegido un compañero",
                    fontSize = 16.sp
                )
            }
        }
    }
}
