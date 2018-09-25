package com.example.aftaab.codefury;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        Button login_btn=findViewById(R.id.login_btn);
        final TextView email_view=findViewById(R.id.user_id);
        final TextView pass_view=findViewById(R.id.password);
        Button signup=findViewById(R.id.sign_up_btn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, SignUP.class);
                startActivity(intent);
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_id=email_view.getText().toString();
                String pass=pass_view.getText().toString();
                try {
                    byte[] data = pass.getBytes("UTF-8");
                    String base64 = Base64.encodeToString(data, Base64.DEFAULT);
                    pass=base64;


                }catch (Exception e){e.printStackTrace();}
                login_check check =new login_check();
                String q[]={u_id, pass};
                check.execute(q);

            }
        });
    }

    public class login_check extends AsyncTask<String, Void, Void>{
        public int exit_code=0;
        Bundle bundle;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... q) {

            String u_id=q[0];
            String pass=q[1];
            try{
                URL url=new URL("https://codefury1.herokuapp.com/login?USER_ID="+u_id+"&PASSWORD="+pass);
                String res=Utility.fetchFromUrl(url);
                bundle.putSerializable("USER_ID", u_id);
                bundle.putSerializable("PASSWORD", pass);
                if(res.equals("1")){
                    exit_code=1;
                }
                else{
                    exit_code=-1;
                }

            }catch (Exception e){e.printStackTrace();}

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(exit_code==1){
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
            if(bundle!=null)
                intent.putExtras(bundle);
            startActivity(intent);}
            else if(exit_code==-1){
                Toast.makeText(getBaseContext(), "User id or password already exists", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getBaseContext(), "No internet", Toast.LENGTH_LONG).show();
            }
        }
    }
}
