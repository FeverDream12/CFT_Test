package com.example.cft_test.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cft_test.R
import com.example.cft_test.databinding.FragmentSignInBinding
import com.example.cft_test.MAIN
import com.example.cft_test.USERNAME

class SignInFragment : Fragment() {

    lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputCheck()

        binding.signUpButton.setOnClickListener{

            if(passwordCheck() && nameCheck()){
                USERNAME = binding.firstNameEditTextLayout.text.toString() + " " + binding.secondNameEditTextLayout.text.toString()
                MAIN.navController.navigate(R.id.action_signInFragment_to_homeFragment)
            }

        }
    }

    private fun inputCheck(){
        binding.signUpButton.isEnabled = false
        binding.firstNameEditTextLayout.addTextChangedListener(textWatcher)
        binding.secondNameEditTextLayout.addTextChangedListener(textWatcher)
        binding.passwordTextInputEditText.addTextChangedListener(textWatcher)
        binding.passwordReInputEditText.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object: TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val firstName = binding.firstNameEditTextLayout.text.toString()
            val secondName = binding.secondNameEditTextLayout.text.toString()
            val password = binding.passwordTextInputEditText.text.toString()
            val passwordReInput = binding.passwordReInputEditText.text.toString()

            binding.signUpButton.isEnabled =
                !(firstName == "" || secondName == "" ||password == "" ||passwordReInput == "")

        }
    }

    private fun nameCheck(): Boolean{
        val firstName = binding.firstNameEditTextLayout.text.toString()
        val secondName = binding.secondNameEditTextLayout.text.toString()

        if(firstName.length < 2 || secondName.length < 2){
            Toast.makeText(requireContext(),"Имя и фамилия не могут содержать менее двух символов",Toast.LENGTH_SHORT).show()
            return false
        }else{
            return true
        }
    }

    private fun passwordCheck(): Boolean{

        val password = binding.passwordTextInputEditText.text.toString()
        val passwordReInput = binding.passwordReInputEditText.text.toString()

        if(password != passwordReInput){
            Toast.makeText(requireContext(),"Пароли не совпадают",Toast.LENGTH_SHORT).show()
            return false
        }else if(password.length < 6){
            Toast.makeText(requireContext(),"Пароль слишком короткий",Toast.LENGTH_SHORT).show()
            return false
        }else if(password.contains("[A-Z]".toRegex()) && password.contains("[0-9]".toRegex()) && password.contains("[a-z]".toRegex())){
            return true
        }else{
            Toast.makeText(requireContext(),"Пароль должен содержать цифры, а так же символы верхнего и нижнего регистра",Toast.LENGTH_SHORT).show()
            return false
        }

    }
}