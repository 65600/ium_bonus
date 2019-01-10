package ium.test.bonus;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_screen);

        Button b = findViewById(R.id.backButton);
        TextView title = findViewById(R.id.confirmTitle);

        //Importa le informazioni adeguate dall'intent
        Intent i = getIntent();
        if(i.getBooleanExtra("success", false)){
            String msg = "Benvenuto " + i.getStringExtra("username");
            title.setText(msg);
        }else{
            title.setText("Accesso negato.");
            title.setTextColor(ContextCompat.getColor(this, R.color.colorError));
        }

        b.setOnClickListener((View v) ->
                finish() //Chiude l'attività corrente e ritorna alla precedente senza aprirne una nuova
        );
    }
}
