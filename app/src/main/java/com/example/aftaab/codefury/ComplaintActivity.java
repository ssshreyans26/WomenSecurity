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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class ComplaintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaint_layout);
        final Button complaint_btn=findViewById(R.id.comp_btn);
        final EditText complaint_text=findViewById(R.id.complaint_text);
        final String name=(String)getIntent().getExtras().getSerializable("NAME");
        final String c_id=getSaltString();
        final String comp_text=complaint_text.getText().toString();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        final String date2= dateFormat.format(date);

        complaint_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q[]={name, comp_text, c_id, date2};
                register_complaint registerComplaint=new register_complaint();
                registerComplaint.execute(q);
            }
        });



    }
    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }


    public class register_complaint extends AsyncTask<String, Void, Void>{
        public int exit_code=-1;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(exit_code==1){
                Toast.makeText(getApplicationContext(), "Complaint sent successfully", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Some error occured", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected Void doInBackground(String... q) {
            String name=q[0];
            String complaint_id=q[1];
            String complaint_text=q[2];
            String date=q[3];
            try{
                URL url=new URL("https://codefury1.herokuapp.com/update_chat?NAME="+name+"&COMPLAINT="+complaint_text+"&COMPLAINT_ID="+complaint_id+"&DATE="+date);
                String res=Utility.fetchFromUrl(url);
                if(res.equals("1"))
                {
                    exit_code=1;
                }


            }catch (Exception e){e.printStackTrace();}


            return null;
        }
    }
}
