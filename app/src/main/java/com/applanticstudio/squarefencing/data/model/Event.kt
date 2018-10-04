package com.applanticstudio.squarefencing.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Event(@PrimaryKey val identifier: String, val timestamp: Date, val exiting: Boolean,
                 val regionId: String, val point: Point, val user: String)