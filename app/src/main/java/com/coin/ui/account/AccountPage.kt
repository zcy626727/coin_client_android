package com.coin.ui.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MenuOpen
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.coin.R
import com.coin.ui.navigation.CoinRoute
import com.coin.ui.navigation.CoinTopLevelDestination
import com.coin.ui.navigation.LayoutType
import com.coin.ui.navigation.TOP_LEVEL_DESTINATIONS
import com.coin.ui.navigation.navigationMeasurePolicy
import com.coin.ui.utils.CoinNavigationContentPosition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDrawerContent(
    selectedDestination: String,
    navigationContentPosition: CoinNavigationContentPosition,
    navigateToTopLevelDestination: (CoinTopLevelDestination) -> Unit,
    navigatePageDestination: (String) -> Unit,
    onDrawerClicked: () -> Unit = {}
) {
    ModalDrawerSheet {
        // TODO remove custom nav drawer content positioning when NavDrawer component supports it. ticket : b/232495216
        Layout(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                .padding(16.dp), content = {
                //头像
                Column(
                    modifier = Modifier.layoutId(LayoutType.HEADER),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                }

                //选项
                Column(
                    modifier = Modifier
                        .layoutId(LayoutType.CONTENT)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                }
            }, measurePolicy = navigationMeasurePolicy(navigationContentPosition)
        )
    }
}