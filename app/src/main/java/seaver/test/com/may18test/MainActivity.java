package seaver.test.com.may18test;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText account;
    private EditText password;
    private Button login;
    private CheckBox remark;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        account=(EditText)findViewById(R.id.account);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        remark=(CheckBox)findViewById(R.id.isremember);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember=sharedPreferences.getBoolean("isRemember",false);
        if(isRemember){
            account.setText(sharedPreferences.getString("account",""));
            password.setText(sharedPreferences.getString("password",""));
            remark.setChecked(true);
        }
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                editor=sharedPreferences.edit();
                if(remark.isChecked()){
                    editor.putString("account",account.getText().toString());
                    editor.putString("password",password.getText().toString());
                    editor.putBoolean("isRemember",true);
                }else {
                    editor.clear();
                }
                editor.apply();
                Intent intent=new Intent(MainActivity.this,FirstActivity.class);
                startActivity(intent);
                break;
        }
    }
}
