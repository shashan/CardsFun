package com.apollosofttech.cards;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apollosofttech.cards.apihelper.ApiHelper;
import com.apollosofttech.cards.apihelper.RetrofitInstance;
import com.apollosofttech.cards.survices.AppStatus;
import com.apollosofttech.cards.survices.ProgressLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends Activity {
    private final String LOG = "Login";
    private String androidId;
    private String username;

    private String password;
    private Context context;
    private ProgressLoader pd;
    private EditText userEditText;
    private EditText userPassText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pd = new ProgressLoader();
        userEditText = findViewById(R.id.userEdit);
        userPassText = findViewById(R.id.passEdit);
        androidId = Settings.Secure.getString(getContentResolver(), "android_id");
        context = this;
        findViewById(R.id.login).setOnClickListener(handler);
    }
    private View.OnClickListener handler = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.login:
                    username = userEditText.getText().toString();
                    password = userPassText.getText().toString();
                    if (AppStatus.getInstance(context).isOnline(context)) {
                        if (!username.equals("") && !password.equals("")) {
                            ApiHelper apiHelper = RetrofitInstance.getRetrofitInstance().create(ApiHelper.class);
                            Call<String> loginCall = apiHelper.login(androidId, username, password);
                            Log.d(LOG, "U : " + username + " P : " + password + " aid : " + androidId);
                            pd.displayProgressDialog(false, "Login in...", context);
                            loginCall.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    pd.dismiss();
                                    if (response.code() == 200) {
                                        Log.d(LOG, response.body());
                                        if (response.body().trim().equals("1")) {
                                            showToast("You are not Register With This Device");
                                        } else if (response.body().trim().equals("2")) {
                                            showToast("Username or password is incorrect");
                                        } else {
                                            android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                                            String s = response.body().split(":")[1];
                                            editor.putString("user_Teen", username);
                                            editor.putString("user_bal", s);
                                            editor.commit();
                                            Intent intent = new Intent(Login.this, DashBoard.class);
                                            intent.putExtra("user_Teen", username);
                                            intent.putExtra("user_bal", s);
                                            startActivity(intent);
                                            finish();
                                        }

                                    } else {
                                        showToast("Can't find server");
                                    }
                                }
                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    pd.dismiss();
                                    showToast("Network Error");
                                }
                            });
                        } else {
                            showToast("Please Enter Username/Password");
                        }
                    } else {
                        showToast("No Internet Connection!");
                    }
                    break;
            }
        }
    };

    public void showToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View toastRoot = inflater.inflate(R.layout.notification_toast,
                null);
        Toast toast = new Toast(context);
        TextView tc = toastRoot.findViewById(R.id.notifi);
        tc.setText(message);
        toast.setView(toastRoot);
        toast.setGravity(Gravity.CENTER_HORIZONTAL
                | Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }
}