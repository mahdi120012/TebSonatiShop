package com.tebsonatishop.user_info;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.tebsonatishop.R;
import com.tebsonatishop.activities.MainActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class Registeration_helper extends AsyncTask<Void, Void, String> {

    Context c;
    String urlAddress;
    EditText etName, etPhoneNumber, etPassword;
    TextView txState;
    ProgressDialog pd;
    User           user;
    ConstraintLayout constraintLayout;
    ConstraintSet constraintSet;


    public Registeration_helper(Context c, String urlAddress, EditText etName, EditText etPhoneNumber
                                    , EditText etPassword, TextView txState, ConstraintLayout constraintLayout) {

        this.c = c;
        this.urlAddress = urlAddress;
        this.etName = etName;
        this.etPhoneNumber = etPhoneNumber;
        this.etPassword = etPassword;
        this.txState = txState;
        this.constraintLayout = constraintLayout;

        user = new User();
        user.setName(etName.getText().toString());
        user.setPhoneNumber(etPhoneNumber.getText().toString());
        user.setPassword(etPassword.getText().toString());
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("عضویت کاربر");
        pd.setMessage("درحال عضویت ... لطفا صبر کنید");
        pd.show();
    }


    @Override
    protected String doInBackground(Void... params) {
        return this.registerUser();
    }


    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        pd.dismiss();
        if (response.startsWith("Error")) {
            display(response);

        } else {

            if (response.startsWith(ErrorTracker.USER_EXIST)) {
                //display(response);
                etPhoneNumber.setBackgroundResource(R.drawable.et_border_red);
                txState.setText("این شماره همراه از قبل وجود دارد.");
                txState.setVisibility(View.VISIBLE);

                constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);

                constraintSet.connect(R.id.etPassInRegisterPage,ConstraintSet.TOP,R.id.txStateUserRegister,ConstraintSet.BOTTOM);
                constraintSet.applyTo(constraintLayout);


            }

            else if (response.startsWith(ErrorTracker.SUCCESS)) {
                display(response);

                SharedPreferences sharedPreferences = Registeration_helper.this.c.getSharedPreferences("file", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", etName.getText().toString());
                editor.putString("user", etPhoneNumber.getText().toString());
                editor.putString("pass", etPassword.getText().toString());
                editor.commit();

                etPhoneNumber.setBackgroundResource(R.drawable.edittext_style);
                etName.setText("");
                etPhoneNumber.setText("");
                etPassword.setText("");

                Toast.makeText(c, "عضویت با موفقیت انجام شد", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(c, MainActivity.class);
                c.startActivity(intent);
            }
        }
    }


    private void display(String txt) {
        //Toast.makeText(c, txt, Toast.LENGTH_LONG).show();
        /*if(txt.contains("user exist already,sorry")){
            txState.setText("شماره همراه از قبل وجود دارد");
        }*/


    }


    private String registerUser() {
        Object connection = Connector.connect(urlAddress);
        if (connection.toString().startsWith("Error")) {
            return connection.toString();

        }

        try {

            HttpURLConnection con = (HttpURLConnection) connection;
            OutputStream os = new BufferedOutputStream(con.getOutputStream());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

            String registerationData = new Registeraton_data(user).packregisterationData();
            if (registerationData.startsWith("Error")) {
                return registerationData;

            }

            bw.write(registerationData);
            bw.flush();
            bw.close();
            os.close();

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream is = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line;
                StringBuffer response = new StringBuffer();

                while ((line = br.readLine()) != null) {
                    response.append(line + "\n");
                }

                br.close();
                is.close();

                return response.toString();
            } else {

                return ErrorTracker.RESPONSE_ERROR + responseCode;

            }

        }
        catch (IOException e) {
            e.printStackTrace();
            return ErrorTracker.IO_ERROR;

        }

    }
}
