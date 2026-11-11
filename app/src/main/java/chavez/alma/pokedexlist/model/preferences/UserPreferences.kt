package chavez.alma.pokedexlist.model.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class UserPreferences(private val context: Context) {

    companion object {
        const val ARCHIVO = "user_prefs"
        const val USER = "user_email"
        const val USERNAME = "user_name"

        val COMPANION_ID_KEY = stringPreferencesKey("companion_pokemon_number")
        val COMPANION_SINCE_KEY = longPreferencesKey("companion_since_timestamp")
    }

    val companionPokemonNumber: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[COMPANION_ID_KEY]
    }

    val companionSinceTimestamp: Flow<Long?> = context.dataStore.data.map { preferences ->
        preferences[COMPANION_SINCE_KEY]
    }

    suspend fun saveCompanion(pokemonNumber: String, timestamp: Long = System.currentTimeMillis()) {
        context.dataStore.edit { preferences ->
            preferences[COMPANION_ID_KEY] = pokemonNumber
            preferences[COMPANION_SINCE_KEY] = timestamp
        }
    }
}
