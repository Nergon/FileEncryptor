package pw.nergon.fileencryptor;

import javafx.scene.control.Alert;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;

public class FileHandler {

    private File file;
    private String mode;
    private String password;

    public FileHandler(String path, String mode, String password)  {
        file = new File(path);
        this.mode = mode;
        this.password = password;
    }

    public void switchMode() {
        switch (mode) {
            case "enc":
                processFile(Cipher.ENCRYPT_MODE, file.getAbsolutePath()+".enc");
                break;
            case "dec":
                processFile(Cipher.DECRYPT_MODE, file.getAbsolutePath().replaceAll(".enc",""));
                break;
        }
    }

    private void processFile(int cipherMode, String outFile) {
        try (FileInputStream inputStream = new FileInputStream(file);
             FileOutputStream outputStream = new FileOutputStream(new File(outFile))) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            Key secretKey = new SecretKeySpec(hash, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cipherMode, secretKey);


            byte[] inputBytes = new byte[(int) file.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);


            outputStream.write(outputBytes);

            outputStream.flush();
            outputStream.close();

            inputStream.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Your file was en-/decrypted");
            alert.setContentText("Your file was en-/decrypted. Please make sure you save your password. Otherwise the file will not be decryptable");
            alert.showAndWait();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File not found or not readable");
            alert.setHeaderText("File not found or not readable");
            alert.setContentText("Found IO-Exception. Maybe the File is not existing or you don't have the permission to read it");
            alert.showAndWait();
            e.printStackTrace();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unexpected Error");
            alert.setHeaderText("Unexpected Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

}
