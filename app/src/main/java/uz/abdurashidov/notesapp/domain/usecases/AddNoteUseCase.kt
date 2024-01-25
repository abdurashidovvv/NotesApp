package uz.abdurashidov.notesapp.domain.usecases

import uz.abdurashidov.notesapp.data.local.model.Note
import uz.abdurashidov.notesapp.domain.repository.Repository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(note: Note) = repository.insert(note)
}