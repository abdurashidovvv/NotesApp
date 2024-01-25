package uz.abdurashidov.notesapp.presentation.bookmark

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.abdurashidov.notesapp.common.ScreenViewState
import uz.abdurashidov.notesapp.data.local.model.Note
import uz.abdurashidov.notesapp.presentation.home.HomeScreen
import uz.abdurashidov.notesapp.presentation.home.HomeState
import uz.abdurashidov.notesapp.presentation.home.NoteCard
import uz.abdurashidov.notesapp.presentation.home.notes

@Composable
fun BookMarkScreen(
    state: BookmarkState,
    modifier: Modifier = Modifier,
    onBookmarkChange: (note: Note) -> Unit,
    onDelete: (noteId: Long) -> Unit,
    onNoteClick: (Long) -> Unit
) {
    when (state.notes) {
        is ScreenViewState.Error -> {
            Text(
                text = state.notes.message ?: "Unknown error",
                color = MaterialTheme.colorScheme.error
            )
        }

        ScreenViewState.Loading -> {
            CircularProgressIndicator()
        }

        is ScreenViewState.Success -> {
            val notes = state.notes.data
            LazyColumn(contentPadding = PaddingValues(4.dp), modifier = modifier) {
                itemsIndexed(notes) { index, note ->
                    NoteCard(
                        note = note,
                        index = index,
                        onBookmarkChange = onBookmarkChange,
                        onDelete = onDelete,
                        onNoteClick = onNoteClick
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun BookMarkScreenPrev() {
    BookMarkScreen(
        state = BookmarkState(notes = ScreenViewState.Success(notes)),
        onBookmarkChange = {},
        onDelete = {},
        onNoteClick = {}
    )
}