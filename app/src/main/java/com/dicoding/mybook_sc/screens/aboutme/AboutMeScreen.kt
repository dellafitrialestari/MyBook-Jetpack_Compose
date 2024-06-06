package com.dicoding.mybook_sc.screens.aboutme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.mybook_sc.R
import com.dicoding.mybook_sc.screens.detail.DetailTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutMeScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val aboutMeText = stringResource(id = R.string.Keterangan)
    val contntDesc = stringResource(id = R.string.ContenDesc)
    val name = stringResource(id = R.string.name)
    val email = stringResource(id = R.string.email)
    val insta = stringResource(id = R.string.Insta)
    val title = stringResource(id = R.string.Title)
    val title2 = stringResource(id = R.string.Title2)
    val title3 = stringResource(id = R.string.Title3)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            DetailTopAppBar(title = title, onBackPressed = onBackClick)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 90.dp, bottom = 16.dp),
                contentAlignment = Alignment.Center,

                ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.foto_profil
                    ),
                    contentDescription = contntDesc,
                    modifier = modifier
                        .sizeIn(maxWidth = 150.dp, maxHeight = 150.dp)
                        .clip(
                            RoundedCornerShape(100)
                        ),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = name,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_bold)
                    ),
                    fontSize = 18.sp
                )
                Text(
                    text = email,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_regular)
                    ),
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 30.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title2,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_bold)
                    ),
                    fontSize = 15.sp
                )
                Text(
                    text = aboutMeText,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_regular)
                    ),
                    color = Color.Gray,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 18.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = title3,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_bold)
                    ),
                    fontSize = 15.sp
                )
                Text(
                    text = insta,
                    fontFamily = FontFamily(
                        Font(R.font.montserrat_regular)
                    ),
                    color = Color.Gray,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}