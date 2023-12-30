package kz.nkaiyrken.notesapp2023.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kz.nkaiyrken.notesapp2023.db.room.dao.NoteRoomDao
import kz.nkaiyrken.notesapp2023.db.room.model.NoteDBModel
import kz.nkaiyrken.notesapp2023.utils.Constants

@Database(entities = [NoteDBModel::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getRoomDao(): NoteRoomDao

    companion object {

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            return if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    name = Constants.Keys.NOTES_DATABASE
                ).build()
                INSTANCE as AppRoomDatabase
            } else INSTANCE as AppRoomDatabase
        }
    }
}