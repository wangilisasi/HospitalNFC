package tz.co.vanuserve.hospitalnfc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import tz.co.vanuserve.hospitalnfc.adapters.PatientAdapter;
import tz.co.vanuserve.hospitalnfc.models.PatientModel;
import tz.co.vanuserve.hospitalnfc.utils.VerticalSpacingItemDecorator;

public class MainActivity extends AppCompatActivity implements PatientAdapter.PatientViewHolder.OnCategoryClickListener{

    List<PatientModel> patientModelList;
    PatientAdapter patientAdapter;
   RecyclerView patientRecyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab=findViewById(R.id.add_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AddPatientActivity.class);
                startActivity(intent);
            }
        });

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle("Patient List");
        }

        patientRecyclerView=findViewById(R.id.patient_recycler);

        //Set up data
        patientModelList =new ArrayList<>();
        patientModelList.add(new PatientModel(R.drawable.mohd,"Name: Ibrahim John","Room No: 100","Disease: Malaria","Doctor: Samwel John"));
        patientModelList.add(new PatientModel(R.drawable.nebi,"Name: John Isaya","Room No: 200","Disease: Diabetes","Doctor: Stanford John"));
        patientModelList.add(new PatientModel(R.drawable.patrick,"Name: Viktor Willa","Room No: 300","Disease: Corona","Doctor: Eliud Ihare"));
        patientModelList.add(new PatientModel(R.drawable.willa,"Name: Awadhi Bagoka","Room No: 400","Disease: Cholera","Doctor: Amunga Meda"));
        patientModelList.add(new PatientModel(R.drawable.jigwa,"Name: Abdul Mohammed","Room No: 500","Disease: Typhoid","Doctor: Fred Mpuya"));


        //Set up RecyclerView
        patientAdapter=new PatientAdapter(patientModelList,this);
        VerticalSpacingItemDecorator itemDecorator=new VerticalSpacingItemDecorator(15);
        patientRecyclerView.addItemDecoration(itemDecorator);
        patientRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        patientRecyclerView.setAdapter(patientAdapter);
    }

    @Override
    public void onCategoryClick(int position) {
       Intent intent=new Intent(this,RecyclerActivity.class);
       startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}