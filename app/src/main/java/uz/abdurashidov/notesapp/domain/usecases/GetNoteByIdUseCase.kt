package uz.abdurashidov.notesapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import uz.abdurashidov.notesapp.data.local.model.Note
import uz.abdurashidov.notesapp.domain.repository.Repository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(id: Long): Flow<Note> = repository.getNoteById(id)
}