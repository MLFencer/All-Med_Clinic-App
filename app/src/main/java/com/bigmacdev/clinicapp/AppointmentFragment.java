package com.bigmacdev.clinicapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //recyclerView.setAdapter(new MyAppointmentRecyclerViewAdapter(appointments, mListener));
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

    public void updateList(int day, int month, int year, String path){
        //Todo: get schedule from location
        final String p = path;
        final int d = day;
        final int m = month;
        final int y = year;
        new Thread(){
            @Override
            public void run() {
                JsonObjectBuilder job = Json.createObjectBuilder();
                job.add("day",d);
                job.add("month",m);
                job.add("year",y);
                job.add("path",p);
                JsonObject jo = job.build();
                Client client = new Client();
                String out = client.runRequest("scheduleGet:"+client.encryptData(jo.toString()));
                if(out.equals("false")){
                    appointments = new ArrayList<Appointment>();
                    done = false;
                }else {
                    out = client.decryptData(out);
                    appointments = new ArrayList<Appointment>();
                    JsonObject j = Json.createReader(new StringReader(out)).readObject();
                    int i=0;
                    while(true){
                        if(j.containsKey("app"+i)){
                            JsonObject j2 = j.getJsonObject("app"+i);
                            appointments.add(new Appointment(j2.getString("first"), j2.getString("last"), j2.getString("reason"), j2.getInt("hour"), j2.getInt("minute"), j2.getInt("day"), j2.getInt("month"), j2.getInt("year")));
                        }else{
                            break;
                        }
                        i++;
                    }
                }
            }
        }.start();
        while (done==null){}
        recyclerView.setAdapter(new MyAppointmentRecyclerViewAdapter(appointments, mListener));
    }

    public void setPractice(Practice p){
        this.practice=p;
    }
}
