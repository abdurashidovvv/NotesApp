package uz.abdurashidov.notesapp.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.abdurashidov.notesapp.data.local.model.Note

interface Repository {
    fun getAllNotes(): Flow<List<Note>>
    fun getNoteById(id: Long): Flow<Note>
    suspend fun insert(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(id: Long)
    fun getBookmarkedNote(): Flow<List<Note>>
}