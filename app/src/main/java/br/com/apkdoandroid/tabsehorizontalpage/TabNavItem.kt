package br.com.apkdoandroid.tabsehorizontalpage

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.apkdoandroid.tabsehorizontalpage.R;

sealed class TabNavItem(
    val type : TabNavType,
    val badge : Int,
    @StringRes val title : Int,
    @DrawableRes val selectedIcon : Int,
    @DrawableRes val unSelectedIcon : Int
){

    object Home : TabNavItem(
        type = TabNavType.HOME,
        badge = 0,
        title = R.string.label_home_navigation_drawer_item,
        selectedIcon = R.drawable.ic_home_fill,
        unSelectedIcon = R.drawable.ic_home_line
    )

    object Search : TabNavItem(
        type = TabNavType.SEARCH,
        badge = 0,
        title = R.string.label_search_navigation_drawer_item,
        selectedIcon = R.drawable.ic_search_fill,
        unSelectedIcon = R.drawable.ic_search_line
    )

    object Notification : TabNavItem(
        type = TabNavType.NOTIFICATION,
        badge = 10,
        title = R.string.label_notifications_navigation_drawer_item,
        selectedIcon = R.drawable.ic_notification_fill,
        unSelectedIcon = R.drawable.ic_notification_line
    )




    enum class TabNavType {
        HOME,
        SEARCH,
        NOTIFICATION
    }

    companion object{
        val items = listOf(
            Home,
            Search,
            Notification
        )
    }
    }