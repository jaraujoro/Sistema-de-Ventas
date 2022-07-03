
package Utilidades.BcryptPassword;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Bcrypt {
        public String EncryptionPassword(String password) {//encripta  y devuelve contraseña 
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        return bcryptHashString;
    }

    public boolean desencriptionPassword(String password, String hashingpassword) { //verifica la contraseña ingresada con la base de datos 
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashingpassword);
        return result.verified;
    }
}
