package uz.abdurashidov.notesapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import uz.abdurashidov.notesapp.data.local.model.Note
import uz.abdurashidov.notesapp.domain.repository.Repository
import javax.inject.Inject

class BookmarkedNoteUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Note>> = repository.getBookmarkedNote()
}