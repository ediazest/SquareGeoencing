package com.applanticstudio.squarefencing.data.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface EventDAO {

    @Query("SELECT * FROM event")
    fun getAll(): LiveData<List<Event>>

    @Insert()
    fun insert(event: Event)

    @Query("DELETE FROM event")
    fun removeAll()
}