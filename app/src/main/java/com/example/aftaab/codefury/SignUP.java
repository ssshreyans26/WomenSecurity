package com.example.aftaab.codefury;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;

public class SignUP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        final EditText name_view=findViewById(R.id.signup_name);
        final EditText u_id_view=findViewById(R.id.signup_uid);
        final EditText pass_view=findViewById(R.id.signup_pass);
        final EditText c_pass_view=findViewById(R.id.signup_c_pass);
        final EditText e_1_view=findViewById(R.id.signup_e1);
        final EditText e_2_view=findViewById(R.id.signup_e_2);
        Button register_btn=findViewById(R.id.signup_btn);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=name_view.getText().toString();
                String u_id=u_id_view.getText().toString();
                String pass=pass_view.getText().toString();
                String c_pass=c_pass_view.getText().toString();
                String e_1=e_1_view.getText().toString();
                String e_2=e_2_view.getText().toString();
                if(c_pass.equals(pass)){
                signup_new signupNew=new signup_new();
                String q[]={name, u_id, pass, e_1, e_2};
                signupNew.execute(q);


                }
                else {
                    Toast.makeText(getApplicationContext(), "Password dont match", Toast.LENGTH_LONG).show();;
                }
            }
        });


    }

    public class signup_new extends AsyncTask<String, Void, Void>{
        public int exit_code=-1;
        Bundle bundle=new Bundle();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... q) {

            String name, u_id, pass, e1, e2;
            name=q[0];
            u_id=q[1];
            pass=q[2];
            e1=q[3];
            e2=q[4];
            try{
                URL url=new URL("https://codefury1.herokuapp.com/signup?NAME="+name+"&USER_ID="+u_id+"&PASSWORD="+pass+"&EMERGENCY_1="+e1+"&EMERGENCY_2="+e2);
                String res=Utility.fetchFromUrl(url);
                if(res.equals("1")){
                    exit_code=1;
                    bundle.putSerializable("USER_ID", u_id);
                }


            }catch (Exception e){e.printStackTrace();}

            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            if(exit_code==1) {
                Toast.makeText(getApplicationContext(), "New user created successfully", Toast.LENGTH_LONG);
                Intent intent=new Intent(SignUP.this, MainActivity.class);
                if(bundle!=null)

                    intent.putExtras(bundle);
                startActivity(intent);

            }
        }
    }
}
