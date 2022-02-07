package com.example.showseek.layout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.showseek.R;
import com.example.showseek.estructures.array.ListArray;
import com.example.showseek.objects.Artista;
import com.example.showseek.objects.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Atributos globales
    private TextView register;
    private EditText editTextEmail, editTextPassword;
    private Button singIn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private String email;
    private String nombre;
    private DatabaseReference database;
    private Intent inicio;

    //Se infla la actividad con el xml activity_main y se instancian los botones
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        singIn = (Button) findViewById(R.id.botonLogin);
        singIn.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        editTextEmail = findViewById(R.id.entradaCorreoLogin);
        editTextPassword= findViewById(R.id.entradaContraseña2);

        mAuth = FirebaseAuth.getInstance();

    }

    //Actividades de los botones de la vesta inflada
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.botonLogin:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("El email es necesario");
            editTextEmail.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Introduzca un email valido");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("La contraseña es necesaria");
            editTextPassword.requestFocus();
            return;
        } else if (password.length() < 6) {
            editTextPassword.setError("La contraseña mínima es de 7 caracteres");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    //redirecciona a WelcomeFragment----------------------------------------------------------------

                    database = FirebaseDatabase.getInstance().getReference();
                    database.child("Users").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.exists()){
                                for(DataSnapshot snap : snapshot.getChildren()){
                                    Usuario ar = snap.getValue(Usuario.class);

                                    if(email.compareTo(ar.getCorreo()) == 0){
                                        inicio = new Intent(MainActivity.this, InicioActivity.class);
                                        nombre = ar.getNombre();
                                        String key = snap.getKey();
                                        Log.d("name",nombre);
                                        if(nombre == null){
                                            nombre = "No se encontró el usuario :c";
                                            inicio.putExtra("nombre",nombre);
                                        }
                                        else{
                                            inicio.putExtra("nombre",nombre);
                                        }
                                        inicio.putExtra("clave",key);
                                        inicio.putExtra("tipo",ar.getTipo());
                                        startActivity(inicio);
                                        break;
                                    }


                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });





                } else {
                    Toast.makeText(MainActivity.this, "Error al conectar por favor revisar sus datos", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    /*
    //Atributes
    Button b1, b2, b3;
    TextView salida;
    EditText entrada;
    LinkedList<Cliente> registros;
    StackRef<String> repertorio;
    QueueRef<String> contratos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.b_ingresar);
        b2 = findViewById(R.id.b_refrescar);
        b3 = findViewById(R.id.btn_Opcion);

        salida = findViewById(R.id.salida_texto);
        entrada = findViewById(R.id.entrada_texto);

        registros = new LinkedList<Cliente>();
        repertorio = new StackRef<String>();
        contratos = new QueueRef<String>();

        String c1 = "Hola! \nQuiero contrartarte para \nuna boda al aire libre. Seria maravilloso\n tomar tus servicios el 12 de diciembre.\n\n\tCliente: Francesca";
        String c2 = "Hola! \nQuiero contrartarte para \nuna fiesta de 50 años. Seria maravilloso\n tomar tus servicios el 8 de diciembre.\n\n\tCliente: Santiago";
        String c3 = "Hola! \nQuiero contrartarte para \nuna boda al aire libre. Seria maravilloso\n tomar tus servicios el 2 de Enero.\n\n\tCliente: César";
        String c4 = "Hola! \nQuiero contrartarte para \nuna lunada de reencuentro. Seria maravilloso\n tomar tus servicios el 7 de diciembre.\n\n\tCliente: Esteban";
        contratos.enqueue(c1);
        contratos.enqueue(c2);
        contratos.enqueue(c3);
        contratos.enqueue(c4);

        opcion();

        String prueba = "Se entra a opciones";
        Log.d("Salida: ",prueba);
    }

    public void opcion(){
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Se entra a : ","Botón Opción");
                salida.setText("Ingresa la opción que deseas utilizar\n1. Registrar Nuevo Usuario\n2. Creación de Repertorio\n3. Ver Repertorio\n 4. Ver Contratos");
                String opcion = String.valueOf(entrada.getText());
                if("1".equals(opcion)){
                    Log.d("Se entra a : ","Registro de Usuario");
                    salida.setText("Ingresa los datos del nuevo usuario separados por comas");
                    entrada.setText("");
                    registrar();
                }
                else if("2".equals(opcion)){
                    Log.d("Se entra a : ","Entrada de Repertorio");
                    salida.setText("Ingresa las canciones  de menor a mayor importancia");
                    entrada.setText("");
                    repertorio1();
                }
                else if("3".equals(opcion)){
                    Log.d("Se entra a : ","Salida de Repertorio");
                    salida.setText("En refrescar verás las canciones de la más importante a\n menos importante");
                    entrada.setText("");
                    repertorio2();
                }
                else if("4".equals(opcion)){
                    salida.setText("En refrescar verás los contratos añadidos en orden de pedido");
                    Log.d("Se entra a : ","Visualización de Contratos");
                    entrada.setText("");
                    contrato();
                }
            }
        });
    }


    public void repertorio2(){
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Se oprimió Refrescar: ","Salida de Repertorio");
                String a=repertorio.pop();

                if(a != null){
                    salida.setText(a);
                }
                else {
                    salida.setText("No hay más canciones para mostrar");
                }
            }
        });
    }

    public void contrato(){
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Se oprimió Refrescar: ","Vizualización de Contratos");
                String a=contratos.dequeue();

                if(a != null){
                    salida.setText(a);
                }
                else {
                    salida.setText("No hay más solicitudes para eventos");
                }

            }
        });
    }

    public void registrar(){
        b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                Log.d("Se oprimió Ingresar : ","Registrar nuevo Usuario");
                String[] datos = String.valueOf(entrada.getText()).split(",");
                Cliente last = registros.topBack();
                Cliente user;
                try{
                    if(last != null){
                        user = new Cliente(last.getID()+1, datos[0], datos[1], datos[2],datos[3], datos[4],datos[5], datos[6],datos[7]);
                    }
                    else{
                        user = new Cliente(1, datos[0], datos[1], datos[2],datos[3], datos[4],datos[5], datos[6],datos[7]);
                    }
                    registros.pushBack(user);
                    Log.d("Registro: ","Se ha añadido un nuevo usuario con el ID: " + user.getID());
                }
                catch(Exception e){
                    Log.d("Ingreso de Datos de usuario por 'Ingresar' : ","Seleccionar nueva opción");
                }


            }
        });
    }

    public void repertorio1(){
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Se oprimió Ingresar : ","Entrada de Repertorio");
                String cancion = String.valueOf(entrada.getText());
                repertorio.push(cancion);
                Log.d("Repertorio: ","Se ha añadido una nueva canción a la cola :"+repertorio.top());
                entrada.setText("");
            }
        });
    }*/

}


