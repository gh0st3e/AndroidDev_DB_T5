package denisleonov.fit.bstu.by.db_lab_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import android.widget.TextView;

import java.io.File;



public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void btnGetFilesDir(View view) {
        File gtf = getFilesDir();

        TextView absolute = findViewById(R.id.absolute);
        TextView name = findViewById(R.id.name);
        TextView path = findViewById(R.id.path);
        TextView readOrWrite = findViewById((R.id.readorwrite));
        TextView external = findViewById(R.id.external);

        absolute.setText(String.format("Absolute: %s", gtf.getAbsolutePath()));
        name.setText(String.format("Name: %s",gtf.getName()));
        path.setText(String.format("Path: %s",gtf.getPath()));
        readOrWrite.setText(String.format("Read/Write: %s/%s",gtf.canRead(),gtf.canWrite()));
        external.setText(String.format("External: %s",Environment.getExternalStorageState()));
    }

    public void btnGetCahcheDir(View view) {
        File gtf = getCacheDir();

        TextView absolute = findViewById(R.id.absolute);
        TextView name = findViewById(R.id.name);
        TextView path = findViewById(R.id.path);
        TextView readOrWrite = findViewById((R.id.readorwrite));
        TextView external = findViewById(R.id.external);

        absolute.setText(String.format("Absolute: %s", gtf.getAbsolutePath()));
        name.setText(String.format("Name: %s",gtf.getName()));
        path.setText(String.format("Path: %s",gtf.getPath()));
        readOrWrite.setText(String.format("Read/Write: %s/%s",gtf.canRead(),gtf.canWrite()));
        external.setText(String.format("External: %s",Environment.getExternalStorageState()));
    }

    public void btnGetExtFilesDir(View view) {
        File gtf = super.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

        TextView absolute = findViewById(R.id.absolute);
        TextView name = findViewById(R.id.name);
        TextView path = findViewById(R.id.path);
        TextView readOrWrite = findViewById((R.id.readorwrite));
        TextView external = findViewById(R.id.external);

        absolute.setText(String.format("Absolute: %s", gtf.getAbsolutePath()));
        name.setText(String.format("Name: %s",gtf.getName()));
        path.setText(String.format("Path: %s",gtf.getPath()));
        readOrWrite.setText(String.format("Read/Write: %s/%s",gtf.canRead(),gtf.canWrite()));
        external.setText(String.format("External: %s",Environment.getExternalStorageState()));
    }

    public void getExtCacheDir(View view) {
        File gtf = getExternalCacheDir();

        TextView absolute = findViewById(R.id.absolute);
        TextView name = findViewById(R.id.name);
        TextView path = findViewById(R.id.path);
        TextView readOrWrite = findViewById((R.id.readorwrite));
        TextView external = findViewById(R.id.external);

        absolute.setText(String.format("Absolute: %s", gtf.getAbsolutePath()));
        name.setText(String.format("Name: %s",gtf.getName()));
        path.setText(String.format("Path: %s",gtf.getPath()));
        readOrWrite.setText(String.format("Read/Write: %s/%s",gtf.canRead(),gtf.canWrite()));
        external.setText(String.format("External: %s",Environment.getExternalStorageState()));
    }

    public void btnGetExtStorDir(View view) {
        TextView absolute = findViewById(R.id.absolute);
        TextView name = findViewById(R.id.name);
        TextView path = findViewById(R.id.path);
        TextView readOrWrite = findViewById((R.id.readorwrite));
        TextView external = findViewById(R.id.external);

        File gtf = Environment.getExternalStorageDirectory();

        String m = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(m)){
            external.setText(String.format("External: %s",Environment.getExternalStorageState()));
        }
        else{
            external.setText(String.format("External: %s",m));
        }




        absolute.setText(String.format("Absolute: %s", gtf.getAbsolutePath()));
        name.setText(String.format("Name: %s",gtf.getName()));
        path.setText(String.format("Path: %s",gtf.getPath()));
        readOrWrite.setText(String.format("Read/Write: %s/%s",gtf.canRead(),gtf.canWrite()));

    }

    public void btnGetExtStorPublDir(View view) {
        File gtf = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        TextView absolute = findViewById(R.id.absolute);
        TextView name = findViewById(R.id.name);
        TextView path = findViewById(R.id.path);
        TextView readOrWrite = findViewById((R.id.readorwrite));
        TextView external = findViewById(R.id.external);

        String m = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(m)){
            external.setText(String.format("External: %s",Environment.getExternalStorageState()));
        }
        else{
            external.setText(String.format("External: %s",m));
        }

        absolute.setText(String.format("Absolute: %s", gtf.getAbsolutePath()));
        name.setText(String.format("Name: %s",gtf.getName()));
        path.setText(String.format("Path: %s",gtf.getPath()));
        readOrWrite.setText(String.format("Read/Write: %s/%s",gtf.canRead(),gtf.canWrite()));
        ;
    }
}