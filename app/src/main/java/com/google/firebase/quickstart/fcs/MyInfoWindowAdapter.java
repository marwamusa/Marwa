package com.google.firebase.quickstart.fcs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.io.File;

/**
 * Created by acer on 8/23/2017.
 */

public class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    Context context;
//    final SQLiteDatabase db = ProgramTabs.HELPER.getReadableDatabase();
//    final Cursor cursor =db.rawQuery("select * from victim ",null);

    public MyInfoWindowAdapter(Context context)
    {
        this.context=context;
    }
    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = LayoutInflater.from(context).inflate(R.layout.case_layout,null,false);

        TextView name = (TextView)view.findViewById(R.id.caseNameTv);
        ImageView iv = (ImageView) view.findViewById(R.id.casePhotoIV);

//        Victim victim = (Victim) marker.getTag();
//                    name.setText(victim.getName());


        name.setText("Company #1");

        //Toast.makeText(context,""+victim.getPhoto(),Toast.LENGTH_LONG).show();
                    //File imgFile = new File(victim.getPhoto());
//
//                    if(imgFile.exists()) {
//
//                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                        iv.setImageBitmap(myBitmap);
//                    }

        iv.setImageResource(R.drawable.ic_launcher_background);
        return view;
    }
}
