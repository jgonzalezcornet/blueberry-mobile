package com.example.blueberry.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blueberry.MyApplication
import com.example.blueberry.PreviewScreenSizes
import com.example.blueberry.R
import com.example.blueberry.ui.components.getPadding
import com.example.blueberry.ui.components.home.RecoverPasswordCodeCard
import com.example.blueberry.ui.home.HomeViewModel
import com.example.blueberry.data.model.Error

@Composable
fun RecoverCodeScreen(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit = {},
    onRecoverCodeSuccess: () -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
) {
        var context = LocalContext.current

        Column(
            modifier = modifier
                .verticalScroll(
                    enabled = true,
                    state = rememberScrollState()
                )
                .padding(horizontal = getPadding())
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RecoverPasswordCodeCard(
                onCancel = onCancel,
                onRecoverCode = { code, password, confirmPassword ->
                    if(password != confirmPassword){
                        viewModel.setError(Error(400, context.getString(R.string.password_mismatch_error)))
                    } else {
                        try {
                            viewModel.resetPassword(code, password, onRecoverCodeSuccess)
                        } catch(e: Exception) {
                            viewModel.setError(Error(400, e.message.toString()))
                        }
                    }
                }
            )
        }

}


@PreviewScreenSizes
@Composable
fun RecoverCodeScreenPreview() {
    RecoverCodeScreen()
}