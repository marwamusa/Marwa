package com.google.firebase.quickstart.fcs;

import android.content.Intent;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlacingMarker extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Marker marker=null;
    public static LatLng pos=null;


    double oldLat;
    double oldLng;

    double markerLat;
    double markerLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getSupportActionBar().show();

        Intent i = getIntent();
        oldLat = i.getDoubleExtra("user_latitude",0.0);
        //Float.parseFloat(string.replaceAll(",", ""));
        oldLng = i.getDoubleExtra("user_longitude",0.0);

        markerLat= 31.3547;
        markerLng = 34.3088;
        //Toast.makeText(getApplicationContext(),"Old Lat Lang\n"+oldLat+", \n"+oldLng,Toast.LENGTH_LONG).show();

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


        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.addMarker(new MarkerOptions().position(new LatLng(31.3547,34.3088)).title("Marker in Gaza").draggable(true));
        /*CameraPosition.Builder builder = new CameraPosition.Builder();
        builder.zoom(3);
        builder.target(new LatLng(31.3547,34.3088));
        builder.tilt(45);
        CameraPosition cp = builder.build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp),1000,null);*/

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(31.3547,34.3088)));
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                pos = marker.getPosition();
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
                //marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.calendar));
                //Toast.makeText(getApplicationContext(),"Marker Draged  "+pos.latitude+","+pos.longitude,Toast.LENGTH_LONG).show();

                LatLng markerLocation = marker.getPosition();

                Intent sendIntent = new Intent(getApplicationContext(),SignInActivity.class);
                markerLat = markerLocation.latitude;
                markerLng = markerLocation.longitude;
                Toast.makeText(getApplicationContext(),"تمت قراءة الاحداثيات بنجاح \n للرجوع اختر حفظ من الخيارات بالأعلى", Toast.LENGTH_LONG).show();
                //Toast.makeText(PlacingMarker.this, "Sent suc "+markerLocation.latitude+" , "+markerLocation.longitude, Toast.LENGTH_LONG).show();
                //sendIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);

                //setResult(25,sendIntent);
                //finish();
                //startActivity(sendIntent);


            }
        });
        /*mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Login c = (Login)marker.getTag();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.putExtra("id",c.get_id());
                i.setData(Uri.parse("http://www.google.com"));
                startActivity(i);
                //if(marker.getTitle().equals())
                return false;
            }
        });*/
        MyInfoWindowAdapter adapter = new MyInfoWindowAdapter(getApplicationContext());
        mMap.setInfoWindowAdapter(adapter);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                //open new window and show details
            }
        });
    }

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
            /*case R.id.goToGaza: Log.d("map","goToGaza");
                LatLng position = new LatLng(31.3547,34.3088);
                CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(position,8);
                MarkerOptions mo = new MarkerOptions();
                mo.title("Gaza Land");
                mo.position(position);
                mo.draggable(true);
                mMap.moveCamera(cu);
                marker = mMap.addMarker(mo);
                break;
            case R.id.goToLondon: Log.d("map","goToLondon");
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
            case R.id.search:
//                Intent i = new Intent(getApplicationContext(),SignInActivity.class);
//                i.putExtra("new_latitude",  markerLat);//Double.parseDouble(
//                i.putExtra("new_longitude", markerLng);
//
//                //Toast.makeText(PlacingMarker.this, "Sent suc "+markerLocation.latitude+" , "+markerLocation.longitude, Toast.LENGTH_LONG).show();
//                //sendIntent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
//
//                setResult(25,i);
//                finish();
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
