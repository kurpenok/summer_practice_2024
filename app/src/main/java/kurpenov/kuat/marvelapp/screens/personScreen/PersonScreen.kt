package kurpenov.kuat.marvelapp.screens.personScreen

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kurpenov.kuat.marvelapp.screens.personScreen.components.BackButton
import kurpenov.kuat.marvelapp.screens.personScreen.components.BackgroundImage
import kurpenov.kuat.marvelapp.core.rememberStateWithLifecycle
import kurpenov.kuat.marvelapp.core.showToast
import kurpenov.kuat.marvelapp.core.ui.di.daggerSavedStateViewModel
import kurpenov.kuat.marvelapp.screens.personScreen.di.personScreenComponent
import kurpenov.kuat.marvelapp.ui.theme.Typography

@Destination
@Composable
fun PersonScreen(navigator: DestinationsNavigator, characterId: Int) {
    val activity = LocalContext.current as Activity
    val viewModel =  daggerSavedStateViewModel {
        personScreenComponent.getInstance(activity).personScreenViewModelFactory.create(it)
    }
    viewModel.sendEvent(PersonScreenUIEvent.OnGetData(characterId))
    PersonScreen(
        viewModel = viewModel,
        onBack = { navigator.popBackStack() }
    )
}

@Composable
private fun PersonScreen(
    viewModel: PersonScreenViewModel,
    onBack: () -> Unit
) {
    val uiState by rememberStateWithLifecycle(viewModel.state)
    val context = LocalContext.current
    LaunchedEffect(Unit) {

        viewModel.effect.collect { effect ->
            when (effect) {
                is PersonScreenUIEffect.ErrorToLoadData ->
                    showToast(context, effect.error)

            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundImage(url = uiState.url)
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            BackButton(
                onClick = { onBack() },
                modifier = Modifier.padding(top = 16.dp)
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = uiState.personName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    style = Typography.h3,
                    color = Color.White,
                    )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = uiState.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp),
                    style = Typography.h4,
                    color = Color.White,
                )
            }
        }
    }
}
