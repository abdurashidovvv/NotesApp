package uz.abdurashidov.notesapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import uz.abdurashidov.notesapp.presentation.bookmark.BookMarkScreen
import uz.abdurashidov.notesapp.presentation.bookmark.BookMarkViewModel
import uz.abdurashidov.notesapp.presentation.detail.DetailAssistFactory
import uz.abdurashidov.notesapp.presentation.detail.DetailScreen
import uz.abdurashidov.notesapp.presentation.home.HomeScreen
import uz.abdurashidov.notesapp.presentation.home.HomeViewModel

enum class Screens {
    HOME, DETAIL, BOOKMARK
}


@Composable
fun NoteNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    bookMarkViewModel: BookMarkViewModel,
    assistFactory: DetailAssistFactory
) {
    NavHost(navController = navHostController, startDestination = Screens.HOME.name) {
        composable(route = Screens.HOME.name) {
            val state by homeViewModel.state.collectAsState()
            HomeScreen(
                state = state,
                onBookmarkChange = homeViewModel::onBookmarkChange,
                onDelete = homeViewModel::deleteNote,
                onNoteClick = {
                    navHostController.navigateToSingleTop(
                        route = "${Screens.DETAIL}?id=${it}"
                    )
                })
        }
        composable(route = Screens.BOOKMARK.name) {
            val state by bookMarkViewModel.state.collectAsState()
            BookMarkScreen(
                state = state,
                onBookmarkChange = bookMarkViewModel::onBookmarkChage,
                onDelete = bookMarkViewModel::deleteNote,
                onNoteClick = {
                    navHostController.navigateToSingleTop(
                        route = "${Screens.DETAIL.name}?id=$it"
                    )
                })
        }
        composable(
            route = "${Screens.DETAIL.name}?id={id}",
            arguments = listOf(
                navArgument("id") {
                    NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong("id") ?: -1L
            DetailScreen(
                noteId = id,
                assistFactory = assistFactory,
                navigateUp = { navHostController.navigateUp() }
            )
        }
    }
}

fun NavHostController.navigateToSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}