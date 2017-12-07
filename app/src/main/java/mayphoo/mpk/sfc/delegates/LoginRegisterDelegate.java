package mayphoo.mpk.sfc.delegates;

/**
 * Created by User on 12/1/2017.
 */

public interface LoginRegisterDelegate {

    void onTapLogin();
    void onTapForgotPassword();
    void onTapToRegister();

    void setScreenTitle(String title);
}
