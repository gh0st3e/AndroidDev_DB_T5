package dl.fit.bstu.by.db_lab_16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactInfo extends AppCompatActivity {

    private EditText InfoName_ET;
    private EditText InfoPhone_ET;

    private ContactEntity Contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        InfoName_ET = findViewById(R.id.infoName);
        InfoPhone_ET = findViewById(R.id.infoPhone);

        Bundle arguments = getIntent().getExtras();
        Contact = (ContactEntity) arguments.get("Contact");

        setInfo();

    }

    private void setInfo(){
        InfoName_ET.setText(Contact.Name);
        InfoPhone_ET.setText(Contact.Phone);
    }

    public void deleteBtn(View view) {
        final ArrayList ops = new ArrayList();
        final ContentResolver cr = getContentResolver();
        ops.add(ContentProviderOperation
                .newDelete(ContactsContract.RawContacts.CONTENT_URI)
                .withSelection(ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{Contact.ID})
                .build());

        try {
            cr.applyBatch(ContactsContract.AUTHORITY, ops);
            ops.clear();
            this.finish();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
         catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void updateBtn(View view) {
        String newName = InfoName_ET.getText().toString();
        String newPhone = InfoPhone_ET.getText().toString();

        if(newName.length()<1){
            Toast.makeText(this, "Введите имя длиной больше 1 буквы", Toast.LENGTH_LONG).show();
            return;
        }
        if(newPhone.length()<3){
            Toast.makeText(this, "Введите номер длиной больше 3 цифр", Toast.LENGTH_LONG).show();
            return;
        }

        final ArrayList ops = new ArrayList();
        final ContentResolver cr = getContentResolver();
        ops.add(ContentProviderOperation
                .newDelete(ContactsContract.RawContacts.CONTENT_URI)
                .withSelection(ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{Contact.ID})
                .build());
        try {
            cr.applyBatch(ContactsContract.AUTHORITY, ops);
            ops.clear();
            this.finish();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }

        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, newName)
                .build());

        ops.add(ContentProviderOperation.
                newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, newPhone)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build());

        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ContactInfo.this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}