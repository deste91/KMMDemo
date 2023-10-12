package it.reply.webinardemo.android.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.reply.webinardemo.android.presentation.theme.PokedexTheme
import pokedex.eu.reply.presentation.theme.Mariner
import pokedex.eu.reply.presentation.theme.Turbo

@Composable
fun TurboMarineButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
) {

    StandardButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        border = BorderStroke(width = 2.dp, color = Mariner),
        colors = ButtonDefaults.buttonColors(
            containerColor = Turbo,
            contentColor = Mariner,
            disabledContainerColor = Turbo.copy(alpha = .7f),
            disabledContentColor = Mariner.copy(alpha = .7f)
        ),
        contentPadding = contentPadding
    ) {
        Text(text = text.uppercase(), style = MaterialTheme.typography.bodyLarge)
    }

}

@Preview
@Composable
fun TurboMarineButtonPreview() {
    PokedexTheme {
        TurboMarineButton(text = "button", onClick = {})
    }
}