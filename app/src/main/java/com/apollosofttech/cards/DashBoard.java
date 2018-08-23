package com.apollosofttech.cards;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apollosofttech.cards.apihelper.ApiHelper;
import com.apollosofttech.cards.apihelper.RetrofitInstance;
import com.apollosofttech.cards.survices.AppStatus;
import com.apollosofttech.cards.survices.ProgressLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoard extends Activity {
    private final String LOG = "DashBoard";
    private int Balance = 0;
    private int bootAmount;
    //    private String usEr1, usEr2, usEr3, usEr4, usEr5;
    private ProgressLoader pd;
    //    private String checkTableUrl, errorcod, match_table_id;
    private Context context;
    //    private JSONArray jsonarray;
//    private JSONObject jsonobject;
    private TextView baLance, userName;
    private String balance, username;
    private int MaxTableAmount;
    public static final int BootArray[][] = {

            //{ 2, 4, 256, 4096 }, { 4, 4, 512, 8192 }, { 8, 4, 1024, 10240 },
            {10, 4, 1280, 20480},
            {20, 4, 2560, 40960}, //{ 40, 4, 5120, 81920 },
            {50, 4, 6400, 102400}, //{ 80, 4, 10240, 163840 },
            {100, 4, 12800, 204800},
            {200, 4, 25600, 409600}, //{ 400, 4, 51200, 819200 },
            {500, 4, 6400, 1024000}, //{ 800, 4, 102400, 1638400 },
            {1000, 4, 128000, 2048000},
            {2000, 4, 256000, 4096000}};
    ApiHelper apiHelper = RetrofitInstance.getRetrofitInstance().create(ApiHelper.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        context = this;
        Log.d(LOG,"OnCreate");
        baLance = findViewById(R.id.user_chips);
        userName = findViewById(R.id.user_name);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            balance = extras.getString("user_bal");
            username = extras.getString("user_Teen");
            baLance.setText(balance);
            userName.setText(username);
        } else {
            finish();
        }

        pd = new ProgressLoader();
        findViewById(R.id.normalPlay).setOnClickListener(handler);
        findViewById(R.id.privatePlay).setOnClickListener(handler);
        findViewById(R.id.selectTable).setOnClickListener(handler);
    }

    private View.OnClickListener handler = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.normalPlay:
                    pd.displayProgressDialog(false, "Checking Avalable Table...", context);
                    if (AppStatus.getInstance(context).isOnline(context)) {
                        if (Balance > 10240) {
                            int i = 0;
                            while (Balance * 2 > BootArray[i][3]) {
                                MaxTableAmount = BootArray[i][3];
                                i++;
                            }
                            i = i - 1;
                            bootAmount = BootArray[i][0];
                        } else if (Balance > 0) {
                            MaxTableAmount = BootArray[0][3];
                            bootAmount = BootArray[0][0];
                        } else {

                        }

//                        ApiHelper apiHelper = RetrofitInstance.getRetrofitInstance().create(ApiHelper.class);
                        Log.d(LOG, "get_Table : " + username + " : " + bootAmount);
                        Call<String> getTableCall = apiHelper.get_table(username, bootAmount);
                        getTableCall.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                pd.dismiss();
                                if (response.code() == 200) {
                                    Log.d(LOG, response.body());
                                    try {
                                        JSONObject jsonobject = new JSONObject(response.body());
                                        JSONArray jsonarray = jsonobject.getJSONArray("feed");
                                        jsonobject = jsonarray.getJSONObject(0);
                                        String errorcod = null;
                                        if (jsonobject.has("errorcod")) {
                                            errorcod = jsonobject.getString("errorcod");
                                        }
                                        if (errorcod != null && errorcod.equals("0")) {
                                            String match_table_id = null;
                                            if (jsonobject.has("tbl_id")) {
                                                match_table_id = jsonobject.getString("tbl_id");
                                            }
                                            /*if (jsonobject.has("Player1")) {
                                                usEr1 = jsonobject.getString("Player1");
                                            }
                                            if (jsonobject.has("Player2")) {
                                                usEr2 = jsonobject.getString("Player2");
                                            }
                                            if (jsonobject.has("Player3")) {
                                                usEr3 = jsonobject.getString("Player3");
                                            }
                                            if (jsonobject.has("Player4")) {
                                                usEr4 = jsonobject.getString("Player4");
                                            }
                                            if (jsonobject.has("Player5")) {
                                                usEr5 = jsonobject.getString("Player5");
                                            }*/
                                            Intent intent = new Intent(DashBoard.this, MainActivity.class);
                                            intent.putExtra("tbl_id", match_table_id);
                                            intent.putExtra("user_Teen", username);
                                            intent.putExtra("boot_amount", bootAmount);
                                            startActivity(intent);
                                        } else {
                                            showToast("Can't find Table");
                                        }
                                    } catch (JSONException e) {
                                        showToast("Invalid Responce");
                                        e.printStackTrace();
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
                        showToast("No Internet Connection!");
                    }
                    break;
                case R.id.privatePlay:
                    if (AppStatus.getInstance(context).isOnline(context)) {
                        Intent intenta = new Intent(DashBoard.this, PrivateTable.class);
                        intenta.putExtra("user_Teen", username);
                        startActivity(intenta);
                    } else {
                        showToast("No Internet Connection!");
                    }
                    break;
                case R.id.selectTable:
                    if (AppStatus.getInstance(context).isOnline(context)) {
                        Intent intenta = new Intent(DashBoard.this, SelectTable.class);
                        intenta.putExtra("user_Teen", username);
                        intenta.putExtra("user_bal", Balance);
                        startActivity(intenta);
                    } else {
                        showToast("No Internet Connection!");
                    }
                    break;

            }
        }
    };

    protected void onResume() {
        super.onResume();
        if (AppStatus.getInstance(context).isOnline(context)) {
            pd.displayProgressDialog(false, "Checking Balance", context);
            Log.d(LOG, "get_Table : " + username + " : " + bootAmount);
            Call<String> getTableCall = apiHelper.balance(username);
            getTableCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    pd.dismiss();
                    if (response.code() == 200) {
                        Log.d(LOG, response.body());
                        try {
                            JSONObject jsonobject = new JSONObject(response.body());
                            JSONArray jsonarray = jsonobject.getJSONArray("balance_jason");
                            jsonobject = jsonarray.getJSONObject(0);
                            if (jsonobject.has("balance")) {
                                Balance = jsonobject.getInt("balance");
                                baLance.setText(""+Balance);
                            } else {
                                Balance = 0;
                                showToast("Invalid Balance");
                            }
                        } catch (JSONException e) {
                            showToast("Invalid Responce");
                            e.printStackTrace();
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
            showToast("No Internet Connection!");
        }

    }

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