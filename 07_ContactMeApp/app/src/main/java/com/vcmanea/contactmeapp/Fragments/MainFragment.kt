package com.vcmanea.contactmeapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.vcmanea.contactmeapp.R
import kotlinx.android.synthetic.main.fragment_main_page.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_main_page, container, false)
	}


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnCotact.setOnClickListener{

            view.findNavController().navigate(R.id.action_mainPage_to_contactFragment)

        }

        btnAbout.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainPage_to_aboutFragment)

        }


    }

}
