package com.apollosofttech.cards;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.apollosofttech.cards.app.Constans;
import com.apollosofttech.cards.survices.AppStatus;
import com.apollosofttech.cards.survices.JSONfunctions;
import com.apollosofttech.cards.survices.ProgressLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PrivateTable extends Activity {
    private final String LOG = "PrivateTable";
    Context context;
    String userName, tableId, totalUser;
    int bOotAmount;
    TextView user_has_name, table_boot_val, req_no_player;
    ProgressLoader pd;
    JSONArray jsonarray;
    JSONObject jsonobject;
    Button canclePrivateBtn, acceptPrivateBtn;
    String errorcod, usEr1, usEr2, usEr3, usEr4, usEr5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_private_table);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        user_has_name = findViewById(R.id.user_has_name);
        table_boot_val = findViewById(R.id.table_boot_val);
        table_boot_val.setVisibility(View.INVISIBLE);
        req_no_player = findViewById(R.id.req_no_player);
        req_no_player.setVisibility(View.INVISIBLE);
        canclePrivateBtn = findViewById(R.id.canclePrivateBtn);
        canclePrivateBtn.setVisibility(View.INVISIBLE);
        acceptPrivateBtn = findViewById(R.id.acceptPrivateBtn);
        acceptPrivateBtn.setVisibility(View.INVISIBLE);
        context = this;
        pd = new ProgressLoader();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userName = extras.getString("user_Teen");
            user_has_name.setText(userName);
        }

        findViewById(R.id.canclePrivateBtn).setOnClickListener(handler);
        findViewById(R.id.acceptPrivateBtn).setOnClickListener(handler);

    }

    private View.OnClickListener handler = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.canclePrivateBtn:

                    finish();
                    break;
                case R.id.acceptPrivateBtn:
                    if (AppStatus.getInstance(context).isOnline(context)) {
                        new GoToPrivateTable().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                    } else {
                        showToast("No Internet Connection!");
                    }

                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (AppStatus.getInstance(context).isOnline(context)) {
            new SeePrivateTables().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            showToast("No Internet Connection!");
        }

    }

    private class SeePrivateTables extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd.displayProgressDialog(false, "Checking Private Table", context);
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Retrieve JSON Objects from the given URL address
            try {
                jsonobject = JSONfunctions.getJSONfromURL(Constans.displayPrivateTable + userName);


                // Locate the array name in JSON
                jsonarray = jsonobject.getJSONArray("info");
                jsonobject = jsonarray.getJSONObject(0);
                if (jsonobject.has("errorcod")) {
                    errorcod = jsonobject.getString("errorcod");
                    if (errorcod.equals("0")) {
                        if (jsonobject.has("tbl_id")) {
                            tableId = jsonobject.getString("tbl_id");
                        }
                        if (jsonobject.has("TotalUser")) {
                            totalUser = jsonobject.getString("TotalUser");
                        }
                        if (jsonobject.has("Boot_amount")) {
                            bOotAmount = jsonobject.getInt("Boot_amount");
                        }
                    }
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            pd.dismiss();
            //Toast.makeText(context, errorcod, Toast.LENGTH_SHORT).show();
            if (errorcod.equals("0")) {
                table_boot_val.setVisibility(View.VISIBLE);
                table_boot_val.setText("BootAmount : " + bOotAmount);
                req_no_player.setVisibility(View.VISIBLE);
                req_no_player.setText("Total Player : " + totalUser);
                canclePrivateBtn.setVisibility(View.VISIBLE);
                acceptPrivateBtn.setVisibility(View.VISIBLE);
            } else {
                table_boot_val.setVisibility(View.VISIBLE);
                table_boot_val.setText("No Private table for you");
                req_no_player.setVisibility(View.INVISIBLE);
                canclePrivateBtn.setVisibility(View.VISIBLE);
                acceptPrivateBtn.setVisibility(View.INVISIBLE);
            }
        }
    }

    private class GoToPrivateTable extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd.displayProgressDialog(false, "Getting Table", context);
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Retrieve JSON Objects from the given URL address
            try {
                jsonobject = JSONfunctions.getJSONfromURL(Constans.getPrivateTable + userName);


                jsonarray = jsonobject.getJSONArray("feed");
                jsonobject = jsonarray.getJSONObject(0);
                if (jsonobject.has("errorcod")) {
                    errorcod = jsonobject.getString("errorcod");
                }
                if (errorcod.equals("0")) {

                    if (jsonobject.has("tbl_id")) {
                        tableId = jsonobject.getString("tbl_id");
                    }
                    if (jsonobject.has("Player1")) {
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
                    }

                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            pd.dismiss();
            if (errorcod.equals("0")) {
                Intent intent = new Intent(PrivateTable.this, MainActivity.class);
                intent.putExtra("tbl_id", tableId);
                intent.putExtra("user_Teen", userName);
                intent.putExtra("boot_amount", bOotAmount);
                startActivity(intent);
                finish();
            }
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
