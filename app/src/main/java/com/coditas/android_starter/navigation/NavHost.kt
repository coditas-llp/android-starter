import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.coditas.android_starter.screens.screenOne.ScreenOne
import com.coditas.android_starter.screens.screenTwo.ScreenTwo

@Composable
fun MyNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "screenOne") {
        composable("screenOne") { ScreenOne(navController) }
        composable(
            "screenTwo/{param}",
            arguments = listOf(navArgument("param") { type = NavType.StringType })
        ) { backStackEntry ->
            val param = backStackEntry.arguments?.getString("param")
            ScreenTwo(navController, param)
        }
    }
}
