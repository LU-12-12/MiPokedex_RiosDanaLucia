package chavez.alma.pokedexlist.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import chavez.alma.pokedexlist.model.data.pokemonList
import chavez.alma.pokedexlist.model.domain.Pokemon
import chavez.alma.pokedexlist.model.preferences.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

data class CompanionState(
    val companionPokemon: Pokemon? = null,
    val daysTogether: Long = 0
)

class PokemonViewModel(application: Application) : AndroidViewModel(application) {

    private val userPreferences = UserPreferences(application)

    // Detalle del Pokémon actual expuesto para la pantalla de detalle
    private val _selectedPokemonDetail = MutableStateFlow<Pokemon?>(null)
    val selectedPokemonDetail: StateFlow<Pokemon?> = _selectedPokemonDetail.asStateFlow()

    // Flow con la información combinada del compañero seleccionado
    val companionState: StateFlow<CompanionState> = combine(
        userPreferences.companionPokemonNumber,
        userPreferences.companionSinceTimestamp
    ) { number, timestamp ->
        if (number != null) {
            val pokemon = pokemonList.find { it.number.toString() == number }
            val days = if (timestamp != null) {
                val diffInMillis = System.currentTimeMillis() - timestamp
                TimeUnit.MILLISECONDS.toDays(diffInMillis)
            } else 0L
            CompanionState(companionPokemon = pokemon, daysTogether = days)
        } else {
            CompanionState()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CompanionState()
    )

    // Caza de Pokémon
    var wildPokemon by mutableStateOf<Pokemon?>(null)
        private set

    var gonePokemon by mutableStateOf(false)
        private set

    var isPokemonCaptured by mutableStateOf(false)
        private set

    val capturedPokemon = mutableStateListOf<Pokemon>()

    fun searchPokemon() {
        gonePokemon = false
        isPokemonCaptured = false
        wildPokemon = pokemonList.random()
    }

    fun capturePokemon() {
        val current = wildPokemon ?: return
        val success = java.util.Random().nextBoolean()
        if (success) {
            isPokemonCaptured = true
            gonePokemon = false
            if (!capturedPokemon.contains(current)) {
                capturedPokemon.add(current)
            }
        } else {
            isPokemonCaptured = false
            gonePokemon = true
        }
        wildPokemon = null
    }

    fun loadPokemonDetail(pokemonNumber: String) {
        val found = pokemonList.find { it.number.toString() == pokemonNumber }
        _selectedPokemonDetail.value = found
    }

    fun selectCompanion(pokemonNumber: String) {
        viewModelScope.launch {
            userPreferences.saveCompanion(pokemonNumber)
            Toast.makeText(
                getApplication(),
                "Compañero seleccionado",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
