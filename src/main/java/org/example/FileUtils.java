package org.example;

import java.io.*;

public class FileUtils {

    public static Object loadObject(String fileName) {
        Object returnObj = null;

        try {
            ObjectInputStream objInputStream = new ObjectInputStream(new FileInputStream(fileName));
            returnObj = objInputStream.readObject();

            objInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return returnObj;
    }
    public static void saveObject(Object object, String fileName) {
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            ObjectOutputStream objStream = new ObjectOutputStream(outputStream);

            objStream.writeObject(object);
            objStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
