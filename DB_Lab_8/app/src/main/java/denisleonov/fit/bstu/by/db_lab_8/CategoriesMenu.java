package denisleonov.fit.bstu.by.db_lab_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CategoriesMenu extends AppCompatActivity {

    private ListView categoriesList;
    private EditText textField;

    private List<String> categories;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_menu);

        loadList();

        textField = findViewById(R.id.catName);

        categoriesList = findViewById(R.id.categoriesList);
        categoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Button editBtn = findViewById(R.id.editCat);
                Button delBtn = findViewById(R.id.delCat);

                editBtn.setEnabled(true);
                delBtn.setEnabled(true);

                String pickedCat =categories.get(position);
                textField.setText(pickedCat);
                textField.setEnabled(false);
            }
        });
    }

    private void loadList(){
        categories = null;
        categories = JSONHelper.importFromJSON(this);
        if(categories == null){
            categories = new ArrayList<>();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        ListView listView = findViewById(R.id.categoriesList);
        listView.setAdapter(adapter);
    }

    private boolean isRepeat(List<String> categories, String name){
        for(String category: categories){
            if(category.equals(name)){
                return true;
            }
        }
        return false;
    }

    public void addCatBtn(View view) {
        textField = findViewById(R.id.catName);
        String name = textField.getText().toString();

        if(name.length()<1){
            Toast.makeText(this, "Введите нормальное название", Toast.LENGTH_LONG).show();
            return;
        }

        categories = JSONHelper.importFromJSON(this);
        if(categories == null){
            categories = new ArrayList<>();
        }

        if(!isRepeat(categories,name) && categories.size()<5){
            categories.add(name);
            JSONHelper.exportToJSON(this,categories);
            textField.setText("");
            loadList();
        }else{
            Toast.makeText(this, "Такая категория уже есть или первышен лимит категорий(5)", Toast.LENGTH_LONG).show();
        }
    }

    public void editCatBtn(View view) {
        textField = findViewById(R.id.catName);
        String name = textField.getText().toString();
        textField.setEnabled(true);

        Button saveBtn = findViewById(R.id.saveCat);
        saveBtn.setEnabled(true);

        if(name.length()<1){
            Toast.makeText(this, "Введите нормальное название", Toast.LENGTH_LONG).show();
            return;
        }

        categories = JSONHelper.importFromJSON(this);
        if(categories == null){
            categories = new ArrayList<>();
        }

        for(String category: categories){
            if(category.equals(name)){
                categories.remove(category);
                break;
            }
        }
    }
    public void saveCatBtn(View view) {
        textField = findViewById(R.id.catName);
        String name = textField.getText().toString();

        if(name.length()<1){
            Toast.makeText(this, "Введите нормальное название", Toast.LENGTH_LONG).show();
            return;
        }

        if(!isRepeat(categories,name) && categories.size()<5) {
            categories.add(name);
            JSONHelper.exportToJSON(this,categories);
            textField.setText("");
            setFalseEnabled();
            loadList();
        }
        else{
            Toast.makeText(this, "Такая категория уже есть или первышен лимит категорий(5)", Toast.LENGTH_LONG).show();
        }
    }

    public void delCatBtn(View view) {
        textField = findViewById(R.id.catName);
        String name = textField.getText().toString();

        categories = JSONHelper.importFromJSON(this);
        if(categories == null){
            categories = new ArrayList<>();
        }

        for(String category: categories){
            if(category.equals(name)){
                categories.remove(category);
                break;
            }
        }

        textField = findViewById(R.id.catName);
        textField.setEnabled(true);

        JSONHelper.exportToJSON(this,categories);
        textField.setText("");
        setFalseEnabled();
        loadList();
    }


    private void setFalseEnabled(){
        Button editBtn = findViewById(R.id.editCat);
        Button delBtn = findViewById(R.id.delCat);
        Button saveBtn = findViewById(R.id.saveCat);

        editBtn.setEnabled(false);
        delBtn.setEnabled(false);
        saveBtn.setEnabled(false);
    }
}