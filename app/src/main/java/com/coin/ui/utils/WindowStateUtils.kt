package com.coin.ui.utils

import android.graphics.Rect
import kotlin.contracts.ExperimentalContracts

import kotlin.contracts.contract


/**
 * 应用支持的不同类型的导航，具体取决于设备大小和状态。
 */
enum class CoinNavigationType {
    BOTTOM_NAVIGATION, NAVIGATION_RAIL, PERMANENT_NAVIGATION_DRAWER
}

/**
 * 导航栏、导航抽屉内导航内容的不同位置取决于设备大小和状态。
 */
enum class CoinNavigationContentPosition {
    TOP, CENTER
}
