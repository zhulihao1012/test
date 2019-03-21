package com.example.administrator.my0314;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main2Activity extends AppCompatActivity {
private String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        new  NetPing().execute();

    }


    public String Ping(String str) {
        String resault = "";
        Process p;
        try {
            //ping -c 3 -w 100  中  ，-c 是指ping的次数 3是指ping 3次 ，-w 100  以秒为单位指定超时间隔，是指超时时间为100秒
            p = Runtime.getRuntime().exec("ping -c 2 -w 10 " + str);
            int status = p.waitFor();

            InputStream input = p.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null){
                buffer.append(line);
            }
            System.out.println("Return ============" + buffer.toString());

            if (status == 0) {
                resault = "success";
            } else {
                resault = "faild";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return resault;
    }

    private class NetPing extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            s = "";
            s = Ping("www.baidu.com");
            Log.e("ping", s);
            if (s=="success"){
                Log.e("","shibushini");
            }
            return s;
        }
    }



}
