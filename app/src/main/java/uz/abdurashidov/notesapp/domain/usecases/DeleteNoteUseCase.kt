package uz.abdurashidov.notesapp.domain.usecases

import uz.abdurashidov.notesapp.domain.repository.Repository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Long) = repository.deleteNote(id)
}