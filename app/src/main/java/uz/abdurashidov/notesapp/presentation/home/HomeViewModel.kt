package uz.abdurashidov.notesapp.presentation.home

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.abdurashidov.notesapp.common.ScreenViewState
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

    private val _satate: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _satate.asStateFlow()

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        getAllNotesUseCase()
            .onEach {
                _satate.value = HomeState(notes = ScreenViewState.Success(it))
            }
            .catch {
                _satate.value = HomeState(notes = ScreenViewState.Error(it.message))
            }
            .launchIn(viewModelScope)
    }

    private fun deleteNote(noteId: Long) = viewModelScope.launch {
        deleteNoteUseCase(noteId)
    }

    private fun onBookmarkChange(note: Note) {
        viewModelScope.launch {
            updateNoteUseCase(note.copy(isBookmarked = !note.isBookmarked))
        }
    }
}

data class HomeState(
    val notes: ScreenViewState<List<Note>> = ScreenViewState.Loading,
)