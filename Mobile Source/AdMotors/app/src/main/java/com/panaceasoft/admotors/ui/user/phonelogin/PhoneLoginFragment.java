package com.panaceasoft.admotors.ui.user.phonelogin;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.google.firebase.auth.FirebaseAuth;
import com.panaceasoft.admotors.MainActivity;
import com.panaceasoft.admotors.R;
import com.panaceasoft.admotors.binding.FragmentDataBindingComponent;
import com.panaceasoft.admotors.databinding.FragmentPhoneLoginBinding;
import com.panaceasoft.admotors.ui.common.PSFragment;
import com.panaceasoft.admotors.utils.AutoClearedValue;
import com.panaceasoft.admotors.utils.PSDialogMsg;
import com.panaceasoft.admotors.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneLoginFragment extends PSFragment {


    //region Variables

    private final androidx.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private PSDialogMsg psDialogMsg;

    @VisibleForTesting
    private AutoClearedValue<FragmentPhoneLoginBinding> binding;

    public AutoClearedValue<ProgressDialog> prgDialog;

    private CallbackManager callbackManager;

    private String number, userName;

    //Firebase test
    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    //endregion


    //region Override Methods

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);
        FacebookSdk.sdkInitialize(getContext());

        callbackManager = CallbackManager.Factory.create();

        // Inflate the layout for this fragment
        FragmentPhoneLoginBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_phone_login, container, false, dataBindingComponent);

        binding = new AutoClearedValue<>(this, dataBinding);

        return binding.get().getRoot();
    }


    //firebase
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.addAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void initUIAndActions() {

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setToolbarText(((MainActivity) getActivity()).binding.toolbar, getString(R.string.login__login));
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
            ((MainActivity) this.getActivity()).binding.toolbar.setBackgroundColor(getResources().getColor(R.color.global__primary));
            ((MainActivity) getActivity()).updateToolbarIconColor(Color.WHITE);
            ((MainActivity) getActivity()).updateMenuIconWhite();
            ((MainActivity) getActivity()).refreshPSCount();
        }

        psDialogMsg = new PSDialogMsg(getActivity(), false);

        // Init Dialog
        prgDialog = new AutoClearedValue<>(this, new ProgressDialog(getActivity()));
        //prgDialog.get().setMessage(getString(R.string.message__please_wait));

        prgDialog.get().setMessage((Utils.getSpannableString(getContext(), getString(R.string.message__please_wait), Utils.Fonts.MM_FONT)));
        prgDialog.get().setCancelable(false);

        //fadeIn Animation
        fadeIn(binding.get().getRoot());

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = firebaseAuth -> {

        };
        //end

        binding.get().backToLoginTextView.setOnClickListener(v ->
                Utils.navigateAfterLogin(PhoneLoginFragment.this.getActivity(), navigationController));

        binding.get().addPhoneButton.setOnClickListener(v -> {
            if ( binding.get().nameEditText.getText().toString().trim().isEmpty()) {

                psDialogMsg.showWarningDialog(getString(R.string.error_message__blank_name), getString(R.string.app__ok));
                psDialogMsg.show();

            }else if(binding.get().phoneEditText.getText().toString().trim().isEmpty()){
                psDialogMsg.showWarningDialog(getString(R.string.error_message__blank_phone_no), getString(R.string.app__ok));
                psDialogMsg.show();
            }
            else {
                number = binding.get().phoneEditText.getText().toString();
                userName = binding.get().nameEditText.getText().toString();
                PhoneLoginFragment.this.validNo(number);

                Utils.navigateAfterPhoneVerify(getActivity(),navigationController,number,userName);

                Toast.makeText(PhoneLoginFragment.this.getActivity(), number, Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    protected void initViewModels() {

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }

    private void validNo(String no) {
        if (no.isEmpty() || no.length() < 10) {
            binding.get().phoneEditText.setError("Enter a valid mobile");
            binding.get().phoneEditText.requestFocus();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
}
//endregion