package com.example.moviemagic.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.moviemagic.domain.Movie
import com.example.moviemagic.presentation.theme.MovieMagicTheme
import com.example.moviemagic.presentation.viewmodel.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
	
	private val viewModel: MoviesViewModel by viewModel()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MovieMagicTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
				) {
					Column(horizontalAlignment = Alignment.CenterHorizontally) {
						SearchBar(viewModel)
						MovieList(viewModel)
					}
				}
			}
		}
	}
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(viewModel: MoviesViewModel) {
	var text by rememberSaveable { mutableStateOf("") }
	var active by rememberSaveable { mutableStateOf(false) }
	
	val searchResult by viewModel.searchMovies.observeAsState()
	
	Box(Modifier
		.semantics { isTraversalGroup = true }) {
		SearchBar(
			modifier = Modifier
				.align(Alignment.TopCenter)
				.semantics { traversalIndex = -1f },
			query = text,
			onQueryChange = {
				text = it
				viewModel.searchMovies(text)
			},
			onSearch = {
				active = false
			},
			active = active,
			onActiveChange = {
				active = it
			},
			placeholder = { Text("Search movies year title...") },
			leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
			trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
		) {
			repeat(searchResult?.search?.size ?: 0) { idx ->
				val resultText = "${searchResult?.search?.get(idx)?.title}"
				ListItem(headlineContent = { Text(resultText) },
					supportingContent = { Text("${searchResult?.search?.get(idx)?.year}") },
					leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
					modifier = Modifier
						.clickable {
							text = resultText
							active = false
						}
						.fillMaxWidth()
						.padding(horizontal = 16.dp, vertical = 4.dp))
			}
		}
	}
}


@Composable
fun MovieList(viewModel: MoviesViewModel) {
	
	LaunchedEffect(viewModel) {
		viewModel.getMovies()
	}
	
	val movies by viewModel.movies.observeAsState()
	
	LazyColumn {
		item(movies?.search.orEmpty()) {
			movies?.search?.forEach { movie ->
				MovieItem(movie = movie)
			}
		}
	}
}

@Composable
fun MovieItem(movie: Movie) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		
		if (movie.poster.isNotEmpty() && movie.poster != "N/A") {
			ImageContent(movie.poster)
		} else {
			PlaceholderContent()
		}
		
		Text(
			text = movie.title,
			color = Color.Black,
			textAlign = TextAlign.Center,
			modifier = Modifier
				.padding(top = 8.dp)
				.align(Alignment.CenterHorizontally)
		)
		Text(
			text = "Year: ${movie.year}",
			color = Color.Gray,
			textAlign = TextAlign.Center,
			modifier = Modifier
				.padding(top = 4.dp)
				.align(Alignment.CenterHorizontally)
		)
	}
}

@Composable
fun ImageContent(poster: String) {
	Image(
		painter = rememberImagePainter(
			data = poster,
			builder = {
				crossfade(true)
			}
		),
		contentDescription = null,
		contentScale = ContentScale.FillWidth,
		modifier = Modifier
			.fillMaxWidth()
			.height(200.dp)
	)
}

@Composable
fun PlaceholderContent() {
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.height(200.dp)
			.background(Color.LightGray),
		contentAlignment = Alignment.Center
	) {
		Text(text = "No Image Available", color = Color.White)
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MovieMagicTheme {
		Column {
		
		}
	}
}