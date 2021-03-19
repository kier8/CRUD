package net.decenternet.technicalexam.ui.tasks;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import net.decenternet.technicalexam.OfflineDatabase;
import net.decenternet.technicalexam.R;
import net.decenternet.technicalexam.domain.Task;

import java.util.List;

public class TasksActivity extends AppCompatActivity implements TasksContract.View {

    private TasksContract.Presenter presenter;
    private OfflineDatabase offlineDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button regButton = findViewById(R.id.regBtn);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TasksActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.activity_register, viewGroup, false);

                final EditText s_email = dialogView.findViewById(R.id.editTextTextEmailAddress);
                final EditText s_fullname = dialogView.findViewById(R.id.editTextTextPersonName);
                final EditText s_number = dialogView.findViewById(R.id.editUserNumber);

                builder.setPositiveButton(R.string.register, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String email = s_email.getText().toString();
                        String fname = s_fullname.getText().toString();
                        String num = s_number.getText().toString();

                        if (email.equals("") || fname.equals("") || num.equals("")){
                            Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();

                        } else {
                            offlineDatabase.insertDataUser(email, fname, num);
                        }
                    }
                });

                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

    }

    @Override
    public void displayTasks(List<Task> tasks) {

    }

    @Override
    public void addTaskToList(Task task) {

    }

    @Override
    public void removeTaskFromList(Task task) {

    }

    @Override
    public void updateTaskInList(Task task) {

    }

    @Override
    public void popupTaskAddingDialog() {

    }

    @Override
    public void popupTaskEditorDialog(Task task) {

    }

}
