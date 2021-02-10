package com.gipra.vicibcomplete.MembersArea.Gene;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.gipra.vicibcomplete.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PremiumGeneBottomDailogue extends BottomSheetDialogFragment {



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.premium_genepopup,
                container, false);

        return view;
    }


}

