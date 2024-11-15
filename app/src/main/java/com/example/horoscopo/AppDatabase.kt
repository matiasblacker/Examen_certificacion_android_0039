package com.example.horoscopo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [SignoModel::class, SignoDetalle::class], version = 1)
@TypeConverters(Converters::class)
abstract class SignosDatabase : RoomDatabase() {
    abstract fun signosDao(): SignosDao

    companion object {
        @Volatile
        private var INSTANCE: SignosDatabase? = null

        fun getInstance(context: Context): SignosDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SignosDatabase::class.java,
                    "signos_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}