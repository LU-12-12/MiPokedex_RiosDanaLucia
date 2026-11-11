package chavez.alma.pokedexlist.utilities

import androidx.compose.ui.graphics.Color
import chavez.alma.pokedexlist.ui.theme.Bug
import chavez.alma.pokedexlist.ui.theme.DarkGray
import chavez.alma.pokedexlist.ui.theme.Electric
import chavez.alma.pokedexlist.ui.theme.Fairy
import chavez.alma.pokedexlist.ui.theme.Fight
import chavez.alma.pokedexlist.ui.theme.Fire
import chavez.alma.pokedexlist.ui.theme.Flying
import chavez.alma.pokedexlist.ui.theme.Ghost
import chavez.alma.pokedexlist.ui.theme.Grass
import chavez.alma.pokedexlist.ui.theme.Ground
import chavez.alma.pokedexlist.ui.theme.Normal
import chavez.alma.pokedexlist.ui.theme.OffWhite
import chavez.alma.pokedexlist.ui.theme.Poison
import chavez.alma.pokedexlist.ui.theme.Psych
import chavez.alma.pokedexlist.ui.theme.Rock
import chavez.alma.pokedexlist.ui.theme.Water

fun getColorByType(type: String): Pair<Color,Color>{
    var color: Color
    var dark = true
    when{
        type.lowercase().contains("normal") -> color = Normal;
        type.lowercase().contains("electric") -> {
            color = Electric
            dark= false
        }
        type.lowercase().contains("water") -> {
            color = Water
        }
        type.lowercase().contains("fire") -> color = Fire
        type.lowercase().contains("fairy") -> {
            color = Fairy
            dark= false
        }
        type.lowercase().contains("grass") -> {
            color = Grass
        }
        type.lowercase().contains("psychic") -> {
            color = Psych
        }
        type.lowercase().contains("fighting") -> {
            color = Fight
            dark= false
        }
        type.lowercase().contains("ghost") -> color = Ghost
        type.lowercase().contains("bug") -> color = Bug
        type.lowercase().contains("poison") -> color = Poison
        type.lowercase().contains("ground") -> color = Ground
        type.lowercase().contains("rock") -> color = Rock
        type.lowercase().contains("flying") -> {
            color = Flying
            dark= false
        }
        else -> color=Normal
    }

    return Pair(color, if(dark) OffWhite else DarkGray)
}