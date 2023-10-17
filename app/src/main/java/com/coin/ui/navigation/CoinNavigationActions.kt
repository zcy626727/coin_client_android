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
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Cyclone
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.coin.R

object CoinRoute {
    //一级路由
    const val HOME = "Home"
    const val QUIZ = "Quiz"
    const val INVEST = "Invest"
    const val ASSET = "asset"

    const val Account = "account"
}

data class CoinTopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

class CoinNavigationActions(private val navController: NavHostController) {

    fun navigateTo(destination: CoinTopLevelDestination) {
        // 路由跳转
        navController.navigate(destination.route) {
            // 弹出到图形的起始目标，以避免在用户选择项目时在后退堆栈上建立大型目标堆栈
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // 重新选择同一项目时避免同一目标的多个副本
            launchSingleTop = true
            // 重新选择先前选定的项目时恢复状态
            restoreState = true
        }
    }

    fun navigateTo(route: String) {
        // 路由跳转
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
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
        selectedIcon = Icons.Default.CurrencyExchange,
        unselectedIcon = Icons.Default.Article,
        iconTextId = R.string.tab_invest
    ),
    CoinTopLevelDestination(
        route = CoinRoute.QUIZ,
        selectedIcon = Icons.Default.Cyclone,
        unselectedIcon = Icons.Outlined.ChatBubbleOutline,
        iconTextId = R.string.tab_quiz
    ),
    CoinTopLevelDestination(
        route = CoinRoute.ASSET,
        selectedIcon = Icons.Default.CreditCard,
        unselectedIcon = Icons.Default.People,
        iconTextId = R.string.tab_account
    )
)


fun isTopNavigator(currentDestination:String?): Boolean {
    if (currentDestination==null) return false;
    for (topLevelDestination in TOP_LEVEL_DESTINATIONS) {
        if (topLevelDestination.route == currentDestination) return true
    }
    return false;
}