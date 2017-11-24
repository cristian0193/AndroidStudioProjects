package cv.yeison.appmensajeria.dominio;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cv.yeison.appmensajeria.modelo.FirebaseHelper;
import cv.yeison.appmensajeria.modelo.Usuario;

/**
 * Created by andres on 23/11/2017.
 */

public class LUsuario implements ILUsuario{

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference refUsuarios;

    public LUsuario() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        refUsuarios = firebaseDatabase.getReference(FirebaseHelper.PERFIL);
    }

    @Override
    public void crearUsuario(String password, final Usuario usuario, final CallBackInteractor<String> callBackInteractor) {
        firebaseAuth.createUserWithEmailAndPassword(usuario.getEmail(), password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            usuario.setUid(task.getResult().getUser().getUid());

                            //String user = usuario.getEmail();
                            //user = user.replace(".","");

                            refUsuarios.child(FirebaseHelper.USUARIOS).child(task.getResult().getUser().getUid()).setValue(usuario);

                            callBackInteractor.success(usuario.getNombres());
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
                            refUsuarios.child(FirebaseHelper.USUARIOS).child(task.getResult().getUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                                    callBackInteractor.success(usuario.getNombres());
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
