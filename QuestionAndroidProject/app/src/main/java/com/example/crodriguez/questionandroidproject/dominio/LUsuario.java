package com.example.crodriguez.questionandroidproject.dominio;

import android.support.annotation.NonNull;

import com.example.crodriguez.questionandroidproject.modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LUsuario implements ILUsuario{

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference refUsuarios;

    public LUsuario(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        refUsuarios = firebaseDatabase.getReference("base_datos_questions");
    }

    @Override
    public void crearUsuario(String password, final Usuario usuario, final CallBackInteractor<String> callBackInteractor) {

        firebaseAuth.createUserWithEmailAndPassword(usuario.getCorreo(), password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            usuario.setUid(task.getResult().getUser().getUid());
                            refUsuarios.child(usuario.getUid())
                                    .setValue(usuario);
                            callBackInteractor.success(usuario.getNombre());
                        } else {
                            callBackInteractor.error(task.getException().getMessage());
                        }

                    }
                });

    }

    @Override
    public void authUsuario(String email, String password, final CallBackInteractor<String> callBackInteractor) {

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            refUsuarios.child(task.getResult().getUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                                    callBackInteractor.success(usuario.getNombre());
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    callBackInteractor.error(databaseError.getMessage());
                                }
                            });
                        } else {
                            callBackInteractor.error(task.getException().getMessage());
                        }
                    }
                });

    }

    @Override
    public void RecuperarPassUsuario(String email, final CallBackInteractor<String> callBackInteractor) {

        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    callBackInteractor.success("Contrasena Recuperada");
                } else {
                    callBackInteractor.error(task.getException().getMessage());
                }

            }
        });

    }
}
