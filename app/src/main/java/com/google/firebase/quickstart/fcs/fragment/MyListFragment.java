package com.google.firebase.quickstart.fcs.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.quickstart.fcs.Launsher;
import com.google.firebase.quickstart.fcs.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyListFragment extends Fragment {


    public MyListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_list, container, false);
    }

    // find view by id and context
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        final ListView lv = (ListView) getView().findViewById(R.id.fragLv);
//        String []names = getResources().getStringArray(R.array.names);
//        ArrayAdapter adapter = new ArrayAdapter(getContext(),android.R.layout.simple_expandable_list_item_1,names);
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Activity activity = getActivity();
//                String item = lv.getItemAtPosition(position).toString();
//                Launsher temp = (Launsher) activity;
//                temp.itemReciver(item);
//                Toast.makeText(getContext(),lv.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }

}
