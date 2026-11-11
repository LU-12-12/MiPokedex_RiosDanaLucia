package chavez.alma.pokedexlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import chavez.alma.pokedexlist.model.data.Users
import chavez.alma.pokedexlist.navigation.AppNavigation
import chavez.alma.pokedexlist.ui.theme.PokedexListTheme
import chavez.alma.pokedexlist.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexListTheme {
                val context = LocalContext.current
                val users = remember { Users(context) }
                val userViewModel = remember { UserViewModel(users) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        innerPadding = innerPadding,
                        userViewModel = userViewModel
                    )
                }
            }
        }
    }
}
