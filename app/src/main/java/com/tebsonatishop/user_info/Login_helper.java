package com.tebsonatishop.user_info;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

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


public class Login_helper extends AsyncTask<Void, Void, String> {

    Context c;
    String urlAddress;
    EditText usernameTxt1, passwordTxt1;

    ProgressDialog pd;
    User_login     user;


    public Login_helper(Context c, String urlAddress, EditText usernameTxt1, EditText passwordTxt1) {

        this.c = c;
        this.urlAddress = urlAddress;
        this.usernameTxt1 = usernameTxt1;
        this.passwordTxt1 = passwordTxt1;

        user = new User_login();
        user.setUsernameOrEmail(usernameTxt1.getText().toString());
        user.setPassword(passwordTxt1.getText().toString());
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("ورود");
        pd.setMessage("درحال ورود ... لطفا صبر نمایید");
        pd.show();
    }


    @Override
    protected String doInBackground(Void... params) {
        return this.Login();
    }


    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        pd.dismiss();
        if (response.startsWith("Error")) {
            display(response);

        } else {

            if (response.startsWith(ErrorTracker.LOGIN_SUCCESS)) {

                //Line below For Remove String Logged Successfully from string and get only name:
                String name = response.replace("Logged Successfully", "");
                //Line below for remove only the spaces at the beginning or end of the String:
                name = name.trim();

                SharedPreferences sharedPreferences = Login_helper.this.c.getSharedPreferences("file", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("user", usernameTxt1.getText().toString());
                editor.putString("pass", passwordTxt1.getText().toString());
                editor.putString("name", name);
                editor.commit();

                if (response.contains("admin")){
                    editor.putString("type", "admin");
                    editor.commit();
                }

                usernameTxt1.setText("");
                passwordTxt1.setText("");
                display(response);

                Intent intent = new Intent(c, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                c.startActivity(intent);


            }

            else {
                display(response);

            }
        }
    }


    private void display(String txt) {

        if (txt.contains("Wrong details")){
            Toast.makeText(c, "نام کاربری یا کلمه عبور اشتباه است", Toast.LENGTH_SHORT).show();
        }

    }


    private String Login() {
        Object connection = Connector.connect(urlAddress);
        if (connection.toString().startsWith("Error")) {
            return connection.toString();

        }

        try {

            HttpURLConnection con = (HttpURLConnection) connection;
            OutputStream os = new BufferedOutputStream(con.getOutputStream());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

            String LoginData = new Login_data(user).packLoginData();
            if (LoginData.startsWith("Error")) {
                return LoginData;

            }

            bw.write(LoginData);
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
