package it.valeriocristofori.thefridgemobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

import it.valeriocristofori.thefridgemobile.R;
import it.valeriocristofori.thefridgemobile.controller.SyntaxValidation;
import it.valeriocristofori.thefridgemobile.db.init.DatabaseManagerConnection;
import it.valeriocristofori.thefridgemobile.model.ToastCustom;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create this.Holder
        Holder holder = new Holder();

        this.open_db_connection();

    }

    private void open_db_connection(){

        // init connection with 'sqlite' database
        try {
            DatabaseManagerConnection db = DatabaseManagerConnection.getSingletonInstance(this);
            db.open();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    class Holder implements View.OnClickListener{
        private EditText tfUsername;
        private EditText tfPassword;
        private Button btnLogin;
        private Button btnSignup;

        public Holder(){
            //init login button
            this.btnLogin = (Button)findViewById(R.id.btnLogin);
            //init sign up button
            this.btnSignup = (Button)findViewById(R.id.btnSignup);
            //binding xml components and java components
            this.tfUsername = (EditText)findViewById(R.id.tfUsername);
            this.tfPassword = (EditText)findViewById(R.id.tfPassword);

            //assign click listener
            this.btnLogin.setOnClickListener(this);
            this.btnSignup.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnLogin){
                //login use case

                //syntax control
                //instance controller for input
                SyntaxValidation sv = new SyntaxValidation();
                if( !sv.validSyntaxUsername(tfUsername.getText().toString()) ){
                    //launch toast for username error
                    this.displayErrorMessage("Username wrong");
                    return;
                }
                if( !sv.validSyntaxPassword(tfPassword.getText().toString()) ){
                    //launch toast for pass error
                    this.displayErrorMessage("Password wrong");
                    return;
                }

                //remand home GUI
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
            else if(v.getId() == R.id.btnSignup){
                //remand to register GUI
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        }



        private void displayErrorMessage( String message ){
            //refactor this in the ToastCustom class!

            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_alert_layout,
                    (ViewGroup) findViewById(R.id.custom_toast_container));

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText(message);


            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();

        }
    }


}
