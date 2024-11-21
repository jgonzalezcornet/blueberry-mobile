package com.example.blueberry.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import androidx.compose.material3.MaterialTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    isUserLoggedIn: Boolean = false,
    openModalNavigation: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.blueberryshadow),
                    contentDescription = "Logo",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        actions = {
            if (isUserLoggedIn) {
                IconButton(onClick = openModalNavigation) {
                    Icon(
                        Icons.Rounded.Menu,
                        contentDescription = stringResource(id = R.string.hamburger_icon_description),
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@PreviewScreenSizes
@Composable
fun TopBarPreview() {
    TopBar(isUserLoggedIn = true)
}

@PreviewScreenSizes
@Composable
fun TopBarNotLoggedInPreview() {
    TopBar(isUserLoggedIn = false)
}

