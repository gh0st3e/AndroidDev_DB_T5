package denisleonov.fit.bstu.by.db_lab_8;

import android.content.Context;
import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class JSONHelper {
    private static final String FILE_NAME = "categories.json";

    static boolean exportToJSON(Context context, List<String> dataList) {

        Gson gson = new Gson();
        DataItems dataItems = new DataItems();
        dataItems.setUsers(dataList);
        String jsonString = gson.toJson(dataItems);

        try(FileOutputStream fileOutputStream =
                    context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    static List<String> importFromJSON(Context context) {

        try(FileInputStream fileInputStream = context.openFileInput(FILE_NAME);

            InputStreamReader streamReader = new InputStreamReader(fileInputStream)){

            Gson gson = new Gson();
            DataItems dataItems = gson.fromJson(streamReader, DataItems.class);

            return  dataItems.getUsers();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return null;
    }

    private static class DataItems {
        private List<String> tasks;

        List<String> getUsers() {
            return tasks;
        }
        void setUsers(List<String> users) {
            this.tasks = users;
        }
    }
}
