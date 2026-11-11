package chavez.alma.pokedexlist.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chavez.alma.pokedexlist.viewmodel.PokemonViewModel

@Composable
fun PokemonDetailScreen(
    pokemonNumber: String,
    pokemonViewModel: PokemonViewModel,
    onBack: () -> Unit
) {
    LaunchedEffect(pokemonNumber) {
        pokemonViewModel.loadPokemonDetail(pokemonNumber)
    }

    val pokemon by pokemonViewModel.selectedPokemonDetail.collectAsState()
    val companionState by pokemonViewModel.companionState.collectAsState()

    val isCompanion = companionState.companionPokemon?.number?.toString() == pokemonNumber

    Scaffold { paddingValues ->
        pokemon?.let { currentPokemon ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = currentPokemon.image),
                    contentDescription = currentPokemon.name,
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = currentPokemon.name, fontSize = 24.sp)
                Text(text = "Número: #${currentPokemon.number}", fontSize = 16.sp)
                Text(text = "Tipo: ${currentPokemon.type}", fontSize = 16.sp)

                Spacer(modifier = Modifier.height(24.dp))

                if (isCompanion) {
                    Text(
                        text = "⭐ Es tu compañero",
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.titleMedium
                    )
                } else {
                    Button(
                        onClick = { pokemonViewModel.selectCompanion(currentPokemon.number.toString()) }
                    ) {
                        Text(text = "Seleccionar como compañero")
                    }
                }
            }
        } ?: Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
