package com.acidstudio.androiddesingjetpackcomposepokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PantallaPrincipal()
        }
    }
}

@Composable
fun PantallaPrincipal() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Fondo
        Image(
            painter = painterResource(id = R.drawable.background_pokemon),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Head()
            Spacer(modifier = Modifier.height(32.dp))
            CardsItems()
            Spacer(modifier = Modifier.height(32.dp))
            ItemsPokemon(ObtenerPokemon())
        }
    }
}


@Composable
fun Head() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 82.dp)
    )
    {
        Text(
            text = "What Pokemon are you looking for?",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        SimpleSearchBar()


    }

}

@Composable
fun SimpleSearchBar() {

    var text by remember { mutableStateOf("") }

    Spacer(modifier = Modifier.padding(top = 18.dp))
    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "Search Icon")
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF888888),
            unfocusedContainerColor = Color(0xFF2E2B2C),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(fontSize = 18.sp),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(end = 10.dp),
        placeholder = { Text("Search Pokemon") },
        shape = RoundedCornerShape(24.dp)

    )
}


@Composable
fun CardsItems() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Items("Pokedex", Color(0xFFDC3030), Color(0xFFA80D0D), id = R.drawable.pokebola_icon)
            Spacer(modifier = Modifier.padding(start = 18.dp))
            Items("Moves", Color(0xFFC48C32), Color(0xFFBB7A0C), id = R.drawable.rayo_iconos)
        }
        Spacer(modifier = Modifier.padding(top = 18.dp))
        Row {
            Items("Evolutions", Color(0xFF22A624), Color(0xFF085006), id = R.drawable.adn_icon)
            Spacer(modifier = Modifier.padding(start = 18.dp))
            Items("Locations", Color(0xFF137A94), Color(0xFF093F64), id = R.drawable.map_icon)
        }
    }
}

@Composable
fun Items(text: String, color: Color, color2: Color, id: Int) {
    Box(
        modifier = Modifier
            .width(170.dp)
            .height(70.dp + 12.dp)
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(colors = listOf(color, color2))
                    )
            ) {
                Text(
                    text = text,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 12.dp),
                    textAlign = TextAlign.Start
                )
            }
        }

        Image(
            painter = painterResource(id),
            contentDescription = "Pokebola Icon",
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.TopEnd)
                .offset(x = (-8).dp, y = (-4).dp)
        )
    }
}

data class Pokemon(val id: Int, val image: Int, val text: String)

fun ObtenerPokemon(): List<Pokemon> {
    val listaPokemon = listOf(
        Pokemon(1, R.drawable.bulbasuar, "Bulbasaur"),
        Pokemon(2, R.drawable.squirtle, "Squirtle"),
        Pokemon(3, R.drawable.charmander, "Charmander"),
        Pokemon(4, R.drawable.pikachu, "Pikachu"),
        Pokemon(5, R.drawable.mew, "Mew")
    )

    return listaPokemon
}

@Composable
fun ItemsPokemon(pokemons: List<Pokemon>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Watch",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp, modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 6.dp)
        )

        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {

            items(pokemons, key = { product -> product.id }) { product ->
                // Display each product using the ProductItem composable
                ProductItem(product)
            }
        }
    }
}

@Composable
fun ProductItem(pokemon: Pokemon) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(130.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(Color.White)
            .border(1.dp, Color.Gray)
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally

    ) {
        // Display the product's name
        Text(
            text = pokemon.text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Image(
            painter = painterResource(pokemon.image),
            contentDescription = "Pokebola Icon",
            modifier = Modifier
                .size(120.dp)
        )
    }
}

@Preview
@Composable
fun PreviewContent() {
    PantallaPrincipal()
}



