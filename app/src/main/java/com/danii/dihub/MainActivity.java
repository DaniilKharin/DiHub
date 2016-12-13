package com.danii.dihub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText editTextName;

    SharedPreferences historyShP;

    Context cont;
    ListView listViewHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle savedIS = savedInstanceState;
        setContentView(R.layout.activity_main);
        final String[] resTypes = getResources().getStringArray(R.array.resTypes);
        final String[] realResTypes = {"all", "owner", "member"};
        cont = this;
        final Spinner resTypeSpinner = (Spinner) findViewById(R.id.resTypeSpinner);
        ArrayAdapter<String> spinerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, resTypes);

        listViewHistory = (ListView) findViewById(R.id.listViewHistory);
        final ArrayList<String> listHistory = new ArrayList<String>();
        ArrayAdapter<String> historyListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listHistory);
        listViewHistory.setAdapter(historyListAdapter);
        listViewHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(cont, ReposActivity.class);
                intent.putExtra("username", listHistory.get(position).substring(0, listHistory.get(position).lastIndexOf('|')));
                intent.putExtra("reptypes", listHistory.get(position).substring(listHistory.get(position).lastIndexOf('|') + 1));
                editTextName.setText("");
                startActivity(intent);
            }
        });

        spinerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        resTypeSpinner.setAdapter(spinerAdapter);
        editTextName = (EditText) findViewById(R.id.editTextName);
        Button openButton = (Button) findViewById(R.id.buttonOpen);
        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextName.getText().toString().length() > 0) {
                    historyShP = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor historyShPed = historyShP.edit();
                    String username;
                    username = editTextName.getText().toString();
                    String resType = realResTypes[(int) resTypeSpinner.getSelectedItemId()];
                    if (historyShP.contains("firstName")) {
                        historyShPed.putString("secondName", historyShP.getString("firstName", "null"));
                        historyShPed.putString("secondType", historyShP.getString("firstType", "null"));
                    }
                    historyShPed.putString("firstName", username);
                    historyShPed.putString("firstType", resType);
                    historyShPed.apply();

                    Intent intent = new Intent(cont, ReposActivity.class);
                    intent.putExtra("username", editTextName.getText().toString());
                    intent.putExtra("reptypes", realResTypes[(int) resTypeSpinner.getSelectedItemId()]);
                    editTextName.setText("");
                    startActivity(intent);
                } else
                    Toast.makeText(MainActivity.this, "Имя пользователя не должно быть пустым", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void onStart() {

        super.onStart();
        if (listViewHistory.getAdapter() != null) {
            @SuppressWarnings("unchecked")
            ArrayAdapter<String> strArrAdapter = (ArrayAdapter<String>) listViewHistory.getAdapter();
            historyShP = getPreferences(MODE_PRIVATE);
            if (historyShP.contains("firstName")) {
                if (strArrAdapter.getCount() > 0)
                    strArrAdapter.remove(strArrAdapter.getItem(0));
                strArrAdapter.insert(historyShP.getString("firstName", "null").concat("|").concat(historyShP.getString("firstType", "null")), 0);
            }
            if (historyShP.contains("secondName")) {
                if (strArrAdapter.getCount() > 1)
                    strArrAdapter.remove(strArrAdapter.getItem(1));
                strArrAdapter.insert(historyShP.getString("secondName", "null").concat("|").concat(historyShP.getString("secondType", "null")), 1);
            }
            strArrAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        editTextName = (EditText) findViewById(R.id.editTextName);
        savedInstanceState.putString("userName", editTextName.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

}



