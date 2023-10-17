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
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Webhook
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Webhook
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.coin.R

object CoinRoute {
    //一级路由
    const val HOME = "Home"
    const val MARKET = "Market"
    const val ASSET = "Asset"

    const val Account = "Account"
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
                saveState = false
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
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = R.string.tab_home
    ),
    CoinTopLevelDestination(
        route = CoinRoute.MARKET,
        selectedIcon = Icons.Default.Webhook,
        unselectedIcon = Icons.Outlined.Webhook,
        iconTextId = R.string.tab_market
    ),
    CoinTopLevelDestination(
        route = CoinRoute.ASSET,
        selectedIcon = Icons.Default.AccountBalanceWallet,
        unselectedIcon = Icons.Outlined.AccountBalanceWallet,
        iconTextId = R.string.tab_asset
    )
)


fun isTopNavigator(currentDestination:String?): Boolean {
    if (currentDestination==null) return false;
    for (topLevelDestination in TOP_LEVEL_DESTINATIONS) {
        if (topLevelDestination.route == currentDestination) return true
    }
    return false;
}