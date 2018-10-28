package com.example.marian.webservicestest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{
    private TextView msg;
    Button httpButton;
    HttpConnection httpConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg= findViewById(R.id.msg_tv);
        httpButton= findViewById(R.id.http_btn);

        httpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //34an a connect 3 elurl da fe fun esmha OppenConnection abaselha elurl da w tro7 hea fat7ah

                //fe class esmo UrlConnection da ba7ot gwah nategt elfun elly fo2 d 34an y3rdha
                //lkn bma nnta bnst5dm el http protocol
                //fhansta5dm hhhtpUrlConnection w da class 5as b elconnection bta3 el http bs


                //3ml try w catch ll IOEcxeption 34an elURLconnection behandel el MalFormedURLExcxeption
                //elly hwa mas2ol 3n no yhandl 2y a5ta2 gaya m elurl
                //w l2no bewrs m el IOEcxeption f hwa be3ml llIO belmra


        /*dlw2t lo b3tna the msg da lltextview haytl3 error esmo NetworedMainThread
        * y3ny elcode da maebfa34 yrun f elmain 34an elmultithreading
        * lazm n3mlo thread lwa7do
        * w da b enna npassy kol elcode da f object mn class runable */

                Runnable codeRunnable = new Runnable() {
                    @Override
                    public void run() {

                        try

                        {
                            URL getMdg = new URL("http://developerhendy.16mb.com/hello.php");

                            HttpURLConnection connector = (HttpURLConnection) getMdg.openConnection();

            /*
            tyb eldata elly btgely d btgely bytes
            ana m7tag 7aga t7awly elbytes d l no3 el7aga elly gaya swa2 text 2o imgs 2o..
            w hna ba7tag l2nwa3 elstreams elmo5talfa
            w mnha ell hansta5dmo dlw2t w da elstreem elmas2ol 3n ta7weel elbytes l chars

             */
                            InputStreamReader charsStreem =new InputStreamReader(connector.getInputStream()) ;
                            BufferedReader bufferedReader = new BufferedReader(charsStreem);
                            final String theMsg = bufferedReader.readLine();
                    /*
                    * ha3rf object mn no3 thread y 5ly elrunable yrun elcode f thread tani 8er thread elmain
                    * */

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run()
                                {
                                    msg.setText(theMsg);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                };



                Thread newThread = new Thread(codeRunnable);
                newThread.start();



            }
        });




    }
}
