package com.google.firebase.quickstart.fcs;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Marker marker=null;
    LatLng pos=null;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getWindow().setStatusBarColor(getResources().getColor(R.color.pink));
        getSupportActionBar().show();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Go To Gaza
        LatLng position = new LatLng(31.3547,34.3088);
        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(position,8);
        MarkerOptions mo = new MarkerOptions();
        mo.title("Gaza Land");
        mo.position(position);
        mo.draggable(true);
        mMap.moveCamera(cu);
        marker = mMap.addMarker(mo);

        // Clinics
        LatLng sydney = new LatLng(-34, 151);
        LatLng markzAlremal = new LatLng(31.515245, 34.451280);
        LatLng khaledAlHasan = new LatLng(31.389838, 34.564198);
        LatLng donia = new LatLng(31.905979, 35.206217);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));//.draggable(true)
        mMap.addMarker(new MarkerOptions().position(markzAlremal).title("مركز الرمال الصحي- وحدة التصویر الشعاعي الرقمي للثدي"));
        mMap.addMarker(new MarkerOptions().position(khaledAlHasan).title("مركز خالد الحسن للسرطان"));
        mMap.addMarker(new MarkerOptions().position(donia).title("دنيا- المركز التخصصي لأورام النساء"));

        // Doctors
        LatLng ahmadAlshurafa = new LatLng(31.505739, 34.430751);
        LatLng nayfKasbary = new LatLng(32.224008, 35.242358);
        mMap.addMarker(new MarkerOptions().position(ahmadAlshurafa).title("د. أحمد الشرفا- رئيس قسم الأورام في مستشفى غزة الأوروبي"));
        mMap.addMarker(new MarkerOptions().position(nayfKasbary).title("د. نايف كسبري- اختصاصي الأورام"));

        // Financial Support
        LatLng amira = new LatLng(23.512433, 53.596085);
        LatLng patientFriend = new LatLng(25.339014, 55.443937);
        mMap.addMarker(new MarkerOptions().position(amira).title("«صندوق أميرة» لدعم علاج مرضى السرطان حول العالم"));
        mMap.addMarker(new MarkerOptions().position(patientFriend).title("جمعية أصدقاء مرضى السرطان"));

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

                pos=marker.getPosition();
                Intent i=new Intent();
                i.putExtra("lat",pos.latitude);
                i.putExtra("lan",pos.longitude);

//        AddStatus c=new AddStatus();
//        //n get feom addsstatus
//        String n=i.getStringExtra("name");
//        c.name=n;
//        marker.setTag(c);
                setResult(777,i);

            }
        });

        MyInfoWindowAdapter adapter=new MyInfoWindowAdapter(getApplicationContext());
        mMap.setInfoWindowAdapter(adapter);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //Toast.makeText(getApplicationContext(),pos.latitude+","+pos.longitude, Toast.LENGTH_LONG).show();
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Woman w=(Woman)marker.getTag();
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.google.com"));
                startActivity(i);
                return false;
            }
        });
    }

    //when click in any marker in map



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_menue,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch(item.getItemId())
        {
            case R.id.hybrid: //Log.d("map","Hybrid");
                mapType(GoogleMap.MAP_TYPE_HYBRID); break;

            case R.id.normal: Log.d("map","normal");
                mapType(GoogleMap.MAP_TYPE_NORMAL); break;

            case R.id.terrain: Log.d("map","terrain");
                mapType(GoogleMap.MAP_TYPE_TERRAIN); break;

            case R.id.satellite: Log.d("map","satellite");
                mapType(GoogleMap.MAP_TYPE_SATELLITE);break;

            case R.id.zoomIn: Log.d("map","zoomIn");
                CameraUpdate update1 = CameraUpdateFactory.zoomIn();
                mMap.moveCamera(update1);
                break;
            case R.id.zoomOut: Log.d("map","zoomOut");
                CameraUpdate update2 = CameraUpdateFactory.zoomOut();
                mMap.moveCamera(update2);
                break;
            case R.id.goToGaza: Log.d("map","goToGaza");
                LatLng position = new LatLng(31.3547,34.3088);
                CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(position,8);
                MarkerOptions mo = new MarkerOptions();
                mo.title("Gaza Land");
                mo.position(position);
                mo.draggable(true);
                mMap.moveCamera(cu);
                marker = mMap.addMarker(mo);
                break;
            /*case R.id.goToLondon: Log.d("map","goToLondon");
                CameraPosition.Builder builder = new CameraPosition.Builder();
                builder.zoom(10);
                builder.target(new LatLng(51.5074,0.1278));
                builder.tilt(45);
                CameraPosition cp = builder.build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp),8000,null);
                break;
            case R.id.drawPath: Log.d("map","drawPath");
                PolylineOptions o = new PolylineOptions();
                o.color(Color.RED);
                o.width(10);
                o.add(new LatLng(31.3547,34.3088));
                o.add(new LatLng(30.0444,31.2357));
                o.add(new LatLng(31.2001,29.9187));
                mMap.addPolyline(o);
                //Toast.makeText(getApplicationContext(),pos.latitude+","+pos.longitude,Toast.LENGTH_LONG).show();
                break;*/
            case R.id.mylocationmarker: Log.d("map","mylocationmarker");
                break;
            case R.id.search: Log.d("map","بحث");
                break;
            case R.id.clinics: Log.d("map","العيادات التخصصية");
                break;
            case R.id.doctors: Log.d("map","أطباء");
                break;
            case R.id.financial_support: Log.d("map","المؤسسات الداعمة");
                break;
            case R.id.donners: Log.d("map","أشخاص داعمين");
                break;



        }
        return super.onOptionsItemSelected(item);
    }
    public void mapType(int m)
    {
        if(mMap!=null)
        {
            mMap.setMapType(m);
        }

    }
}

