/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.collections.ObservableList;
import javax.net.ssl.HttpsURLConnection;
/**
 *
 * @author Jesus is Lord
 */
public class Request {
    private final String USER_AGENT = "Mozilla/5.0";
    private HttpsURLConnection connexion;
    private String boundary;
    private String encapsulationBoundary;
    private OutputStream outputStream;
    

    private String getParams(Map params) throws UnsupportedEncodingException{
        StringBuffer stringParams = new StringBuffer();
        if (params.keySet().size() > 0) {
            boolean isFirstParam = true;
            for (Iterator paramIter = params.keySet().iterator();paramIter.hasNext();){
                String paramStr = (String)paramIter.next();
                if (isFirstParam) {
                    stringParams.append("" + paramStr);
                    isFirstParam = false;
                } else {
                    stringParams.append("&" + paramStr);
                }
                stringParams.append("=" +
                URLEncoder.encode((String)params.get(paramStr),"UTF-8"));
            }
        }
        return stringParams.toString();
    }
    public void getFile(String host,Map params,String name) throws Exception
    {
        InputStream input = null;
        FileOutputStream writeFile = null;

        try
        {
            URL url = new URL(host);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            //add reuqest header
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            // Send post request
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(getParams(params));
            wr.flush();
            wr.close();
            int fileLength = connection.getContentLength();

            if (fileLength == -1)
            {
                System.out.println("Invalide URL or file.");
                return ;
            }
            
            input = connection.getInputStream();
            writeFile = new FileOutputStream(name);
            byte[] buffer = new byte[1024];
            int read;

            while ((read = input.read(buffer)) > 0)
                writeFile.write(buffer, 0, read);
            writeFile.flush();
        }
        catch (IOException e)
        {
            System.out.println("Error while trying to download the file.");
            throw new Exception("Une erreur s'est produite lord du téléchargement du fichier");
        }
        finally
        {
            try
            {
                writeFile.close();
                input.close();
            }
            catch (IOException e)
            {
                throw new Exception("Une erreur interne s'est produite");
            }
        }
        
    }
    public byte[] getFile(String host,Map params) throws Exception
    {
        InputStream input = null;
        FileOutputStream writeFile = null;

        try
        {
            URL url = new URL(host);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            //add reuqest header
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            // Send post request
            connection.setDoOutput(true);
            //con.setConnectTimeout(2000);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(getParams(params));
            wr.flush();
            wr.close();
            int fileLength = connection.getContentLength();

            if (fileLength == -1)
            {
                System.out.println("Invalide URL or file.");
                return null;
            }
            
            input = connection.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //writeFile = new FileOutputStream(name);
            byte[] buffer = new byte[1024];
            int read;

            while ((read = input.read(buffer)) > 0)
                out.write(buffer, 0, read);
            out.flush();
            return out.toByteArray();
        }
        catch (IOException e)
        {
            System.out.println("Error while trying to download the file.");
        }
        finally
        {
            try
            {
                input.close();
            }
            catch (IOException e)
            {
            }
        }
        return null;
    }

    public  void send(String url, Map params) throws IOException {
        String charset = "UTF-8";
        try {
            MultipartUtility  multipart = new MultipartUtility (url, charset);

            multipart.addHeaderField("User-Agent", "CodeJava");
            multipart.addHeaderField("Test-Header", "Header-Value");

            multipart.addFormField("description", "Cool Pictures");
            multipart.addFormField("keywords", "Java,upload,Spring");

            if (params.keySet().size() > 0) {
                for (Iterator paramIter = params.keySet().iterator();paramIter.hasNext();){
                    String paramStr = (String)paramIter.next();
                    multipart.addFormField(paramStr,(String)params.get(paramStr));
                }
            }
            /*multipart.addFilePart("fileUpload", uploadFile1);
            multipart.addFilePart("fileUpload", uploadFile2);*/

            List<String> response = multipart.finish();

            System.out.println("SERVER REPLIED:");

            for (String line : response) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    
    protected boolean addFile(String fieldName, String filePath, int zoneId, long fileLength,Map params) throws Exception
    {
        File fileToPost = new File(filePath);
        InputStream inputStream = new FileInputStream(fileToPost);
 
        this.writeData(this.encapsulationBoundary);
        this.writeData(this.getFileHeader(fieldName, filePath));
        addFormField(params);
        byte[] buf = new byte[1024];
        int nread, total=0;
        synchronized (inputStream)
        {
          while((nread = inputStream.read(buf, 0, buf.length)) >= 0)
          {
            total += nread;
            this.outputStream.write(buf, 0, nread);
            switch(zoneId)
            {
                case 1:
                    //interscanJava.apercu.setTransfert1(DocumentReader.convertOctet(total) + "/" + DocumentReader.convertOctet(fileLength) + " envoyés");
                    break;
                case 2:
                    //interscanJava.apercu.setTransfert2(DocumentReader.convertOctet(total) +"/" + DocumentReader.convertOctet(fileLength) + " envoyés");
                    break;
                default:
                    break;
            }
          }
        }
        this.outputStream.flush();
        buf = null;
 
        this.writeData("");
 
        return true;
    }
 
    protected String getFileHeader(String fieldName, String filePath) throws Exception
    {
        String contentDisposition = "Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + filePath + "\"";
        String contentType = "Content-Type:application/pdf";
        String fileData = contentDisposition + "\r\n" + contentType + "\r\n";
 
        return fileData;
    }
 
    protected boolean writeData(String data) throws Exception
    {
        String toWrite = data + "\r\n";
        this.outputStream.write(toWrite.getBytes());
 
        return true;
    }
 
    private boolean buildBoundary()
    {
        String boundaryId = "";
 
        for(int i=0 ; i<3 ; i++)
            boundaryId = boundaryId + this.buildBoundaryPart();
 
        this.boundary = "---------------------------" + boundaryId;
        this.encapsulationBoundary = "--" + this.boundary;
 
        return true;
    }
 
    private String buildBoundaryPart()
    {
        Random random = new Random();
 
        return Long.toString(random.nextLong(), 36);
    }
    
    public void addFormField(Map params) throws UnsupportedEncodingException, Exception {
       String data="";
        if (params.keySet().size() > 0) {
            boolean isFirstParam = true;
            for (Iterator paramIter = params.keySet().iterator();paramIter.hasNext();){
                String paramStr = (String)paramIter.next();
                this.writeData(this.encapsulationBoundary);
                //data+="--" + boundary+"\r\n";
                data+="Content-Disposition: form-data; name=\"" + paramStr + "\""+"\r\n";
                //data+="Content-Type: text/plain; charset=\"UTF-8\""+"\r\n";
                data+=URLEncoder.encode((String)params.get(paramStr),"UTF-8");
                writeData(data);
            }
        }
        
    }
}
