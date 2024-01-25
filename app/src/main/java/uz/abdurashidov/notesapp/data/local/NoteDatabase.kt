package uz.abdurashidov.notesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import uz.abdurashidov.notesapp.data.local.converters.DateConverter
import uz.abdurashidov.notesapp.data.local.model.Note

@TypeConverters(value = [DateConverter::class])
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract val notesDao: NotesDao
}