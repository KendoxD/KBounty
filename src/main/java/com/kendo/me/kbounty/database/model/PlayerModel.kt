package com.kendo.me.kbounty.database.model

import java.util.UUID

data class PlayerModel(
    val uuid : UUID,
    val bounty : Int,
)