package com.dicoding.mybook_sc.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dicoding.mybook_sc.R
import com.dicoding.mybook_sc.data.local.Book
import com.dicoding.mybook_sc.data.local.JSONObject
import com.dicoding.mybook_sc.di.Injection
import com.dicoding.mybook_sc.screens.Routes
import com.dicoding.mybook_sc.screens.UiState
import com.dicoding.mybook_sc.screens.ViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(repository = Injection.provideRepository(context = LocalContext.current))
    ),
    navigateToDetail: (Long) -> Unit,
    navController: NavController
) {
    val title = stringResource(id = R.string.app_name)
    val query by viewModel.query

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getBooks()
            }
            is UiState.Success -> {
                Column()
                {
                    HomeTopAppBar(title = title, navController = navController)
                    HomeContent(
                        query = query,
                        books = uiState.data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail,
                        onQueryChange = viewModel::search
                    )
                }
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    query: String,
    books: List<Book>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    onQueryChange: (String) -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        SearchBar(query = query, onQueryChange = onQueryChange)
        LazyColumn(
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(books) { book ->
                CardItem(
                    id = book.id,
                    name = book.name,
                    penulis = book.penulis,
                    photo = book.photo,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val textSearch = stringResource(id = R.string.search)

    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        },
        placeholder = {
            Text(text = textSearch)
        },
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .border(
                border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
    )
}


@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    id: Long = 1,
    name: String,
    penulis: String,
    photo: String,
    navigateToDetail: (Long) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            navigateToDetail(id)
        },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(all = 16.dp)
        ) {
            Image(
                painter = painterResource(id = JSONObject.getResourceIdByName(LocalContext.current, photo)),
                contentDescription = name,
                modifier = modifier
                    .sizeIn(maxHeight = 100.dp, maxWidth = 70.dp)
            )
            Column(
                modifier = modifier.weight(4f)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_bold))
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Text(
                    text = penulis,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        color = Color.Gray
                    ),
                    modifier = Modifier.padding(top = 12.dp),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    title: String,
    navController: NavController
) {
    val titleAbout = stringResource(id = R.string.titleAbout)

    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color.White,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_bold)
                    ),
                )
            )
        },
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(Routes.Profile.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = titleAbout,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )
}
