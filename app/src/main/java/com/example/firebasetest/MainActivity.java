package com.example.firebasetest;

import static android.content.ContentValues.TAG;

//import android.app.KeyguardManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
//import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirestoreRegistrar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
//import com.google.firebase.ktx.Firebase;
import android.util.ArrayMap;
import java.time.LocalDate;
import java.util.HashMap;
//import java.util.Map;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import android.os.Bundle;


//sssss
public class MainActivity extends AppCompatActivity {




    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        //need start,end,name.duration,description
        HashMap<String, Object> user = new HashMap<>();
        user.put("firstName", "Easy");
        user.put("lastName", "Hard");
        user.put("description", "PLs Work");





        //add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        //Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task){
                        if (task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        }
                        else{
                            Log.w(TAG, "Error getting documents", task.getException());
                        }
                    }
                });





        initWidgets();
        LocalDate selectedDate = LocalDate.now();
        setMonthView();
    }

    private void addOnCompleteListener(OnCompleteListener<QuerySnapshot> querySnapshotOnCompleteListener) {

    }

    private void initWidgets() {
    }

    private void setMonthView(){
    }
}
