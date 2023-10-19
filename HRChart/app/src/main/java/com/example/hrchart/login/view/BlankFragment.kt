package com.example.hrchart.login.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.hrchart.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {

    companion object {
        /** TAG */
        private const val TAG = "LoginFragment"
    }

    private  lateinit var binding : FragmentBlankBinding
    private lateinit var viewModel: BlankViewModel

    // データが格納されているargsを取得
    private val args:BlankFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreateView Start")
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BlankViewModel::class.java)
        Log.d(TAG, "$args")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlankBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}