package uz.abdurashidov.notesapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.abdurashidov.notesapp.data.local.model.Note

@Dao
interface NotesDao {
    @Query("select  * from notes order by createdDate")
    fun getAllNotes(): Flow<List<Note>>

    @Query("select  * from notes where id=:id order by createdDate")
    fun getNoteById(id: Long): Flow<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Query("delete from notes where id=:id")
    suspend fun deleteNote(id:Long)

    @Query("select * from notes where isBookmarked=1 order by createdDate desc")
    fun getBookmarkedNotes():Flow<List<Note>>
}