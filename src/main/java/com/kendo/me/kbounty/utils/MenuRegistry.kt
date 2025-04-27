package com.kendo.me.kbounty.utils

import java.util.UUID

object MenuRegistry {
    val activeMenus: MutableMap<UUID, PaginatedMenu<*>> = mutableMapOf()
}