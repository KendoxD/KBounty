package com.kendo.me.kbounty.database.model

import java.util.UUID

data class HuntModel(
    val target: UUID,
    val hunter: UUID,
    val startedAt: Long = System.currentTimeMillis(),
)