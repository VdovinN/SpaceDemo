package com.vdovin.spacedemo.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vdovin.spacedemo.framework.database.dao.SpaceDao
import com.vdovin.spacedemo.framework.database.model.SpaceEntity

@Database(entities = [SpaceEntity::class], version = 1, exportSchema = false)
abstract class SpaceDatabase : RoomDatabase() {
    abstract fun spaceDao(): SpaceDao
}