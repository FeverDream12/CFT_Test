package com.example.cft_test.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.cft_test.R
import com.example.cft_test.USERNAME
import com.example.cft_test.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.helloButton.setOnClickListener {
            val dialog = Dialog(requireContext())
            val dialogBinding = layoutInflater.inflate(R.layout.hello_dialog,null)
            val usernameText = dialogBinding.findViewById<TextView>(R.id.userNameText)
            usernameText.text = USERNAME

            dialog.setContentView(dialogBinding)
            dialog.setCancelable(true)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            val dialogOkButton = dialogBinding.findViewById<Button>(R.id.okButton)

            dialogOkButton.setOnClickListener{
                dialog.dismiss()
            }
        }
    }
}