package org.AzureConnection;

import com.azure.storage.common.StorageSharedKeyCredential;
import com.azure.storage.file.datalake.DataLakeDirectoryClient;
import com.azure.storage.file.datalake.DataLakeFileClient;
import com.azure.storage.file.datalake.DataLakeFileSystemClient;
import com.azure.storage.file.datalake.DataLakeServiceClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Scanner;

public class azureLakeHeadConnection {
    public static String saveIndustryName;

    public static void runValue() throws IOException {
        azureLakeHeadConnection obj = new azureLakeHeadConnection();
        obj.main(new String[]{});
    }

    public static void main(String[] arg) throws IOException {
        // Define input parameters
        String accountName = "sutanustorage";
        String accountKey = "QXpjq+cemwT6m9YUV5foaqFTBvDBIHUoHSfegZiaqNsw7vai3qoi1TK/77oRzc7ITUQwDb77yuUk+ASt241S+Q==";
        String fileSystemName = "containertest";
        String directoryNAme = "https://sutanustorage.blob.core.windows.net/containertest/sample.json";
        String fileName = "sample.json";
        String saveIndustryNameMainMethod;

/*        String accountName = "rap7or";
        String accountKey = "yeE81QAj+D0IsHzBmVeMYE/9GiCD0OTEMs2OVh7GgSCAh9lviJsPt67O3awjz3zOHdVg51n8+YGO+AStwcHC5Q==";
        String fileSystemName = "tupperware";
        String directoryNAme = "https://rap7or.blob.core.windows.net/tupperware/azureDataConnection.java";
        String fileName = "azureDataConnection.java";*/

        //Creating Storage sharedCredential Object
        StorageSharedKeyCredential credential = new StorageSharedKeyCredential(accountName, accountKey);

        // Create a DataLakeFileSystemClient object using the credential
        DataLakeFileSystemClient fileSystemClient = null;
		try {
			fileSystemClient = new DataLakeServiceClientBuilder()
			        .endpoint(directoryNAme)
			        .credential(credential).buildClient()
			        .getFileSystemClient(fileSystemName);
		} catch (Exception e) {
			System.out.printf("Exception caught in creating new DatalakeServiceClientBuilder method due to :: "+e.getMessage(),e);;

		}

        System.out.println("Connection 1::"+fileSystemClient.getFileSystemName());
        System.out.println("Connection 2::"+fileSystemClient.getFileClient(fileName));
        System.out.println("Connection 3::"+fileSystemClient.getAccessPolicy());
        System.out.println("Connection 4::"+fileSystemClient.getProperties());

        // Create a DataLakeDirectoryClient object using the fileSystemClient and directory name
        DataLakeDirectoryClient directoryClient = fileSystemClient.getDirectoryClient(directoryNAme);

        // Create a DataLakeFileClient object using the directoryClient and file name
        DataLakeFileClient fileClient = directoryClient.getFileClient(fileName);

        System.out.println("File Created ::: "+fileClient);
        System.out.println("File Directory ::: "+directoryClient);

        //Download file
        DownloadFile(fileSystemClient);

        //Read File
        saveIndustryNameMainMethod = ReadFile();
    }

    public static void DownloadFile(DataLakeFileSystemClient fileSystemClient){
    	
        try {
			DataLakeDirectoryClient directoryClient =
			        fileSystemClient.getDirectoryClient("");
			DataLakeFileClient fileClient =
			        directoryClient.getFileClient("sample.json");
			File file = new File("C:\\Users\\MahipalSingh\\Documents\\downloadedFile.json");
			OutputStream targetStream = new FileOutputStream(file);
			fileClient.read(targetStream);
			targetStream.close();
		} catch (FileNotFoundException e) {
			System.out.printf("Exception caught in DownloadFile method due to :: "+e.getMessage(),e);;
		} catch (IOException e) {
			System.out.printf("IO Exception caught in DownloadFile method due to :: "+e.getMessage(),e);;

		}
    }

    public static String ReadFile(){
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("C:\\Users\\MahipalSingh\\Documents\\downloadedFile.json");
        try {
            json2PojoClass newObj = objectMapper.readValue(file, json2PojoClass.class);
            System.out.println(newObj.getInsideMaterials());
            String saveIndustryNameReadFile = newObj.getInsideMaterials(); //used to verify data.
            saveIndustryName = newObj.getInsideMaterials();
            File myObj = new File("C:\\Users\\MahipalSingh\\Documents\\downloadedFile.json");
            Scanner myReader = new Scanner(myObj);
            String data;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println(data.toString());
            }
            return saveIndustryNameReadFile;
        } catch (Exception e) {
			System.out.printf("Exception caught in ReadFile method due to :: "+e.getMessage(),e);;
            return null;
        }
    }
}
