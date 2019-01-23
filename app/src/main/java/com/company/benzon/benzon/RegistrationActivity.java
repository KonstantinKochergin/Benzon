package com.company.benzon.benzon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    EditText surnameET, nameET, numberET;

    String surname, name, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        surnameET = findViewById(R.id.surname_edit_text);
        nameET = findViewById(R.id.name_edit_text);
        numberET = findViewById(R.id.phone_edit_text);

    }

    public void onRegistrationButtonClick(View view){
        surname = surnameET.getText().toString().replaceAll("\\s", "");
        name = nameET.getText().toString().replaceAll("\\s", "");
        number = numberET.getText().toString().replaceAll("\\s", "");
        GlobalData.user = new Profile(name, surname, number);
        Intent intent = new Intent(this, MainActivity.class);
        RegistrationAsyncTask asyncTask = new RegistrationAsyncTask(surname, name, number, getSharedPreferences(GlobalData.USER_PREFS, Context.MODE_PRIVATE), intent, this);
        asyncTask.execute();
        /*try {
            InteractionWithServer.newUser(number, GlobalData.PASSWORD, surname, name);
            SharedPreferences userSettings = getSharedPreferences(GlobalData.USER_PREFS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = userSettings.edit();
            editor.putString(GlobalData.SURNAME_KEY, surname);
            editor.putString(GlobalData.NAME_KEY, name);
            editor.putString(GlobalData.NUMBER_KEY, number);
            editor.apply();
        }
        catch (Exception ex){
            Log.e("Сервачок",  ex.toString());
            Toast.makeText(this, "С регистрацией проблема", Toast.LENGTH_SHORT).show();
        }*/
       /* Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);*/
    }
}

class RegistrationAsyncTask extends AsyncTask<Void, Void, Void>{

    private String number, surname, name;
    private SharedPreferences preferences;
    private Intent intent;
    private Context context;

    public RegistrationAsyncTask(String surname, String name, String number, SharedPreferences prefs, Intent intent, Context context){
        this.surname = surname;
        this.name = name;
        this.number = number;
        preferences = prefs;
        this.intent = intent;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            InteractionWithServer.newUser(number, GlobalData.PASSWORD, surname, name);
            SharedPreferences userSettings = preferences;
            SharedPreferences.Editor editor = userSettings.edit();
            editor.putString(GlobalData.SURNAME_KEY, surname);
            editor.putString(GlobalData.NAME_KEY, name);
            editor.putString(GlobalData.NUMBER_KEY, number);
            editor.apply();
        }
        catch (Exception ex){
            Log.e("Сервачок",  ex.toString() + " из асинк таска");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        context.startActivity(intent);
        super.onPostExecute(aVoid);
    }
}
