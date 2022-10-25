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
import com.rifqipadisiliwangi.challengchapterenam.databinding.FragmentLoginBinding
import com.rifqipadisiliwangi.challengchapterenam.viewmodel.UserViewModel

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var userViewModel : UserViewModel
    lateinit var username : String
    lateinit var email : String
    lateinit var password : String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)

        userViewModel.dataUser.observe(requireActivity()) {
//            email = it.
            username = it.username
            email = it.email
            password = it.password
        }

        binding.singUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {
            val itEmail = binding.etEmail.text.toString()
            val passwordInput = binding.etPassword.text.toString()
            if (itEmail!=""&&passwordInput!=""){
                if(itEmail == email && passwordInput == password){
                    userViewModel.editSession("true")
                    Toast.makeText(requireActivity(), context?.getString(R.string.success_login), Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireActivity(), context?.getString(R.string.success_login), Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }

            }
            else{
                Toast.makeText(requireActivity(), context?.getString(R.string.empty_login_input), Toast.LENGTH_SHORT).show()
            }
        }

    }
}