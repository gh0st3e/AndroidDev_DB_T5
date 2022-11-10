package dl.fit.bstu.by.db_lab_16;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Entity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_READ_CONTACTS = 1;
    private static boolean READ_CONTACTS_GRANTED = false;

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    private ListView contactsLV;
    private ArrayList<ContactEntity> Contacts;
    private ContactEntity Contact;

    private ContentResolver contentResolver;

    private EditText contactName_ET;
    private EditText contactPhone_ET;

    private EditText searchStr_ET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String a = "contactsLV";

        contactsLV = findViewById(R.id.contactsLV);
        contentResolver = getContentResolver();
        Contacts = new ArrayList<ContactEntity>();

        contactName_ET = findViewById(R.id.newContactName);
        contactPhone_ET = findViewById(R.id.newContactPhone);

        int hasReadContactPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        // если устройство до API 23, устанавливаем разрешение
        if (hasReadContactPermission == PackageManager.PERMISSION_GRANTED) {
            READ_CONTACTS_GRANTED = true;
        } else {
            // вызываем диалоговое окно для установки разрешений
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, REQUEST_CODE_READ_CONTACTS);
        }
        // если разрешение установлено, загружаем контакты
        if (READ_CONTACTS_GRANTED) {
            loadContacts();
        }

        contactsLV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Contact = Contacts.get(position); // по позиции получаем выбранный элемент
                loadContactInfo();
            }
        });

        searchStr_ET = findViewById(R.id.searchStr);
        searchStr_ET.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    // reset listview
                    loadContacts();
                } else {
                    // perform search
                    searchContact(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                READ_CONTACTS_GRANTED = true;
            }
        }
        if (READ_CONTACTS_GRANTED) {
            loadContacts();
        } else {
            Toast.makeText(this, "Требуется установить разрешения", Toast.LENGTH_LONG).show();
        }
    }

    private void loadContacts() {
        Contacts.clear();
        Cursor contactsCursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (contactsCursor != null) {
            while (contactsCursor.moveToNext()) {
                ContactEntity contact = new ContactEntity();

                String contactId = contactsCursor.getString(
                        contactsCursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID)
                );
                contact.setID(contactId);

                String contactName = contactsCursor.getString(
                        contactsCursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                contact.setName(contactName);

                int hasContactPhone = Integer.parseInt(contactsCursor.getString(
                        contactsCursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                ));
                if (hasContactPhone > 0) {
                    Cursor phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{contactId}, null);

                    while (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        contact.setPhoneNumber(phoneNumber);
                    }
                    phoneCursor.close();
                }

                Contacts.add(contact);
            }
            contactsCursor.close();
        }

        ArrayAdapter<ContactEntity> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, Contacts);

        contactsLV.setAdapter(adapter);
    }

    private void loadContactInfo(){
        try{
            Intent intent1 = new Intent(this, ContactInfo.class);
            intent1.putExtra("Contact",  Contact);
            startActivity(intent1);
        }
        catch (Exception e){
            Log.d("lab_04",e.getMessage());
        }
    }

    private void searchContact(String str){
        ArrayList<ContactEntity> foundContacts = new ArrayList<>();
        for(ContactEntity contact:Contacts){
            if(contact.Phone.contains(str)){
                foundContacts.add(contact);
            }
        }
        ArrayAdapter<ContactEntity> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, foundContacts);

        contactsLV.setAdapter(adapter);
    }


    public void newContactBtn(View view) {
        String name = contactName_ET.getText().toString();
        String phone = contactPhone_ET.getText().toString();

        if(name.length()<1){
            Toast.makeText(this, "Введите имя длиной больше 1 буквы", Toast.LENGTH_LONG).show();
            return;
        }
        if(phone.length()<3){
            Toast.makeText(this, "Введите номер длиной больше 3 цифр", Toast.LENGTH_LONG).show();
            return;
        }

        ArrayList <ContentProviderOperation> ops = new ArrayList<>();

        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
                .build());

        ops.add(ContentProviderOperation.
                newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build());

        try {
            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        updateListContacts();
    }

    private void updateListContacts(){
        loadContacts();
    }

    public void refreshBtn(View view) {
        updateListContacts();
    }
}