package cl.ist.proyectoapp3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AResultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_main);

        final Button BtVolver =(Button) findViewById(R.id.btVolver);
        BtVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sIntent = new Intent(AResultado.this, MainActivity.class);
                startActivity(sIntent);

            }
        });
    }
}
