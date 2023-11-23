package com.app.daeja.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.daeja.Activity.LoginActivity;
import com.app.daeja.R;

public class SettingsFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_settings, container, false);

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Edit Profile 버튼에 대한 클릭 이벤트 설정
        ImageView editProfileImageView = view.findViewById(R.id.imageView4);
        editProfileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // AlertDialog.Builder를 사용하여 다이얼로그 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // LayoutInflater를 사용하여 XML 레이아웃을 불러와서 AlertDialog에 설정
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_settings_change_profile, null);
                builder.setView(dialogView);

                // 다이얼로그에 대한 추가적인 설정 가능
                // 예를 들어, 버튼 클릭 리스너, 버튼 등의 설정

                // AlertDialog 생성
                AlertDialog dialog = builder.create();

                // 다이얼로그 표시
                dialog.show();

                // 다이얼로그 끄기 버튼 등을 클릭했을 때 다이얼로그를 닫기 위한 코드
                Button closeButton = dialogView.findViewById(R.id.applyButton);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 다이얼로그를 닫음
                        dialog.dismiss();
                    }
                });

            }
        });

        // Location 버튼에 대한 클릭 이벤트 설정

        ImageView notifiactionImageView = view.findViewById(R.id.imageView1);
        notifiactionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // AlertDialog.Builder를 사용하여 다이얼로그 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // LayoutInflater를 사용하여 XML 레이아웃을 불러와서 AlertDialog에 설정
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.notification_dialog, null);
                builder.setView(dialogView);

                // AlertDialog 생성
                AlertDialog dialog = builder.create();

                // 다이얼로그 표시
                dialog.show();
            }
        });


        // Location 버튼에 대한 클릭 이벤트 설정
        ImageView locationImageView = view.findViewById(R.id.imageVie42342);
        locationImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                // Settings_Location 액티비티로 이동하는 코드
//                Intent locationIntent = new Intent(getActivity(), Settings_Location.class);
//                startActivity(locationIntent);
                // AlertDialog.Builder를 사용하여 다이얼로그 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // LayoutInflater를 사용하여 XML 레이아웃을 불러와서 AlertDialog에 설정
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.location_dialog, null);
                builder.setView(dialogView);

                // AlertDialog 생성
                AlertDialog dialog = builder.create();

                // 다이얼로그 표시
                dialog.show();

                // 다이얼로그 끄기 버튼 등을 클릭했을 때 다이얼로그를 닫기 위한 코드
                Button closeButton = dialogView.findViewById(R.id.closeButton1);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 다이얼로그를 닫음
                        dialog.dismiss();
                    }
                });

            }
        });

        // Support 버튼에 대한 클릭 이벤트 설정
        ImageView supportImageView = view.findViewById(R.id.imageVie2532);
        supportImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AlertDialog.Builder를 사용하여 다이얼로그 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // LayoutInflater를 사용하여 XML 레이아웃을 불러와서 AlertDialog에 설정
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_settings_support, null);
                builder.setView(dialogView);

                // 다이얼로그에 대한 추가적인 설정 가능
                // 예를 들어, 버튼 클릭 리스너, 버튼 등의 설정

                // AlertDialog 생성
                AlertDialog dialog = builder.create();

                // 다이얼로그 표시
                dialog.show();

                // 다이얼로그 끄기 버튼 등을 클릭했을 때 다이얼로그를 닫기 위한 코드
                Button closeButton = dialogView.findViewById(R.id.closeButton);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 다이얼로그를 닫음
                        dialog.dismiss();
                    }
                });
            }
        });


        // Share 버튼에 대한 클릭 이벤트 설정
        ImageView shareImageView = view.findViewById(R.id.imageVie2679);
        shareImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AlertDialog.Builder를 사용하여 다이얼로그 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // LayoutInflater를 사용하여 XML 레이아웃을 불러와서 AlertDialog에 설정
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.share_dialog, null);
                builder.setView(dialogView);

                // 다이얼로그에 대한 추가적인 설정 가능
                // 예를 들어, 버튼 클릭 리스너, 버튼 등의 설정

                // AlertDialog 생성
                AlertDialog dialog = builder.create();

                // 다이얼로그 표시
                dialog.show();

                // 다이얼로그 끄기 버튼 등을 클릭했을 때 다이얼로그를 닫기 위한 코드
                Button closeButton = dialogView.findViewById(R.id.shareButton);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 다이얼로그를 닫음
                        dialog.dismiss();
                    }
                });
            }
        });

        // 로그아웃 버튼에 대한 클릭 이벤트 설정
        ImageView loginButton = view.findViewById(R.id.imageVie2);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그아웃 버튼 클릭 시 LoginActivity로 이동하는 코드
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(loginIntent);
                // 현재 액티비티를 종료 (선택적으로)
                getActivity().finish();
            }
        });



    }
}
