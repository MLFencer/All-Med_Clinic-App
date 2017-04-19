package com.bigmacdev.clinicapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import net.maritimecloud.internal.core.javax.json.Json;
import net.maritimecloud.internal.core.javax.json.JsonObject;
import net.maritimecloud.internal.core.javax.json.JsonObjectBuilder;

import java.io.StringReader;
import java.util.ArrayList;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class AppointmentFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private RecyclerView recyclerView;

    private Practice practice;
    private ArrayList<Appointment> appointments;
    private Boolean done = null;
    private MyAppointmentRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AppointmentFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AppointmentFragment newInstance(int columnCount) {
        AppointmentFragment fragment = new AppointmentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointment_list, container, false);

        appointments = new ArrayList<Appointment>();

        appointments.add(new Appointment("last", "first", "NA", 12, 12, 03, 12, 2017));
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
             adapter = new MyAppointmentRecyclerViewAdapter(appointments, mListener);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Appointment item);
    }

    public void updateList(final int day, int month, int year, String path){
        final String p = path;
        final int d = day;
        final int mo = month;
        final int ye = year;
        new Thread(){
            @Override
            public void run() {
                JsonObjectBuilder job = Json.createObjectBuilder();
                job.add("day",d);
                job.add("month",mo);
                job.add("year",ye);
                job.add("path",p);
                JsonObject jo = job.build();
                Client client = new Client();
                Log.d("AppFrag", "Request: "+jo.toString());
                String out = client.runRequest("scheduleGet:"+client.encryptData(jo.toString()));
                if(out.equals("false")){
                    appointments = new ArrayList<Appointment>();
                    done = false;
                }else {
                    //out = client.decryptData(out);
                    appointments = new ArrayList<Appointment>();
                    JsonObject j = Json.createReader(new StringReader(out)).readObject();
                    int i=0;
                    while(true){
                        if(j.containsKey("file"+i)){
                            String whole =  j.getString("file"+i);
                            Log.d("AppFrag", "Whole: "+whole);
                            int x = whole.indexOf("_");
                            String h = whole.substring(0,x);
                            whole = whole.substring(x+1,whole.length());
                            x = whole.indexOf("_");
                            String m = whole.substring(0,x);
                            whole = whole.substring(x+1,whole.length());
                            x = whole.indexOf("_");
                            String l = whole.substring(0,x);
                            whole = whole.substring(x+1,whole.length());
                            String f = whole.substring(0, whole.length()-4);
                            Appointment a = new Appointment(f, l, "Yearly Physical", Integer.parseInt(h), Integer.parseInt(m), d, mo, ye);
                            a.setLocation(j.getString("file"+i));
                            appointments.add(a);
                            Log.d("AppFrag",""+appointments.size());
                        }else{
                            Log.d("AppFrag", "Break");
                            break;
                        }
                        i++;
                        Log.d("AppFrag", ""+i);
                    }
                }
                Log.d("AppFrag", "done");
                done=true;
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        updateAppList(appointments);
                        Log.d("AppFrag", " Set Adapter");
                    }
                });

            }
        }.start();
        //while (done==null){Log.d("AppFrag", "running");}
        //

    }

    private void updateAppList(ArrayList<Appointment> apps){
       adapter.updateAppsList(apps);
    }

    public void setPractice(Practice p){
        this.practice=p;
    }
}
