package uz.abdurashidov.notesapp.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.abdurashidov.notesapp.data.local.model.Note
import uz.abdurashidov.notesapp.domain.usecases.DeleteNoteUseCase
import uz.abdurashidov.notesapp.domain.usecases.GetAllNotesUseCase
import uz.abdurashidov.notesapp.domain.usecases.UpdateNoteUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {
}

data class HomeState(
    val notes: List<Note>
)