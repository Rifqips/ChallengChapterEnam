package com.rifqipadisiliwangi.challengchapterenam.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.rifqipadisiliwangi.challengchapterenam.R
import com.rifqipadisiliwangi.challengchapterenam.databinding.FragmentRegisterBinding
import com.rifqipadisiliwangi.challengchapterenam.viewmodel.UserViewModel

class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    lateinit var userViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        val itEmail = binding.etEmail.inputType
        val passwordInput = binding.etPassword.inputType
        val conPassInput = binding.etConfimPas.inputType

        binding.btnSignup.setOnClickListener {
            if (passwordInput.toString() == conPassInput.toString()){
                userViewModel.editData(
                    itEmail.toString(),
                    passwordInput.toString(),
                    conPassInput.toString(),
                    "false"
                )
                Toast.makeText(
                    requireActivity(), context?.getString(R.string.success_reg), Toast.LENGTH_SHORT
                ).show()
                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
            }
            else {
                Toast.makeText(
                    requireActivity(),
                    context?.getString(R.string.pass_tidak_sesuai),
                    Toast.LENGTH_SHORT
                )
                    .show()
             }
        }
        binding.logIn.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}