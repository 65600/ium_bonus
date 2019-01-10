package ium.test.bonus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, String> acceptedCredentials = createCredentials();
    private enum loginCheck{
        OK,
        user_len,
        pw_len
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.loginButton);
        TextView user = findViewById(R.id.username);
        TextView pw = findViewById(R.id.password);

        //(Java 8) Risoluzione da lambda a interfaccia funzionale con un solo metodo
        b.setOnClickListener((View v) -> {
            //Copia dei valori correnti e valutazione dello stato di errore.
            loginCheck res = loginCheck.OK;
            String u_check = user.getText().toString();
            String p_check = pw.getText().toString();

            if (u_check.length() < 1){
                res = loginCheck.user_len;
                user.setError("Nome utente vuoto");
            }
            if (p_check.length() < 1){
                res = loginCheck.pw_len;
                pw.setError("Password vuota");
            }

            if (res == loginCheck.OK){ //Se i campi sono pieni, si valuta la validità e si passa all'altra attività.
                Intent next = new Intent(this, ConfirmScreen.class);

                //Verifica che esista un utente al nome inserito e la password sia corretta.
                String matching = acceptedCredentials.get(u_check);
                if(matching != null && matching.equals(p_check)){
                    next.putExtra("success", true);
                    next.putExtra("username", u_check);
                }else{
                    next.putExtra("success", false);
                }

                startActivity(next);
            }
        });
    }

    //Credenziali di test
    public HashMap<String, String> createCredentials() {
        HashMap<String, String> gen = new HashMap<>();
        gen.put("admin", "admin");
        gen.put("user", "password");
        return gen;
    }
}
