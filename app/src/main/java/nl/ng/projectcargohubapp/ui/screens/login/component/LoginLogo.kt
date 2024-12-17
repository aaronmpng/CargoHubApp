package nl.ng.projectcargohubapp.ui.screens.login.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.strings.Strings


@Composable
fun LoginLogo(){
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = stringResource(R.string.image)
    )
}