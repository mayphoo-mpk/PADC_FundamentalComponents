package mayphoo.mpk.sfc.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import mayphoo.mpk.sfc.R;
import mayphoo.mpk.sfc.delegates.LoginRegisterDelegate;
import mayphoo.mpk.sfc.fragments.LoginFragment;
import mayphoo.mpk.sfc.fragments.RegisterFragment;

/**
 * Created by User on 12/1/2017.
 */

public class LoginRegisterActivity extends BaseActivity implements LoginRegisterDelegate {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, LoginRegisterActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        /*
         * when device rotate, destroy activity and recreate activity,
         * when recreate activity, direct restore activity state in savedInstanceState

         * savedInstanceState = null, activity is initialize
         * savedInstanceState != null, after device rotate, recreate activity
         */
        if(savedInstanceState == null){
            //show first login fragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, LoginFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onTapLogin() {

    }

    @Override
    public void onTapForgotPassword() {

    }

    @Override
    public void onTapToRegister() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.fl_container, RegisterFragment.newInstance())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void setScreenTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(title);
        }
    }
}
