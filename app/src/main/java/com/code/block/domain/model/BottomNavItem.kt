package com.code.block.domain.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.code.block.R
import com.code.block.presentation.destinations.* // ktlint-disable no-wildcard-imports

enum class BottomNavItem(
    val route: DirectionDestination,
    val icon: ImageVector? = null,
    @StringRes val contentDescription: Int,
    val alertCount: Int? = null
) {
    MainFeed(
        route = MainFeedScreenDestination,
        icon = Icons.Outlined.Home,
        contentDescription = R.string.home
    ),
    Chat(
        route = ChatScreenDestination,
        icon = Icons.Outlined.Message,
        contentDescription = R.string.message,
        alertCount = 100
    ),
    Empty(
        route = SplashScreenDestination,
        contentDescription = R.string.empty_nav_item
    ),
    Activity(
        route = ActivityScreenDestination,
        icon = Icons.Outlined.Notifications,
        contentDescription = R.string.activity,
        alertCount = 100
    ),
    Profile(
        route = ProfileScreenDestination,
        icon = Icons.Outlined.Person,
        contentDescription = R.string.profile
    )
}
