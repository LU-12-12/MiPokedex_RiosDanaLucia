package chavez.alma.pokedexlist.view.screens


import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import chavez.alma.pokedexlist.ui.theme.Green
import chavez.alma.pokedexlist.ui.theme.Pink40
import chavez.alma.pokedexlist.ui.theme.Pink80
import chavez.alma.pokedexlist.ui.theme.Psych
import chavez.alma.pokedexlist.ui.theme.Purple40
import chavez.alma.pokedexlist.ui.theme.Typography
import chavez.alma.pokedexlist.view.components.PokemonRow
import chavez.alma.pokedexlist.viewmodel.PokemonViewModel

@Composable
fun PokemonHuntScreen(innerPadding: PaddingValues, viewModel: PokemonViewModel = viewModel()) {
    Column(Modifier.fillMaxWidth().padding(innerPadding),horizontalAlignment= Alignment.CenterHorizontally) {
        Button({viewModel.searchPokemon()}){
            Text("Buscar pokemon en la hierva")
        }

        Spacer(Modifier.size(18.dp))

        viewModel.wildPokemon?.let {

            pokemon ->
            Image(painterResource(pokemon.image), "${pokemon.name} image")
            Text("Apareció un ${pokemon.name} salvaje", style = Typography.labelLarge, color = Purple40)
            Spacer(Modifier.size(15.dp))
            Button(onClick = {viewModel.capturePokemon()}) {
                Text("Capturar a ${pokemon.name}")
            }
        }

        if (viewModel.gonePokemon){
            Text("El pokemon se ha escapado :(",
            style = Typography.labelLarge,
            color = Color.Magenta)
        }

        if (viewModel.isPokemonCaptured){
            Text("Has atrapado al pokemon :)",
                style = Typography.labelLarge,
                color = Green
            )
        }

        Spacer(Modifier.size(20.dp))

        Text("Pokemones capturados",
            style = Typography.titleLarge,
            color = Psych)


        LazyColumn() {
            items(viewModel.capturedPokemon){
                pokemon->
                PokemonRow(pokemon)

            }
        }






    }
}


@Preview(showBackground = true)
@Composable
fun PokemonHuntPreview() {
    PokemonHuntScreen(PaddingValues(15.dp))
}