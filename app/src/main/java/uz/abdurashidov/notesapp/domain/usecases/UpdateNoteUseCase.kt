package uz.abdurashidov.notesapp.domain.usecases

import uz.abdurashidov.notesapp.data.local.model.Note
import uz.abdurashidov.notesapp.domain.repository.Repository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(note: Note) = repository.updateNote(note)
}