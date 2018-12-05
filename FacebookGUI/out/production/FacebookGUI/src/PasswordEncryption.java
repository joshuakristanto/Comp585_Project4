import DAO.ProfilesDao;
import Models.Profiles;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Observable;

public class PasswordEncryption {



    // reset the password
    public static void resetPassword(String UserName, String newUserPassword) {

        // generate the salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

        // generate hash from salt and password

        byte[] hash = new byte[32];

           KeySpec spec = new PBEKeySpec(newUserPassword.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = null;

        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();
            System.out.println("hash in try is  " + hash);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        System.out.println("hash is now " + hash);
        System.out.println("hash in array form is " + Arrays.toString(hash) + "then this " + hash.toString());
        // prep salt and hash for storage
               byte store[] = new byte[32];
               System.arraycopy(salt, 0, store, 0, salt.length);
               System.arraycopy(hash, 0, store, 16, hash.length);



//// have to encode in base64
        System.out.println("store is " + store);
        String toStore = Base64.encode(store);
        System.out.println("from base64 " + Base64.decode(toStore));


        System.out.println("Store array " + Arrays.toString(store));
        System.out.println("from base 64 " + Arrays.toString(Base64.decode(toStore)));
        try {
            ProfilesDao.updatePassword(UserName, toStore);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }



    public static String generatePassword(String userInputedPassword) {


        // generate the salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        // generate hash from salt and password

        byte[] hash = new byte[32];

        KeySpec spec = new PBEKeySpec(userInputedPassword.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = null;

        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }


        // prep salt and hash for storage

        byte store[] = new byte[32];
        System.arraycopy(salt, 0, store, 0, salt.length);
        System.arraycopy(hash, 0, store, 16, hash.length);

        System.out.println("store is " + store);
        String toStore = Base64.encode(store);
        System.out.println("from base64 " + Base64.decode(toStore));


        System.out.println("Store array " + Arrays.toString(store));
        System.out.println("from base 64 " + Arrays.toString(Base64.decode(toStore)));




        return toStore;
    }



    public static byte[] retrievePassword(String UserName) {

        ObservableList<Profiles> prof = FXCollections.observableArrayList();
        try {
            prof = ProfilesDao.searchProfiles(UserName);
            System.out.println("prof in try is " + prof.get(0).getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (prof.isEmpty()) {
            System.out.println("size was less than 1 returning an empty array");
            byte[] emotyArr = new byte[32];
            return emotyArr;
        } else {

            // get password string and convert it to a byte array
            String userPassword = prof.get(0).getPassword();
            byte[] passwordByteArr = Base64.decode(userPassword);

            return passwordByteArr;
        }

    }


/////////// returns a boolean value to indicate if passwords were the same
    public static boolean checkPassword(String UserName, String userInputedPassword) {

    // get the stored password
        byte[] retrievedPasswordArr = retrievePassword(UserName);

    // get the salt from the stored password
        byte[] retSalt = new byte[16];
        System.arraycopy(retrievedPasswordArr, 0, retSalt, 0, retSalt.length);

        byte[] storedHash = new byte[16];
        System.arraycopy(retrievedPasswordArr, 16, storedHash, 0, storedHash.length);


        // hash the inputedpassword with the retrieved salt
        byte[] hash = new byte[32];

        KeySpec spec = new PBEKeySpec(userInputedPassword.toCharArray(), retSalt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }


        // compare hashes


        if(Arrays.toString(hash).equals(Arrays.toString(storedHash))) {
            return true;
        } else {
            return false;
        }



    }










}





