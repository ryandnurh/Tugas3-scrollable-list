package com.example.scrollablelist

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.scrollablelist.model.Anime
import com.example.scrollablelist.model.Animes

@Composable
fun AnimeApp(navController: NavController){
    Scaffold (
        topBar = {
            AppMenuTopBar()
        }
    ){
        LazyColumn(contentPadding = it) {
            items(Animes) { anime ->
                AnimeCard(
                    anime = anime,
                    nav = navController
                )
            }
        }
    }
}

@Composable
fun AnimeCard(anime: Anime, nav: NavController) {
    var expanded by remember { mutableStateOf(false) }
    val judul = LocalContext.current.getString(anime.judul)
    val poster = anime.poster
    val description = anime.description
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
        label = "",
    )
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { nav.navigate("halaman2/$judul/$poster/$description") }
    ) {
        Column( modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
            .background(color = color)

        ){
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Image(
                    painter = painterResource(anime.poster),
                    contentDescription = stringResource(anime.judul)
                )
                Text(
                    text = LocalContext.current.getString(anime.judul),
                    modifier = Modifier
                        .absolutePadding(10.dp)
                        .width(150.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.padding(28.dp))
                ItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }
            if (expanded) {
                Description(
                    anime.description,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}

/*
@Composable
fun AnimeList(animeList: List<Anime>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(animeList) { anime ->
            AnimeCard(
                anime = anime,
                modifier = Modifier.padding(8.dp)
            )
        }

    }
}
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppMenuTopBar(){
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(60.dp),
                    painter = painterResource(R.drawable.logo),
                    contentScale = ContentScale.Crop,

                    contentDescription = null
                )
                Text(text = stringResource(R.string.app_name))
            }
        },
        modifier = Modifier
    )
}

@Composable
private fun ItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.run {
                if (expanded)
                    KeyboardArrowUp
                else
                    KeyboardArrowDown
            },
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = null
        )
    }
}

@Composable
fun Description(
    @StringRes description: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.padding(6.dp))
        Text(
            text = stringResource(R.string.desc),
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = stringResource(description),
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}