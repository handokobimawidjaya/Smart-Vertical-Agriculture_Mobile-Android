package com.example.hans.agrigo.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hans.agrigo.Mapping.LineDistance;
import com.example.hans.agrigo.MenuLogin.Login;
import com.example.hans.agrigo.MenuTesting.TampilData;
import com.example.hans.agrigo.R;
import com.example.hans.agrigo.TestPolyline.Polyline;

public class AccountFragment extends Fragment {
    public static String KEY_ACTIVITY = "msg_activity";
    public static String KEY_ACTIVITY1 = "msg_activity1";
    public static String KEY_ACTIVITY2 = "msg_activity2";

    public final static String TAG_EMAIL = "email";
    public final static String TAG_NAME = "name";
    public final static String TAG_NAME2 = "name2";
    SharedPreferences sharedPreferences;
    public static final String session_status = "session_status";

    String email, name, name2;
    TextView txtEmail;
    TextView txtUsername;
    Button btnLogout, btnTentang, btnSetupMap, btnTampil;
//    Button btnEdit;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        txtEmail = (TextView) view.findViewById(R.id.txt_email);
        txtUsername = (TextView) view.findViewById(R.id.txt_name);
        btnLogout = (Button) view.findViewById(R.id.btn_logout);
//        btnEdit = (Button) view.findViewById(R.id.btn_editProfile);
        btnSetupMap = (Button) view.findViewById(R.id.btn_map);
        btnTentang = (Button) view.findViewById(R.id.btn_tentang);
        btnTampil = (Button) view.findViewById(R.id.btn_tampil);

        try {
            String email = getArguments().getString(KEY_ACTIVITY);
            String name = getArguments().getString(KEY_ACTIVITY1);
            String name2 = getArguments().getString(KEY_ACTIVITY2);
            if (email != null) {
                txtEmail.setText(email);
                txtUsername.setText(name +" "+ name2);
            } else {

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();

            }
        });
//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                editProfile();
//            }
//        });
        btnSetupMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupMap();
            }
        });
        btnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilData();
            }
        });
        btnTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tentang();
            }
        });
        return view;

    }

    private void tentang() {
        Intent tentang = new Intent( getActivity(), Polyline.class );
        startActivity( tentang );
        getActivity().finish();
    }


//    public void editProfile()
//    {
//        Intent edit = new Intent(getActivity(), EditProfile.class);
//        startActivity(edit);
//        getActivity().finish();
//    }

    public void setupMap()
    {
        Intent map = new Intent(getActivity(), LineDistance.class);
        startActivity(map);
        getActivity().finish();
    }

    public void tampilData()
    {
        Intent tampil = new Intent(getActivity(), TampilData.class);
        startActivity(tampil);
        getActivity().finish();
    }

    private void logout() {
        sharedPreferences = getActivity().getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(session_status, false);
        editor.putString(TAG_EMAIL, null);
        editor.putString(TAG_NAME, null );
        editor.putString(TAG_NAME2, null );
        editor.commit();

        Intent a = new Intent(getActivity(), Login.class);
        startActivity(a);
        getActivity().finish();
    }
}