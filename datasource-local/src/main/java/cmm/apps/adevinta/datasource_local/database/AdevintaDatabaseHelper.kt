package cmm.apps.adevinta.datasource_local.database

import android.content.Context
import androidx.room.Room


object AdevintaDatabaseHelper {

    private const val DATABASE_NAME = "adevinta_database"
    const val DATABASE_VERSION = 1

    fun getDatabase(context: Context) =
        Room.databaseBuilder(context, AdevintaDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

}