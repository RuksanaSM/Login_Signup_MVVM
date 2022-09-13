package com.example.login_signup_mvvm.views;

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

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.login_signup_mvvm.R;
import com.example.login_signup_mvvm.viewmodels.AuthViewModel;
import com.google.firebase.auth.FirebaseUser;

public class SignoutFragment extends Fragment {
    private EditText emailEdit, passEdit,fnameEdit,lastnameEdit;
    private TextView signInText;
    private Button signUpBtn;
    private AuthViewModel viewModel;
    private NavController navController;

    public SignoutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this , (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        viewModel.getUserData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null){
                    navController.navigate(R.id.action_signoutFragment_to_signinFragment);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fnameEdit = view.findViewById(R.id.fnameid);
        lastnameEdit = view.findViewById(R.id.lnameid);
        emailEdit=view.findViewById(R.id.mailsignup);
        passEdit=view.findViewById(R.id.passwordsignup);
        signInText = view.findViewById(R.id.signupsignintextid);
        signUpBtn = view.findViewById(R.id.signupbtnid);

        navController = Navigation.findNavController(view);

        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_signoutFragment_to_signinFragment);
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdit.getText().toString();
                String pass = passEdit.getText().toString();

                if (!email.isEmpty() && !pass.isEmpty()){
                    viewModel.register(email , pass);
                }
            }
        });
    }
//

    /*@Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {

            Fragment fragment;
            fragment = new SigninFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.nav_host_fragment, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;

        }
        });
    }*/

}