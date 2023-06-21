package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.addNew.AddNewItem
import com.example.myapplication.detailedView.DeadlineDetailView
import com.example.myapplication.finishedView.FinishedDeadline
import com.example.myapplication.listView.DeadlineList
import com.example.myapplication.settings.Settings

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomBar(
            items = listOf(
                BottomNavItem(
                    route = "home",
                    title = stringResource(id = R.string.nav_home),
                    icon = Icons.Default.Home
                ),
                BottomNavItem(
                    route = "finished",
                    title = stringResource(id = R.string.nav_finished),
                    icon = Icons.Default.Done
                ),
                BottomNavItem(
                    route = "create",
                    title = stringResource(id = R.string.nav_create),
                    icon = Icons.Default.Add
                ),
                BottomNavItem(
                    route = "settings",
                    title = stringResource(id = R.string.nav_settings),
                    icon = Icons.Default.Settings
                )
            ),
            navController = navController,
            onItemClick = {
                navController.navigate(it.route)
            }
        )
    }) {
        Navigation(navController = navController)
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            DeadlineList(
                onDeadlineClick = { deadlineId ->
                    navController.navigate("detailedView/$deadlineId")
                }
            )
        }

        composable("finished") {
            FinishedDeadline()
        }

        composable("create") {
            AddNewItem()
        }

        composable("settings") {
            Settings()
        }

        composable(
            "detailedView/{deadlineId}"
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("deadlineId")?.let { DeadlineDetailView(it) }
        }
    }
}

@Composable
fun BottomBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.DarkGray,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(imageVector = item.icon, contentDescription = item.title)
                        Text(
                            text = item.title,
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp
                        )
                    }
                }
            )
        }
    }
}





