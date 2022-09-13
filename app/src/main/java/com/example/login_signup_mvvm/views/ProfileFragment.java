package com.example.login_signup_mvvm.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.login_signup_mvvm.R;
import com.example.login_signup_mvvm.viewmodels.AuthViewModel;

public class ProfileFragment extends Fragment {

  private AuthViewModel viewModel;
  private NavController navController;
  private Button signoutbtn;
private TextView fnametext,lnametext,emailtext;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      viewModel = new ViewModelProvider(this , (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
              .getInstance(getActivity().getApplication())).get(AuthViewModel.class);
      viewModel.getLoggedStatus().observe(this, new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean aBoolean) {
          if (aBoolean){
            navController.navigate(R.id.action_profileFragment_to_signinFragment);
          }
        }
      });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

//    fnametext=view.findViewById(R.id.displayfname);
//    lnametext=view.findViewById(R.id.displaylname);
//    emailtext=view.findViewById(R.id.displaymail);
    signoutbtn=view.findViewById(R.id.profilesignoutbtn);

    navController = Navigation.findNavController(view);


    signoutbtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        viewModel.signOut();
      }
    });
  }
  }
