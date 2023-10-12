package it.reply.webinardemo.android.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.reply.webinardemo.android.presentation.theme.PokedexTheme
import it.reply.webinardemo.domain.model.Pokemon
import pokedex.eu.reply.presentation.theme.Mariner

@Composable
fun StandardStatsTable(
    modifier: Modifier = Modifier,
    titleColumnOne: String,
    titleColumnTwo: String,
    elementsColumnOne: List<String>,
    elementsColumnTwo: List<String>
) {
    Row(
        modifier = modifier
            .padding(15.dp)
            .height(IntrinsicSize.Min)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AutoResizeText(
                text = titleColumnOne,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Mariner
            )

            Divider(
                color = Mariner,
                modifier = Modifier
                    .padding(start = 15.dp)
            )

            elementsColumnOne.forEach {
                AutoResizeText(text = it, color = Mariner)
            }
        }

        Divider(
            color = Mariner,
            thickness = 1.dp,
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AutoResizeText(
                text = titleColumnTwo,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Mariner
            )

            Divider(
                color = Mariner,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 15.dp)
            )

            elementsColumnTwo.forEach {
                AutoResizeText(text = it, color = Mariner)
            }
        }
    }
}

@Preview
@Composable
fun StandardStatsTablePreview() {
    PokedexTheme {
        StandardStatsTable(
            titleColumnOne = "Statistics",
            titleColumnTwo = "Value",
            elementsColumnOne = listOf("HP", "Attack", "Defense"),
            elementsColumnTwo = listOf("20", "25", "40")
        )
    }
}
