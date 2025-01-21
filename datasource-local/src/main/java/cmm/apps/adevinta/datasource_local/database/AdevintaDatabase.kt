package cmm.apps.adevinta.datasource_local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cmm.apps.adevinta.datasource_local.database.dao.UserDao
import cmm.apps.adevinta.datasource_local.user.model.UserLocalModel

@Database(
    version = AdevintaDatabaseHelper.DATABASE_VERSION,
    entities = [
        UserLocalModel::class
    ],
    autoMigrations = [
        //AutoMigration(from = 1, to = 2)
    ]
)

@TypeConverters(ZonedDateTimeConverter::class)
abstract class AdevintaDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}