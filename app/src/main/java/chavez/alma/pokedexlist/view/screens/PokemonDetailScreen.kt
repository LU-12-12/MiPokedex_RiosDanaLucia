package chavez.alma.pokedexlist.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import chavez.alma.pokedexlist.model.domain.Pokemon

@Composable
fun PokemonDetailScreen(innerPadding: PaddingValues, pokemon: Pokemon) {
    Column(modifier = Modifier.padding(innerPadding)) {
        Text(pokemon.name)
        Image(
            painter = painterResource(pokemon.image),
            contentDescription = "${pokemon.name} image"
        )
    }
}
