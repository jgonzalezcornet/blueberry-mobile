package com.example.blueberry.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import com.example.blueberry.ui.components.AliasCard
import com.example.blueberry.ui.components.ChangeAliasCard
import com.example.blueberry.ui.components.ScreenTitle
import com.example.blueberry.ui.theme.PrimaryBlue

@Composable
fun AliasScreen(
    modifier: Modifier = Modifier,
    onBackNavigation: () -> Unit = {}
) {
    var changeAliasModalOpen by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ScreenTitle(
            title = stringResource(R.string.alias_title),
            onBackNavigation = onBackNavigation
        )
        AliasCard()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { changeAliasModalOpen = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue // Set the background color here
                )
            ) {
                Text(
                    text = stringResource(R.string.change_alias),
                    color = Color.White
                )
            }
        }
        if(changeAliasModalOpen){
            ChangeAliasCard(
                onClose = { changeAliasModalOpen = false }
            )
        }
    }
}

@PreviewScreenSizes
@Composable
fun AliasScreenPreview() {
    AliasScreen()
}
