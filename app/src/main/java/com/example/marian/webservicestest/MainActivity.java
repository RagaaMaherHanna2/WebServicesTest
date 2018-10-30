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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity
{
    private TextView msg;
    Button httpButton;
    Button retrofitButton;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg= findViewById(R.id.msg_tv);
        httpButton= findViewById(R.id.http_btn);
//
//        httpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//
//                //34an a connect 3 elurl da fe fun esmha OppenConnection abaselha elurl da w tro7 hea fat7ah
//
//                //fe class esmo UrlConnection da ba7ot gwah nategt elfun elly fo2 d 34an y3rdha
//                //lkn bma nnta bnst5dm el http protocol
//                //fhansta5dm hhhtpUrlConnection w da class 5as b elconnection bta3 el http bs
//
//
//                //3ml try w catch ll IOEcxeption 34an elURLconnection behandel el MalFormedURLExcxeption
//                //elly hwa mas2ol 3n no yhandl 2y a5ta2 gaya m elurl
//                //w l2no bewrs m el IOEcxeption f hwa be3ml llIO belmra
//
//
//                /*dlw2t lo b3tna the msg da lltextview haytl3 error esmo NetworedMainThread
//                * y3ny elcode da maebfa34 yrun f elmain 34an elmultithreading
//                * lazm n3mlo thread lwa7do
//                * w da b enna npassy kol elcode da f object mn class runable */
//
//                Runnable codeRunnable = new Runnable() {
//                    @Override
//                    public void run() {
//
//                        try
//
//                        {
//                            URL getMdg = new URL("http://developerhendy.16mb.com/hello.php");
//
//                            HttpURLConnection connector = (HttpURLConnection) getMdg.openConnection();
//
//                            /*
//                            tyb eldata elly btgely d btgely bytes
//                            ana m7tag 7aga t7awly elbytes d l no3 el7aga elly gaya swa2 text 2o imgs 2o..
//                            w hna ba7tag l2nwa3 elstreams elmo5talfa
//                            w mnha ell hansta5dmo dlw2t w da elstreem elmas2ol 3n ta7weel elbytes l chars
//
//                             */
//                            InputStreamReader charsStreem =new InputStreamReader(connector.getInputStream()) ;
//                            BufferedReader bufferedReader = new BufferedReader(charsStreem);
//                            final String theMsg = bufferedReader.readLine();
//                            /*
//                            * ha3rf object mn no3 thread y 5ly elrunable yrun elcode f thread tani 8er thread elmain
//                            * */
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run()
//                                {
//                                    msg.setText(theMsg);
//                                }
//                            });
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                };
//
//
//
//                Thread newThread = new Thread(codeRunnable);
//                newThread.start();
//
//
//
//            }
//        });

        httpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 5- implement Retrofit Object

                Retrofit retrofitObject = new Retrofit
                        .Builder()
                        .baseUrl("http://developerhendy.16mb.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitInterface retrofitInterface = retrofitObject.create(RetrofitInterface.class);

                Call<RetrofitModel> gettingResult = retrofitInterface.getPosts();
                gettingResult.enqueue(new Callback<RetrofitModel>() {
                    @Override
                    public void onResponse(Call<RetrofitModel> call, Response<RetrofitModel> response) {
                        List<Post> Posts = response.body().getPosts();


                        for (int i=0; i < Posts.size(); i++)
                        {
                            String PostContent = Posts.get(i).getPostContent();
                            msg.append(PostContent+"\n");
                        }
                    }

                    @Override
                    public void onFailure(Call<RetrofitModel> call, Throwable t) {

                    }
                });

            }
        });


    }


    /*
     * ana 34an a3ml connection b elretrofir lazm a3ml 4 steps
     * 1- add dependencies
     * 2- b3ml model l kol object m eljson objects
     * 3- implement model w da b3ml feh model l class eljson w basta2bl feh elbyanat w a7olha l viewos 3 elandroid
     * 4- implement interface w da ba3mlo 34an a7ot feh elfuns bta3t s7b w 7t elpyanat llserver y ema b elget y ema b elpost
     * 5- implement api beb2a feh object mn class elretrofit w da elly be2om b 3mlet elconnection w convetion eldata mn byte streem l elview elly e7na 3awzeno */

    // 1- add dependencies
    //done

    // 2- implement Post Model
    public class Post
    {
        private String postContent;

        public String getPostContent() {
            return postContent;
        }

        public void setPostContent(String postContent) {
            this.postContent = postContent;
        }
    }

    // 3- implement Model
    public class RetrofitModel
    {
        private List<Post> posts;

        public List<Post> getPosts() {
            return posts;
        }

        public void setPosts(List<Post> posts) {
            this.posts = posts;
        }
    }

    // 4- implement interface
    public interface RetrofitInterface
    {
        //han7ot gwah ellmethod ellhatgeb eldata
        //ba7ot 3leh annotation fo2 b no3 eltare2a elly hatgeb beha eldata swa2 get 2or post
        // w papaselha gwaha esm elmlf elly feh eldata elly hagebha

        @GET("getposts.php")
        Call<RetrofitModel> getPosts();

    }




}
