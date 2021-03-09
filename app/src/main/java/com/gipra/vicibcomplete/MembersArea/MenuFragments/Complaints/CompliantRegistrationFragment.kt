package com.gipra.vicibcomplete.MembersArea.MenuFragments.Complaints

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.gipra.vicibcomplete.MembersArea.MainActivity
import com.gipra.vicibcomplete.R
import com.gipra.vicibshoppy.activity.ShoppyHome

class CompliantRegistrationFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_dash_board, container, false)
        val toolbar: Toolbar = view.findViewById(R.id.historyToolBar)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_shoppy)

        toolbar.setNavigationOnClickListener { onBackPressed() }


        return view
    }

    private fun onBackPressed() {

//        val intent = Intent(activity, MainActivity::class.java)
//        startActivity(intent)
//        activity!!.finish()

        val intent=Intent(activity,MainActivity::class.java)
        startActivity(intent)


    }


}