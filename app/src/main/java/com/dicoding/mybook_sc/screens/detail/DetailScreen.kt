package com.dicoding.mybook_sc.screens.detail

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.mybook_sc.R
import com.dicoding.mybook_sc.data.local.JSONObject
import com.dicoding.mybook_sc.di.Injection
import com.dicoding.mybook_sc.screens.UiState
import com.dicoding.mybook_sc.screens.ViewModelFactory
import com.dicoding.mybook_sc.ui.theme.lightBlue


@Composable
fun DetailScreen(
    id: Long,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(repository = Injection.provideRepository(context = LocalContext.current))
    ),
    navigateBack: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getBookById(id)
            }
            is UiState.Success -> {
                DetailContent(
                    name = uiState.data.name,
                    penulis = uiState.data.penulis,
                    descDetail = uiState.data.descDetail,
                    descHalaman = uiState.data.descHalaman,
                    descPenerbit = uiState.data.descPenerbit,
                    descISBN = uiState.data.descISBN,
                    tanggal = uiState.data.tanggal,
                    photo = uiState.data.photo,
                    modifier = modifier,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(
    name: String,
    penulis: String,
    descDetail: String,
    descHalaman: Int,
    descPenerbit: String,
    descISBN: String,
    tanggal: String,
    photo: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    context: Context = LocalContext.current
) {
    val titleb = stringResource(id = R.string.TitleB)
    val desc = stringResource(id = R.string.DescBook)
    val detail = stringResource(id = R.string.DetailBook)
    val jmlhHal = stringResource(id = R.string.jmlHal)
    val ISBN = stringResource(id = R.string.ISBN)
    val penerbit = stringResource(id = R.string.penerbit)
    val tglTerbit = stringResource(id = R.string.tglTerbit)
    val share1 = stringResource(id = R.string.share1)
    val share2 = stringResource(id = R.string.share2)
    val txtShare1 = stringResource(id = R.string.txtShare1)
    val txtShare2 = stringResource(id = R.string.txtShare2)

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .fillMaxWidth(),
        topBar = {
            DetailTopAppBar(title = titleb, onBackPressed = onBackClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(top = 24.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(
                        id = JSONObject.getResourceIdByName(
                            context = LocalContext.current,
                            photo
                        )
                    ),
                    contentDescription = name,
                    modifier = modifier
                        .sizeIn(maxWidth = 150.dp, maxHeight = 150.dp)
                        .clip(
                            RoundedCornerShape(0)
                        ),
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                androidx.compose.material.Text(
                    text = name,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_bold)
                    ),
                    fontSize = 18.sp
                )
                androidx.compose.material.Text(
                    text = penulis,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_regular)
                    ),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                androidx.compose.material.Text(
                    text = desc,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_bold)
                    ),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                androidx.compose.material.Text(
                    text = descDetail,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_light)
                    ),
                    fontSize = 13.sp,
                    textAlign = TextAlign.Justify
                )
            }
            Text(
                text = detail,
                fontFamily = FontFamily(
                    Font(R.font.montserrat_bold)
                ),
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .padding(horizontal = 16.dp),
                color = Color.Black
            )
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Column(
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = jmlhHal,
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_medium)
                        ),
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                    Text(
                        text = "$descHalaman",
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_light)
                        ),
                        fontSize = 13.sp,
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        color = Color.Black
                    )
                    Text(
                        text = ISBN,
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_medium)
                        ),
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                    Text(
                        text = descISBN,
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_light)
                        ),
                        fontSize = 13.sp,
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        color = Color.Black
                    )
                }
                Column(
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = penerbit,
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_medium)
                        ),
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                    Text(
                        text = descPenerbit,
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_light)
                        ),
                        fontSize = 13.sp,
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        color = Color.Black
                    )
                    Text(
                        text = tglTerbit,
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_medium)
                        ),
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                    Text(
                        text = tanggal,
                        fontFamily = FontFamily(
                            Font(R.font.montserrat_light)
                        ),
                        fontSize = 13.sp,
                        modifier = Modifier
                            .padding(bottom = 8.dp),
                        color = Color.Black
                    )
                }
            }
            Button(
                onClick = {
                    val extratext = "$txtShare1 $name $txtShare2 $penulis"

                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    intent.putExtra(Intent.EXTRA_TEXT, extratext)

                    context.startActivity(
                        Intent.createChooser(intent, share2)
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 25.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = lightBlue,
                ),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = share1,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_extra_bold)
                    ),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(
    title: String,
    onBackPressed: () -> Unit
) {
    val back = stringResource(id = R.string.back)

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = back,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        },
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
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    )
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    DetailContent(
        name = "Book Title",
        penulis = "Book Writer",
        descDetail = "This is a detail of the book",
        descHalaman = 200,
        descPenerbit = "Penerbit Buku",
        descISBN = "111111",
        tanggal = "01 January 2023",
        photo = "",
        onBackClick = {},
        )
}

