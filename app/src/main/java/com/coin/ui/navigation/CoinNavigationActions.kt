/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.coin.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.coin.R

object CoinRoute {
    const val HOME = "Home"
    const val QUIZ = "Quiz"
    const val INVEST = "Invest"
    const val ACCOUNT = "Account"
}

data class CoinTopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

class CoinNavigationActions(private val navController: NavHostController) {

    fun navigateTo(destination: CoinTopLevelDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}

val TOP_LEVEL_DESTINATIONS = listOf(
    CoinTopLevelDestination(
        route = CoinRoute.HOME,
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Default.Inbox,
        iconTextId = R.string.tab_home
    ),
    CoinTopLevelDestination(
        route = CoinRoute.INVEST,
        selectedIcon = Icons.Default.Inventory,
        unselectedIcon = Icons.Default.Article,
        iconTextId = R.string.tab_invest
    ),
    CoinTopLevelDestination(
        route = CoinRoute.QUIZ,
        selectedIcon = Icons.Default.AutoAwesome,
        unselectedIcon = Icons.Outlined.ChatBubbleOutline,
        iconTextId = R.string.tab_quiz
    ),
    CoinTopLevelDestination(
        route = CoinRoute.ACCOUNT,
        selectedIcon = Icons.Default.People,
        unselectedIcon = Icons.Default.People,
        iconTextId = R.string.tab_account
    )

)
