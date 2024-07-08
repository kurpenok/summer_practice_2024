package kurpenov.kuat.marvelapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import kurpenov.kuat.marvelapp.screens.NavGraphs

@Composable
fun AppHost() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}
