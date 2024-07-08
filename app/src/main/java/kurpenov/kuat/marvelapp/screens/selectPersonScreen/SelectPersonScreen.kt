package kurpenov.kuat.marvelapp.screens.selectPersonScreen

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kurpenov.kuat.marvelapp.R
import kurpenov.kuat.marvelapp.core.rememberStateWithLifecycle
import kurpenov.kuat.marvelapp.core.showToast
import kurpenov.kuat.marvelapp.core.ui.di.daggerSavedStateViewModel
import kurpenov.kuat.marvelapp.screens.selectPersonScreen.components.BackgroundElement
import kurpenov.kuat.marvelapp.screens.selectPersonScreen.components.RowHero
import kurpenov.kuat.marvelapp.screens.selectPersonScreen.di.selectPersonComponent
import kurpenov.kuat.marvelapp.ui.theme.Dune
import kurpenov.kuat.marvelapp.ui.theme.Typography

@RootNavGraph(start = true)
@Destination
@Composable
fun SelectPersonScreen(navigator: DestinationsNavigator) {
    val activity = LocalContext.current as Activity
    val viewModel =  daggerSavedStateViewModel {
        selectPersonComponent.getInstance(activity).selectPersonViewModelFactory.create(it)
    }
    viewModel.sendEvent(SelectPersonUIEvent.OnOpenScreen)
    SelectPersonScreen(
        viewModel = viewModel,
        onNavigateToPersonScreen = {
            navigator.navigate(
                kurpenov.kuat.marvelapp.screens.destinations.PersonScreenDestination(it)
            )
        }
    )
}

@Composable
private fun SelectPersonScreen(
    viewModel: SelectPersonViewModel,
    onNavigateToPersonScreen: (Int) -> Unit
) {
    val uiState by rememberStateWithLifecycle(viewModel.state)
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SelectPersonUIEffect.NavigateToPersonScreen ->
                    onNavigateToPersonScreen(effect.characterId)

                is SelectPersonUIEffect.ErrorToLoadData ->
                    showToast(context, effect.error)

            }
        }
    }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Dune)
        ) {
            Log.e("COLORCUR", uiState.backgroundColor.value.toString())
            BackgroundElement(color = uiState.backgroundColor)

            Column {
                Image(
                    painter = painterResource(R.drawable.marvel),
                    contentDescription = null,
                    modifier = Modifier
                        .scale(0.4f)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.width(32.dp))
                Text(
                    text = stringResource(id = R.string.main_text),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = Typography.h4,
                    color = Color.White
                )
                if (uiState.getDataIsSuccessful) {
                Spacer(modifier = Modifier.height(32.dp))
                RowHero(heros = uiState.listHero,
                    onValueChange = {
                        viewModel.sendEvent(
                            SelectPersonUIEvent.OnCurrentIndexChange(it)
                        )
                    },
                    onclick = {
                        viewModel.sendEvent(SelectPersonUIEvent.OnclickHero(it))
                    }
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = uiState.errorToLoadData,
                        textAlign = TextAlign.Center,
                        style = Typography.h4,
                        color = Color.Red,
                        modifier = Modifier.clickable { viewModel.sendEvent(SelectPersonUIEvent.OnOpenScreen) }
                    )
                }
            }
        }
    }
}

