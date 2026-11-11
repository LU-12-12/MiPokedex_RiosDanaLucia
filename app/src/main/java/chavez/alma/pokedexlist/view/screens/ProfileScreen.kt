package chavez.alma.pokedexlist.view.screens

import android.graphics.Paint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import chavez.alma.pokedexlist.model.data.UserViewModel
import chavez.alma.pokedexlist.ui.theme.Pink40
import chavez.alma.pokedexlist.ui.theme.Typography

@Composable
fun ProfileScreen(
    innerPadding: PaddingValues,
    userViewModel: UserViewModel = viewModel()
) {
    val nombre="Gamma"
        /*
        if (userViewModel.currentUser != null) {
        "${userViewModel.currentUser!!.name}"
    } else {
        "Nombre Usuario"
    }
    */
    val mail= "Gamma@mail.com"

    /*
    if (userViewModel.currentUser != null) {
                "${userViewModel.currentUser!!.email}"
            } else {
                "usuario@mail.com"
            }
    */

    Column(Modifier.fillMaxSize()
        .padding(innerPadding)
        .padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            nombre, style = Typography.titleLarge, color = Pink40
        )
        Text(
            mail, style = Typography.displayLarge, color = Pink40
        )

        Spacer(Modifier.size(50.dp))
        Text("Mi compañero", style = Typography.titleLarge)
    }


}


@Preview
@Composable
fun previewProfileScreen() {
    ProfileScreen(PaddingValues(20.dp))
}