package ru.askar.appatlantteam;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Сайида on 24.10.2017.
 */

public class SwitchFragment extends Fragment {
    //Button btnBack, btnNext;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_switchscreen, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //btnBack = (Button) getView().findViewById(R.id.btnBack);
        //btnNext = (Button) getView().findViewById(R.id.btnNext);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
