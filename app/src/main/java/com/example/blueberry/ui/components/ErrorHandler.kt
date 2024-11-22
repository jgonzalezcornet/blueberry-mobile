package com.example.blueberry.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import com.example.blueberry.R
import com.example.blueberry.ui.home.HomeViewModel
import com.example.blueberry.data.model.Error

@Composable
fun ErrorDialog (
    error: Error,
    onClose: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onClose,
        title = {
            Text(
                text = "${stringResource(R.string.error_dialog_title)}: ${error.code}",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Red
            )
        },
        text = {
            Text(
                text = error.message,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(onClick = onClose) {
                Text(stringResource(R.string.error_dialog_close))
            }
        },
        containerColor = Color.White
    )
}

@Composable
fun ErrorHandler (
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
    val uiState = viewModel.uiState
    var showDialog by rememberSaveable { mutableStateOf<Boolean>(false) }
    var errorCode by rememberSaveable { mutableIntStateOf(0) }
    var errorMessage by rememberSaveable { mutableStateOf<String>("") }

    LaunchedEffect(uiState.error) {
        if(uiState.error != null && showDialog == false){
            showDialog = true
            errorCode = uiState.error.code ?: 0
            errorMessage = uiState.error.message
            viewModel.clearError()
        }
    }

    if(showDialog && errorCode != 0 && errorMessage != "") {
        ErrorDialog(
            error = Error(errorCode, errorMessage),
            onClose = { showDialog = false }
        )
    }
}