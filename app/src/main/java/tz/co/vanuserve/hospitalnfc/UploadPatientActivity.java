package tz.co.vanuserve.hospitalnfc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UploadPatientActivity extends AppCompatActivity {

    FirebaseFirestore db;
    TextInputEditText name,disease,room;
    Button btn, btn_nfc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_patient);
        btn=findViewById(R.id.btn_save_patient);
        btn_nfc=findViewById(R.id.btn_scan_nfc);
        name=findViewById(R.id.name_patient);
        disease=findViewById(R.id.disease_patient);
        room=findViewById(R.id.room_patient);
        db=FirebaseFirestore.getInstance();

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle("Add Patient");
        }

        btn_nfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UploadPatientActivity.this,NFCActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty()||disease.getText().toString().isEmpty()||room.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please All Fields",Toast.LENGTH_SHORT).show();
                }else {
                    createPatient(name.getText().toString(), disease.getText().toString(), room.getText().toString(), db);
                    name.getText().clear();
                    disease.getText().clear();
                    room.getText().clear();
                }
            }
        });

    }

    private void createPatient(String name, String disease, String room, FirebaseFirestore db) {
        Map<String, Object> patient = new HashMap<>();
        patient.put("name", name);
        patient.put("disease", disease);
        patient.put("room", room);

        db.collection("patients")
                .add(patient)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"document added",Toast.LENGTH_SHORT).show();
                        notification();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void notification(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("n","n", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"n")
                .setContentTitle("Hospital System")
                .setSmallIcon(R.drawable.ic_baseline_add_24)
                .setAutoCancel(true)
                .setContentText("Patient added to Database");
        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(this);
        managerCompat.notify(999,builder.build());
    }
}