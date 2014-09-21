package com.timesheet.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.timesheet.constants.ExceptionCodeConstant;
import com.timesheet.exceptions.ValidationException;

public class PasswordHasher {

	public static String getPasswordHash(String password) {
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        	throw new ValidationException(ExceptionHandler.getExceptionbean(ExceptionCodeConstant.PASSWORD_HASHING_FAILED, "Unable to Hash password."));
        }
	}
}
