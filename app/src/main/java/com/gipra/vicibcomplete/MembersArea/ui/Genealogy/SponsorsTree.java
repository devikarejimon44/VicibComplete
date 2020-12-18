package com.gipra.vicibcomplete.MembersArea.ui.Genealogy;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.gipra.vicibcomplete.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SponsorsTree extends Fragment {
    LinearLayout new_entry_layout,new_entry_main,new_entry_text;
    TextView entry_level,entry_under,entry_side,up_username,entry_under_layout,up_username_layout,new_entry_under;
    LinearLayout new_rightentry_text,new_right_entry_layout;
    TextView up_right_username,new_right_entry_under,up_right_username_layout,entry_right_under_layout;
    RelativeLayout rl_right_entry,rl_left_entry;

    public SponsorsTree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_sponsors_tree, container, false);





        return view;
    }

}
