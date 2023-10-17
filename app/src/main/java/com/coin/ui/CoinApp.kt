import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.coin.ui.account.AccountDrawerContent
import com.coin.ui.navigation.CoinBottomNavigationBar
import com.coin.ui.navigation.CoinNavigationActions
import com.coin.ui.navigation.CoinNavigationRail
import com.coin.ui.navigation.CoinRoute
import com.coin.ui.navigation.isTopNavigator
import com.coin.ui.utils.CoinNavigationContentPosition
import com.coin.ui.utils.CoinNavigationType
import kotlinx.coroutines.launch

@Composable
fun CoinApp(
    windowSize: WindowSizeClass,
) {
    //导航类型
    val navigationType = when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> CoinNavigationType.BOTTOM_NAVIGATION
        WindowWidthSizeClass.Medium,
        WindowWidthSizeClass.Expanded -> CoinNavigationType.NAVIGATION_RAIL

        else -> CoinNavigationType.BOTTOM_NAVIGATION
    }

    // 导航项内容位置
    val navigationContentPosition = when (windowSize.heightSizeClass) {
        WindowHeightSizeClass.Compact -> CoinNavigationContentPosition.TOP
        WindowHeightSizeClass.Medium, WindowHeightSizeClass.Expanded -> CoinNavigationContentPosition.CENTER

        else -> CoinNavigationContentPosition.TOP
    }

    CoinNavigationWrapper(
        navigationType = navigationType, navigationContentPosition = navigationContentPosition
    )
}


//导航包装器
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CoinNavigationWrapper(
    navigationType: CoinNavigationType,
    navigationContentPosition: CoinNavigationContentPosition,
) {
    //抽屉状态
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        CoinNavigationActions(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination = navBackStackEntry?.destination?.route ?: CoinRoute.HOME

    //侧边导航栏
    if (navigationType == CoinNavigationType.NAVIGATION_RAIL) {
        Row(modifier = Modifier.fillMaxSize()) {
            //侧边栏
            CoinNavigationRail(
                selectedDestination = selectedDestination,
                navigationContentPosition = navigationContentPosition,
                navigateToTopLevelDestination = navigationActions::navigateTo,
                navigatePageDestination = navigationActions::navigateTo,
            ) {
                scope.launch {
                    drawerState.open()
                }
            }
            CoinAppContent(
                navController = navController,
                modifier = Modifier.weight(1f),
            )
        }
    } else {
        ModalNavigationDrawer(
            drawerContent = {
                //移动端抽屉栏
                AccountDrawerContent(
                    selectedDestination = selectedDestination,
                    navigationContentPosition = navigationContentPosition,
                    navigateToTopLevelDestination = navigationActions::navigateTo,
                    navigatePageDestination = navigationActions::navigateTo,
                    onDrawerClicked = {
                        scope.launch {
                            drawerState.close()
                        }
                    })
            }, drawerState = drawerState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.inverseOnSurface),
                verticalArrangement = Arrangement.Bottom
            ) {
                CoinAppContent(
                    navController = navController,
                    modifier = Modifier.weight(1f),
                )
                //底部导肮内容
                AnimatedVisibility(
                    visible = (navigationType == CoinNavigationType.BOTTOM_NAVIGATION) && isTopNavigator(
                        navController.currentDestination?.route
                    )
                ) {
                    CoinBottomNavigationBar(
                        selectedDestination = selectedDestination,
                        navigateToTopLevelDestination = navigationActions::navigateTo,
                    )
                }
            }
        }
    }
}


//app内容
@Composable
fun CoinAppContent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = CoinRoute.HOME,
    ) {
        composable(CoinRoute.HOME) {
            Text(text = "主页")
        }
        composable(CoinRoute.MARKET) {
            Text(text = "市场")
        }
        composable(CoinRoute.ASSET) {
            Text(text = "财产")
        }
        composable(CoinRoute.Account) {
            Text(text = "用户信息")
        }
    }
}