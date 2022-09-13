package com.example.login_signup_mvvm.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.login_signup_mvvm.R;
import com.example.login_signup_mvvm.viewmodels.AuthViewModel;
import com.google.firebase.auth.FirebaseUser;

public class SigninFragment extends Fragment {


    private EditText emailEdit, passEdit;
    private TextView signUpText;
    private Button signInBtn;
    private AuthViewModel viewModel;
    private NavController navController;

    public SigninFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this , (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        viewModel.getUserData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null){
                    navController.navigate(R.id.action_signinFragment_to_profileFragment);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailEdit = view.findViewById(R.id.mailsigin);
        passEdit = view.findViewById(R.id.passwordsignin);
        signUpText = view.findViewById(R.id.signinsignuptext);
        signInBtn = view.findViewById(R.id.signinbtnid);

        navController = Navigation.findNavController(view);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_signinFragment_to_signoutFragment);
            }
        });


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdit.getText().toString();
                String pass = passEdit.getText().toString();

                if (!email.isEmpty() && !pass.isEmpty()){
                    viewModel.signIn(email , pass);
                }
            }
        });
    }
}