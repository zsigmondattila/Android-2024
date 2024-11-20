package com.example.recipehub.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.recipehub.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnPersonalInfo).setOnClickListener {
            navigateToPersonalInfo()
        }

        view.findViewById<Button>(R.id.btnMyRecipes).setOnClickListener {
            navigateToMyRecipes()
        }

        view.findViewById<Button>(R.id.btnSettings).setOnClickListener {
            navigateToSettings()
        }
    }

    private fun navigateToPersonalInfo() {
        findNavController().navigate(R.id.action_navigation_profile_to_navigation_personal_info)
    }

    private fun navigateToSettings() {
        findNavController().navigate(R.id.action_navigation_profile_to_navigation_settings)
    }

    private fun navigateToMyRecipes() {
        findNavController().navigate(R.id.action_navigation_profile_to_navigation_my_recipes)
    }
}