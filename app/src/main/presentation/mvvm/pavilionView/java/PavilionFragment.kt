package PavilionViiew

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.taipeizoo.databinding.FragmentHomeBinding

class PavilionFragment : Fragment(), LifecycleOwner {

    private lateinit var pavilionFragmentVM: PavilionFragmentVM
    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pavilionFragmentVM =
            ViewModelProvider(this).get(PavilionFragmentVM::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.pavilionRecycleView.init(this)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}