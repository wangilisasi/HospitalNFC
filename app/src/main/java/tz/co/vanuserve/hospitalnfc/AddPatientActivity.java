package tz.co.vanuserve.hospitalnfc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddPatientActivity extends AppCompatActivity {

    EditText editText;
    Button btn;
    int id=0;

    FirebaseDatabase database;
    DatabaseReference reference;

    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pateint);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle("Add Patient");
        }

        member=new  Member();
        btn=findViewById(R.id.btn_save);
        editText=findViewById(R.id.name);

        reference=database.getInstance().getReference().child("Patient");
        reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               if (snapshot.exists()){
                   id=(int)snapshot.getChildrenCount();
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
       reference.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               notification();
           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String name=editText.getText().toString();

                if(name.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please fill",Toast.LENGTH_SHORT).show();
                }else{
                    notification();
                }

           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot snapshot) {
               notification();
           }

           @Override
           public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String name=editText.getText().toString();

               if(name.isEmpty()){
                   Toast.makeText(getApplicationContext(),"please fill",Toast.LENGTH_SHORT).show();
               }else{
                   member.setName(editText.getText().toString());
                   reference.child(String.valueOf(id+1)).setValue(member);
               }


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
                .setContentText("Data changed on DB");
        NotificationManagerCompat  managerCompat=NotificationManagerCompat.from(this);
        managerCompat.notify(999,builder.build());
    }
}