package denisleonov.fit.bstu.by.db_lab_8;

import android.content.Context;
import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlSerializer;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import denisleonov.fit.bstu.by.db_lab_8.Entity.Task;

public class XMLHelper {
    String filename;

    public XMLHelper(String filename) {
        this.filename = filename;
    }

    public void writeNoteToInternal(Context context, List<Task> dataList) {

        FileOutputStream fos;
        try {
            fos = context.openFileOutput(filename, Context.MODE_PRIVATE);

            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(fos, "UTF-8");
            serializer.startDocument(null, Boolean.valueOf(true));
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

            serializer.startTag(null, "root");

            for (int j = 0; j < dataList.size(); j++) {
                serializer.startTag(null, "note");

                serializer.startTag(null, "value");
                serializer.text(dataList.get(j).getValue());
                serializer.endTag(null, "value");

                serializer.startTag(null, "category");
                serializer.text(dataList.get(j).getCategory());
                serializer.endTag(null, "category");

                serializer.startTag(null, "date");
                serializer.text(dataList.get(j).getDate());
                serializer.endTag(null, "date");

                serializer.endTag(null, "note");
            }
            serializer.endTag(null, "root");
            serializer.endDocument();
            serializer.flush();

            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Task> readNoteFromInternal(Context context) {
        List<Task> arrayResult = new ArrayList<>();
        FileInputStream fis = null;
        InputStreamReader isr = null;
        try {
            fis = context.openFileInput(filename);
            isr = new InputStreamReader(fis);

            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);

            String data;
            data = new String(inputBuffer);

            isr.close();
            fis.close();

            /*
             * Converting the String data to XML format so
             * that the DOM parser understands it as an XML input.
             */

            InputStream is = new ByteArrayInputStream(data.getBytes("UTF-8"));

            DocumentBuilderFactory dbf;
            DocumentBuilder db;
            NodeList items = null;
            Document dom;

            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            dom = db.parse(is);

            // Normalize the document
            dom.getDocumentElement().normalize();

            items = dom.getElementsByTagName("note");

            for (int i = 0; i < items.getLength(); i++) {
                Task note = new Task();
                Node item = items.item(i);
                NodeList parametres = item.getChildNodes();
                for (int j = 0; j < parametres.getLength(); j++) {
                    Node parametr = parametres.item(j);
                    if (parametr.getNodeName().equals("value"))
                        note.setValue(parametr.getFirstChild().getNodeValue());
                    if (parametr.getNodeName().equals("category"))
                        note.setCategory(parametr.getFirstChild().getNodeValue());
                    if (parametr.getNodeName().equals("date"))
                        note.setDate(parametr.getFirstChild().getNodeValue());

                }
                arrayResult.add(note);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayResult != null ? arrayResult : new ArrayList<>();
    }
}
