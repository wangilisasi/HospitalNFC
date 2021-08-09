package tz.co.vanuserve.hospitalnfc;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.messaging.FirebaseMessaging;

import tz.co.vanuserve.hospitalnfc.adapters.PatientTestViewHolder;
import tz.co.vanuserve.hospitalnfc.models.PatientModelTest;
import tz.co.vanuserve.hospitalnfc.utils.VerticalSpacingItemDecorator;

public class RecyclerActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;


    FirestoreRecyclerAdapter<PatientModelTest, PatientTestViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycer);

        //set title
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle("Patient List");
        }

        //Subscribe to a topic
        FirebaseMessaging.getInstance().subscribeToTopic("doctors")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg="Subscribed";
                        if (!task.isSuccessful()){
                            msg="Not subscribed";
                        }
                        Toast.makeText(RecyclerActivity.this,msg,Toast.LENGTH_LONG).show();
                    }
                });

        FloatingActionButton floatingActionButton=findViewById(R.id.add_fab_test);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RecyclerActivity.this,UploadPatientActivity.class);
                startActivity(intent);
            }
        });
        Query query= FirebaseFirestore.getInstance() .collection("patients");
        Query queryDoctor= FirebaseFirestore.getInstance() .collection("doctors");




        FirestoreRecyclerOptions<PatientModelTest> options = new FirestoreRecyclerOptions.Builder<PatientModelTest>()
                .setQuery(query, PatientModelTest.class)
                .build();
        adapter= new FirestoreRecyclerAdapter<PatientModelTest, PatientTestViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PatientTestViewHolder holder, int position, @NonNull PatientModelTest model) {
                holder.name.setText("Name: "+model.getName());
                holder.disease.setText("Disease: "+model.getDisease());
                holder.room.setText("Room: "+model.getRoom());
                holder.doctor.setText("Doctor: "+"Eliud Ihare");
            }

            @NonNull
            @Override
            public PatientTestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.patient_item_test, parent, false);

                return new PatientTestViewHolder(view);
            }
        };

        mRecyclerView = findViewById(R.id.recycler_test);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        VerticalSpacingItemDecorator itemDecorator=new VerticalSpacingItemDecorator(15);
        mRecyclerView.addItemDecoration(itemDecorator);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}