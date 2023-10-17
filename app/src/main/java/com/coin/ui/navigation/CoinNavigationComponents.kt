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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.coin.R
import com.coin.ui.utils.CoinNavigationContentPosition

@Composable
fun CoinNavigationRail(
    selectedDestination: String,
    navigationContentPosition: CoinNavigationContentPosition,
    navigateToTopLevelDestination: (CoinTopLevelDestination) -> Unit,
    navigatePageDestination: (String) -> Unit,
    onDrawerClicked: () -> Unit = {},
) {
    NavigationRail(
        modifier = Modifier.fillMaxHeight(),
        containerColor = MaterialTheme.colorScheme.inverseOnSurface
    ) {
        Layout(
            modifier = Modifier.widthIn(max = 80.dp), content = {
                Column(
                    modifier = Modifier.layoutId(LayoutType.HEADER),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    NavigationRailItem(selected = false, onClick = onDrawerClicked, icon = {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = stringResource(id = R.string.navigation_drawer)
                        )
                    })
                    FloatingActionButton(
                        onClick = {
                            navigatePageDestination(CoinRoute.Account)
                            onDrawerClicked()

                        },
                        modifier = Modifier.padding(top = 8.dp, bottom = 32.dp),
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                    ) {
                        Icon(
                            imageVector = Icons.Default.PersonPin,
                            contentDescription = stringResource(id = R.string.account),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(Modifier.height(8.dp)) // NavigationRailHeaderPadding
                    Spacer(Modifier.height(4.dp)) // NavigationRailVerticalPadding
                }

                Column(
                    modifier = Modifier.layoutId(LayoutType.CONTENT),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    TOP_LEVEL_DESTINATIONS.forEach { coinDestination ->
                        NavigationRailItem(selected = selectedDestination == coinDestination.route,
                            onClick = { navigateToTopLevelDestination(coinDestination) },
                            icon = {
                                Icon(
                                    imageVector = coinDestination.selectedIcon,
                                    contentDescription = stringResource(
                                        id = coinDestination.iconTextId
                                    )
                                )
                            })
                    }
                }
            }, measurePolicy = navigationMeasurePolicy(navigationContentPosition)
        )
    }
}

@Composable
fun CoinBottomNavigationBar(
    selectedDestination: String, navigateToTopLevelDestination: (CoinTopLevelDestination) -> Unit
) {

    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        TOP_LEVEL_DESTINATIONS.forEach { coinDestination ->
            NavigationBarItem(selected = selectedDestination == coinDestination.route,
                onClick = { navigateToTopLevelDestination(coinDestination) },
                icon = {
                    Icon(
                        imageVector = coinDestination.selectedIcon,
                        contentDescription = stringResource(id = coinDestination.iconTextId)
                    )
                })
        }
    }
}

fun navigationMeasurePolicy(
    navigationContentPosition: CoinNavigationContentPosition,
): MeasurePolicy {
    return MeasurePolicy { measurables, constraints ->
        lateinit var headerMeasurable: Measurable
        lateinit var contentMeasurable: Measurable
        measurables.forEach {
            when (it.layoutId) {
                LayoutType.HEADER -> headerMeasurable = it
                LayoutType.CONTENT -> contentMeasurable = it
                else -> error("Unknown layoutId encountered!")
            }
        }

        val headerPlaceable = headerMeasurable.measure(constraints)
        val contentPlaceable = contentMeasurable.measure(
            constraints.offset(vertical = -headerPlaceable.height)
        )
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Place the header, this goes at the top
            headerPlaceable.placeRelative(0, 0)

            // Determine how much space is not taken up by the content
            val nonContentVerticalSpace = constraints.maxHeight - contentPlaceable.height

            val contentPlaceableY = when (navigationContentPosition) {
                // Figure out the place we want to place the content, with respect to the
                // parent (ignoring the header for now)
                CoinNavigationContentPosition.TOP -> 0
                CoinNavigationContentPosition.CENTER -> nonContentVerticalSpace / 2
            }
                // And finally, make sure we don't overlap with the header.
                .coerceAtLeast(headerPlaceable.height)

            contentPlaceable.placeRelative(0, contentPlaceableY)
        }
    }
}

enum class LayoutType {
    HEADER, CONTENT
}
