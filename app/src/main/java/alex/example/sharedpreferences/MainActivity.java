package alex.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private EditText txtNom;
    private EditText txtEdad;
    private Button btnGuardar;
    private Button btnBorrarsp;
    private ImageButton btnEliminarnom;
    private ImageButton btnEliminared;


    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNom = findViewById(R.id.txtnombre);
        txtEdad = findViewById(R.id.txtedad);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnBorrarsp = findViewById(R.id.btnBorrar);
        btnEliminarnom = findViewById(R.id.btnelimnar1);
        btnEliminared = findViewById(R.id.btneliminar2);
        // con esto creo el XML de config
        sp = getSharedPreferences(constante.PERSONAS, MODE_PRIVATE);

        // para leer utilizar la Sp

        String nombre = sp.getString(constante.NOMBRE, "");
        int edad = sp.getInt(constante.EDAD, -1);
        if (!nombre.isEmpty())
            txtNom.setText(nombre);

        if (edad != -1)
            txtEdad.setText(String.valueOf(edad));


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNom.getText().toString();
                int edad = Integer.parseInt(txtEdad.getText().toString());
                // para poder editar o guardar necesito un editor
                SharedPreferences.Editor edit = sp.edit();
                edit.putString(constante.NOMBRE, nombre);
                edit.putInt(constante.EDAD, edad);
//Commit y play
                edit.apply();
            }
        });
        btnBorrarsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.apply();
            }
        });

        btnEliminarnom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.remove(constante.NOMBRE);
                editor.apply();
                txtNom.setText("");

            }
        });
        btnEliminared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.remove(constante.EDAD);
                editor.apply();
                txtEdad.setText("");
            }
        });


    }
}

