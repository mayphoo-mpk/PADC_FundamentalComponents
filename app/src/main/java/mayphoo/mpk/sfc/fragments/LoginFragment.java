package mayphoo.mpk.sfc.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import mayphoo.mpk.sfc.R;
import mayphoo.mpk.sfc.delegates.LoginRegisterDelegate;

/**
 * Created by User on 12/1/2017.
 */

public class LoginFragment extends BaseFragment {

    private LoginRegisterDelegate mLoginRegisterDelegate;

    public static LoginFragment newInstance(){
        LoginFragment loginFragment = new LoginFragment();
        return loginFragment;
    }

    //context => attach host activity(LoginRegisterActivity implements LoginRegisterDelegate)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLoginRegisterDelegate = (LoginRegisterDelegate) context;
    }

    /*
      * LayoutInflater -> inflate layout xml that connect with Fragment
      * container -> when inflate layout file, output is View object,
      *                 use as parent object container(ViewGroup object)
      * savedInstanceState -> when device rotates, destroy and recreate,
      *                 want to restore states will store in this Bundle object
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View loginView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, loginView);
        return loginView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mLoginRegisterDelegate.setScreenTitle("Login");
    }

    @OnClick(R.id.btn_to_register)
    public void onTapToRegister(View view){
        mLoginRegisterDelegate.onTapToRegister();
    }

    @Override
    public void checkNetworkConnectivity() {
        super.checkNetworkConnectivity();
        //check
    }
}
