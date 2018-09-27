package register.csi.com.registerforpublicityapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    String deviceid,firstname,middlename,lastname,emailid,password,repeatpassword;
FirebaseDatabase database;
DatabaseReference databaseReference;
    View focusView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        final String android_id = Settings.Secure.getString(getBaseContext()
                .getContentResolver(), Settings.Secure.ANDROID_ID);
   //     TextView textView=findViewById(R.id.text);
 //      textView.setText(android_id);
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();
        final EditText first=findViewById(R.id.input_firstname);
        final EditText middle=findViewById(R.id.input_middlename);
        final EditText last=findViewById(R.id.input_lastname);
        final EditText Emailid=findViewById(R.id.email);
        final EditText pass=findViewById(R.id.input_password);
        final EditText repass=findViewById(R.id.reEnterPassword);
        Button b=findViewById(R.id.register);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstname = first.getText().toString();
                middlename = middle.getText().toString();
                lastname = last.getText().toString();
                emailid = Emailid.getText().toString();
                password = pass.getText().toString();
                repeatpassword = repass.getText().toString();
                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                }
                else
                    connected = false;
                if(connected==false)
                    Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
                else
                if (firstname.length() == 0) {
                    first.setError("Enter First name");

                    focusView = first;
                    focusView.requestFocus();

                } else if (middlename.length() == 0) {
                    middle.setError("Enter Middle name");
                    focusView = middle;
                    focusView.requestFocus();

                } else if (lastname.length() == 0) {
                    last.setError("Enter last name");
                    focusView = last;
                    focusView.requestFocus();

                } else if (emailid.equals("")) {
                    Emailid.setError("Enter Email id");
                    focusView = Emailid;
                    focusView.requestFocus();

                } else if (password.length() == 0) {
                    pass.setError("Enter Password");
                    focusView = pass;
                    focusView.requestFocus();

                } else if (repeatpassword.length() == 0) {
                    repass.setError("Enter Password");
                    focusView=repass;
                    focusView.requestFocus();

                }
                else if (!isEmailValid(emailid)) {
                    {
                        Emailid.setError("This email is invalid");
                        focusView=Emailid;
                        focusView.requestFocus();

                    }
                } else if (password.length() < 8)
                {        pass.setError("Length of password should be greater than or equal to 8");
                focusView = pass;
                    focusView.requestFocus();

                }
else
    if(!password.equals(repeatpassword))
        Toast.makeText(getApplicationContext(),"Password do not match",Toast.LENGTH_SHORT).show();
else
    {
        User user;
        deviceid = Settings.Secure.getString(getBaseContext()
                .getContentResolver(), Settings.Secure.ANDROID_ID);
        try {
      MessageDigest      md = MessageDigest.getInstance("MD5");
            byte[] passBytes = password.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            Log.i("checkinghashedpassword",sb.toString());
password=sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String memberinitial=firstname.substring(0,3)+"-"+middlename.substring(0,3)+"-"+lastname.substring(0,3);
        Log.i("member",memberinitial);
        String membername=firstname+" "+middlename+" "+lastname+" ";
        Log.i("membername",membername);
        user=new User(android_id,emailid,0,0,memberinitial,membername,password,0);
        databaseReference.child("users").push().setValue(user);
        pass.setText("");
        repass.setText("");
        Emailid.setText("");
        first.setText("");
        middle.setText("");
        last.setText("");
        Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
    }


            }
        });

    }
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

}
