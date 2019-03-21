package com.example.simplecarlist;

import android.app.Dialog;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;


import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    Dialog d;
    private static final String TAG = "now";
    private SearchView sV;
    private Spinner s1;
    private List<Logic> list;
    private Adapter ad;
    private ListView listView;
    EditText e1;
    EditText e2;
    Spinner s3;
    Spinner s4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        d = new Dialog(this);
        sV = findViewById(R.id.SearchView);
        s1 = findViewById(R.id.spinner);
        listView = (ListView) findViewById(R.id.listView);
        loadApplication();
        setListViewAdapter();
        spinnerSetten();

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String x = (String) parent.getItemAtPosition(position);
                select(x);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUp(v);
            }
        });


        sV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                serach(s);
                return false;
            }
        });

    //writeCsv1();
    }

    //
    private InputStream getInputStreamForAsset(String filename) {
        Log.d(TAG, "getInputStreamForAsset: " + filename);
        AssetManager assets = getAssets();
        try {
            return assets.open(filename);
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
            return null;
        }
    }

    public void readAssets() {

        InputStream in = getInputStreamForAsset("cars.csv");
        BufferedReader bin = new BufferedReader(new InputStreamReader(in));
        String line;
        int c = 0;
        try {
            while ((line = bin.readLine()) != null) {
                if (c == 0) {
                    c = 1;
                    line = bin.readLine();
                }
                String[] arr = line.split(",");
                String firstName = arr[1] + "";
                String lastName = arr[2] + "";
                String make = arr[11] + "";
                String model = arr[12] + "";
                list.add(new Logic(firstName, lastName, make, model));
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }
    }

    public void setListViewAdapter() {
        ad = new Adapter(this, 0, list);
        listView.setAdapter(ad);
    }

    public void spinnerSetten() {
        String[] array = new String[list.size() + 1];
        array[0] = "all";
        for (int i = 1; i < list.size() + 1; i++) {
            array[i] = list.get(i - 1).getMake();
        }

        ArrayAdapter ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
        s1.setAdapter(ad);
    }

    public void select(String marke) {

        List<Logic> test = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getMake().equals(marke)) {
                test.add(list.get(i));
            }
            if (marke.equals("all")) {
                test = list;
            }
        }

        ad = new Adapter(this, 0, test);
        listView.setAdapter(ad);
    }

    public void serach(String search) {
        List<Logic> test2 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).myString().contains(search)) {
                test2.add(list.get(i));
                ad = new Adapter(this, 0, test2);
                listView.setAdapter(ad);
            }
        }
    }

    public void popUp(View v) {
        d.setContentView(R.layout.layout2);
        e1 = d.findViewById(R.id.editText1);
        e2 = d.findViewById(R.id.editText2);
        s3 = d.findViewById(R.id.spinnerMarke);
        s4 = d.findViewById(R.id.spinnerModel);
        spinner3Setten();
        spinner4Setten();
        Button button = d.findViewById(R.id.buttonOk);
        Button b2 = d.findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.cancel();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vn = e1.getText().toString();
                String nn = e2.getText().toString();
                String ma = s3.getSelectedItem().toString();
                String mo = s4.getSelectedItem().toString();
                Logic c = new Logic(vn,nn,ma,mo);
                list.add(c);
                writeCsvLast(c);
                d.cancel();
                setListViewAdapter();
            }
        });


        d.show();
    }

    public void spinner3Setten() {
        String[] array = new String[list.size()];


        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).getMake();
        }

        ArrayAdapter ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
        s3.setAdapter(ad);
    }

    public void spinner4Setten() {
        String[] array = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).getModel();
        }

        ArrayAdapter ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
        s4.setAdapter(ad);
    }

    public void loadApplication() {
        String filename = "MyCsv.csv";
        list.clear();
        readAssets();
        try {
            FileInputStream fis = openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;
            int c = 0;
            try {
                while ((line = br.readLine()) != null) {
                    if (c == 0) {
                        c = 1;
                        line = br.readLine();
                    }
                    String[] arr = line.split(",");
                    String firstName = arr[0] + "";
                    String lastName = arr[1] + "";
                    String marke = arr[2] + "";
                    String model = arr[3] + "";
                    list.add(new Logic(firstName, lastName, marke, model));
                }
            } catch (Exception e) {
                //readAssets();
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            //readAssets();
            System.out.println("Keine Datei gefunden!");
        }
    }

    public void writeCsv1()  {

        String filename = "MyCsv.csv";
        try
        {
            FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE | MODE_APPEND);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(fos));
            for (int i = 0; i < list.size(); i++) {
                out.println(list.get(i).writeString());
            }
            out.flush();
            out.close();


        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void writeCsvLast(Logic c)
    {
        String filename = "MyCsv.csv";
        try
        {
            FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE | MODE_APPEND);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(fos));
                out.println(c.writeString());
            out.flush();
            out.close();


        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }




    
}