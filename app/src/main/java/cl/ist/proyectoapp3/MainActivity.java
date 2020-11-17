package cl.ist.proyectoapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button BtAceptar = (Button) findViewById(R.id.btAceptar);
        BtAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ETNombre = (EditText) findViewById(R.id.etNombreFoto);
                String StNombre = ETNombre.getText().toString();
                Image ImageView = (Image) findViewById(R.id.imusuario);

                Intent sIntent = new Intent(MainActivity.this, AResultado.class);
                sIntent.putExtra("STNombre", StNombre);
                startActivity(sIntent);

                new Thread(new Runnable() {
                    public void run() {
                        final Bitmap bitmap =
                                loadImageFromNetwork("http://IP.com/image.png");
                        mImageView.post(new Runnable() {
                            public void run() {
                                mImageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                }).start();
            }
        });
    }
}