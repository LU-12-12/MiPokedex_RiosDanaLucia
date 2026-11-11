package chavez.alma.pokedexlist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import chavez.alma.pokedexlist.model.data.pokemonList
import chavez.alma.pokedexlist.model.domain.Pokemon

class PokemonViewModel: ViewModel() {

    var wildPokemon by mutableStateOf<Pokemon?>(null)
        private set

    var capturedPokemon by mutableStateOf(listOf<Pokemon>())
        private set

    var gonePokemon by mutableStateOf(false)

    var isPokemonCaptured by mutableStateOf(false)


    fun searchPokemon(){
        wildPokemon = pokemonList.random()
        isPokemonCaptured =false
    }


    fun capturePokemon(){
    wildPokemon?.let{
        var isCaptured = (1..2).random()
        if (isCaptured==1){
            capturedPokemon = capturedPokemon+it
            gonePokemon = false
            wildPokemon = null
            isPokemonCaptured= true
        } else{
            gonePokemon= true
            wildPokemon= null
            isPokemonCaptured= false
        }
    }
    }


}