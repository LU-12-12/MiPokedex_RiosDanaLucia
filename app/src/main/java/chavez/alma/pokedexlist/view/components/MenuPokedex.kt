package chavez.alma.pokedexlist.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chavez.alma.pokedexlist.model.data.pokemonList
import chavez.alma.pokedexlist.model.domain.Pokemon


@Composable
fun MenuPokedex(pokemonList: List<Pokemon>, innerPadding: PaddingValues, onNavigateToDetail: (id: Int) -> Unit = {}){
    LazyColumn() {
        items(pokemonList){ pokemon ->
            PokemonRow(pokemon, onNavigateToDetail)

        }
    }
}

@Composable
fun FavoritesRow(favoritesList: List<Pokemon>, onNavigateToDetail: (id: Int) -> Unit = {}){
   LazyRow() {
        items(favoritesList){pokemon ->
            FavoritePokemon(pokemon, onNavigateToDetail)

        }
    }
}

@Composable
fun PokedexGrid(pokemonList: List<Pokemon>, onNavigateToDetail: (id: Int) -> Unit = {}){
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), contentPadding = PaddingValues(
            5.dp, 20.dp
        ), verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(pokemonList){
                pokemon ->
            PokemonCell(pokemon, onNavigateToDetail)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewMenuPokedex(){
    PokedexGrid(pokemonList)
}
