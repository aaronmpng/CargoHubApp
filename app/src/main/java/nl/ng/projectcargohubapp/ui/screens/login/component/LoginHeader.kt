package nl.ng.projectcargohubapp.ui.screens.login.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.ng.projectcargohubapp.R
import nl.ng.projectcargohubapp.theme.ColorPalette

@Composable
fun LoginHeader(){
    Text(
        text = stringResource(R.string.login),
        fontFamily = FontFamily.Monospace,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = ColorPalette.White
    )
    Spacer(modifier = Modifier.padding(top = 20.dp))
}