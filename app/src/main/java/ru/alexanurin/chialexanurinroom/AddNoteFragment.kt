package ru.alexanurin.chialexanurinroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import ru.alexanurin.chialexanurinroom.database.Notes
import ru.alexanurin.chialexanurinroom.databinding.FragmentAddNoteBinding

@KoinApiExtension
class AddNoteFragment : Fragment() {

    //  Создание ViewModel через dependency injection.
    private val addNoteViewModel: AddNoteViewModel by viewModel() //  DI

    //  ViewBinding.
    private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  Проверка на наличие названия заметки. Если пусто, то показать тост и не уходить с фрагмента.
        binding.button.setOnClickListener {
            if (binding.editText.text.toString().trim().isNotEmpty()) {
                navigateBack()
            } else {
                Toast.makeText(context, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun navigateBack() {
        val note = Notes(0, "")
        note.note = binding.editText.text.toString()
        addNoteViewModel.insertNote(note)

        findNavController().popBackStack()
    }
}
