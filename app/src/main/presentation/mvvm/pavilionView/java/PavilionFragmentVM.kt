package PavilionViiew
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PavilionFragmentVM : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Pavilion Fragment"
    }
    val text: LiveData<String> = _text
}