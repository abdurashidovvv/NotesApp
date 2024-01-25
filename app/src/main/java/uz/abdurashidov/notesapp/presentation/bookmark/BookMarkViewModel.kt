package uz.abdurashidov.notesapp.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.abdurashidov.notesapp.common.ScreenViewState
import uz.abdurashidov.notesapp.data.local.model.Note
import uz.abdurashidov.notesapp.domain.usecases.BookmarkedNoteUseCase
import uz.abdurashidov.notesapp.domain.usecases.DeleteNoteUseCase
import uz.abdurashidov.notesapp.domain.usecases.UpdateNoteUseCase
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val bookmarkedNoteUseCase: BookmarkedNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<BookmarkState> = MutableStateFlow(BookmarkState())
    val state = _state.asStateFlow()

    private fun getBookmarkedNotes() {
        bookmarkedNoteUseCase().onEach {
            _state.value = BookmarkState(notes = ScreenViewState.Success(it))
        }
            .catch {
                _state.value = BookmarkState(ScreenViewState.Error(it.message))
            }
            .launchIn(viewModelScope)
    }

    fun onBookmarkChage(note: Note) {
        viewModelScope.launch {
            updateNoteUseCase(note.copy(isBookmarked = !note.isBookmarked))
        }
    }

    fun deleteNote(noteId: Long) {
        viewModelScope.launch {
            deleteNoteUseCase(noteId)
        }
    }
}

data class BookmarkState(
    val notes: ScreenViewState<List<Note>> = ScreenViewState.Loading,

    )