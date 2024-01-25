package uz.abdurashidov.notesapp.data.repository

import kotlinx.coroutines.flow.Flow
import uz.abdurashidov.notesapp.data.local.NotesDao
import uz.abdurashidov.notesapp.data.local.model.Note
import uz.abdurashidov.notesapp.domain.repository.Repository
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
) : Repository {
    override fun getAllNotes(): Flow<List<Note>> {
        return notesDao.getAllNotes()
    }

    override fun getNoteById(id: Long): Flow<Note> {
        return notesDao.getNoteById(id)
    }

    override suspend fun insert(note: Note) {
        notesDao.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        notesDao.updateNote(note)
    }

    override suspend fun deleteNote(id: Long) {
        notesDao.deleteNote(id)
    }

    override fun getBookmarkedNote(): Flow<List<Note>> {
        return notesDao.getBookmarkedNotes()
    }
}