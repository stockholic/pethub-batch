package kr.pethub;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import kr.pethub.core.constants.SystemConstants;


public class PropertyEncrypt extends BaseTestCase{
	
	 private String USER = "shkr";
	 private String PASSWORD = "Wkwkdaus";
	 
	 private @Value("${deploy}") String deploy;
	
	@Test
	public void encryptTest() {
		
		logger.debug("-------------------------------------------------------------------------------> start");
		
		StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
		standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
		standardPBEStringEncryptor.setPassword(SystemConstants.ENCRYPT_KEY);
		
		logger.info("deploy  : "+ deploy);
		logger.info("Encrypted user  : "+ standardPBEStringEncryptor.encrypt(USER));
		logger.info("Encrypted password  : "+ standardPBEStringEncryptor.encrypt(PASSWORD));
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
}
