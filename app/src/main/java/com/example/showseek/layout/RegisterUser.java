package com.example.showseek.layout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.showseek.R;
import com.example.showseek.layout.MainActivity;
import com.example.showseek.objects.Artista;
import com.example.showseek.objects.Cliente;
import com.example.showseek.objects.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, registerUser;
    private EditText editTextFullName, editTextAge, editTextEmail, editTextPassword;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private CheckBox checkArtista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // muestra la vista del registro de usuario
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();
        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.fullName);
        editTextAge = (EditText) findViewById(R.id.age);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        checkArtista = findViewById(R.id.checkArtista);

        progressBar = (ProgressBar) findViewById(R.id.progressBarRegister);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner:
                // al clickear el Banner nos devuelve a la main activity
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerUser:
                // al presionar Registrar se inicia la funcion que realiza el procedimiento
                registerUser();
                break;
        }
    }
    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        // Constraints para evitar errores en el ingreso de datos

        if (fullName.isEmpty()) {
            editTextFullName.setError("Se requiere un nombre completo");
            editTextFullName.requestFocus();
            return;
        }

        if (age.isEmpty()) {
            editTextAge.setError("Se requiere una edad");
            editTextAge.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Se requiere un correo");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Por favor escriba un correo valido");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Se requiere una contrase??a");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Por favor use m??nimo 6 caracteres");
            editTextPassword.requestFocus();
            return;
        }

        // Inicia el proceso de autenticaci??n con Firebase Database

        progressBar.setVisibility(View.VISIBLE);
        // Crea el usuario con la opcion de correo y contrase??a
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            if(checkArtista.isChecked()){
                                Artista user = new Artista(fullName, age, email);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){
                                            Toast.makeText(RegisterUser.this,"El usuario ha sido registrado exitosamente",Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                            startActivity(new Intent(RegisterUser.this, MainActivity.class));

                                        }else{
                                            Toast.makeText(RegisterUser.this, "Registro de Artista fallido, vuelva a intentar",Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        }

                                    }
                                });
                            }
                            else{
                                Cliente user = new Cliente(fullName, age, email);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){
                                            Toast.makeText(RegisterUser.this,"El usuario ha sido registrado exitosamente",Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                            startActivity(new Intent(RegisterUser.this, MainActivity.class));

                                        }else{
                                            Toast.makeText(RegisterUser.this, "Registro de Cliente fallido, vuelva a intentar",Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        }

                                    }
                                });
                            }


                        }else {
                            Toast.makeText(RegisterUser.this, "Registro de usuario fallido, vuelva a intentar",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}