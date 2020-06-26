package com.revature.config;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;

/**
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@Configuration
public class SheetsServiceConfig {

	// Generate a service account and P12 key:
	// https://developers.google.com/identity/protocols/OAuth2ServiceAccount
	/** * */
	private final String CLIENT_ID = "projectsync@projectsync-281422.iam.gserviceaccount.com";
	
	// Add requested scopes.
	/** * */
	private final List<String> SCOPES = Arrays.asList("https://spreadsheets.google.com/feeds");
	
	// The name of the p12 file one created when obtaining the service account
	/** * */
	private final String P12FILE = "/projectsync-281422-0ea6cec11520.p12";

	/** * */
	public static final String spreadsheetId = "13KdjcScFGR7Z_pghP0BNXxYJ9tM9JAt9tctGq4wQSdA";

	/**
	 * @return
	 * @throws GeneralSecurityException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	@Bean
	public GoogleCredential googleCredentials() throws GeneralSecurityException, IOException, URISyntaxException {
		//TODO: Comment
		JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
		
		//TODO: Comment
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		
		//TODO: Comment
		URL fileUrl = this.getClass().getResource(P12FILE);
		
		//TODO: Comment
		GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
				//TODO: Comment
				.setJsonFactory(JSON_FACTORY).setServiceAccountId(CLIENT_ID)
				//TODO: Comment
				.setServiceAccountPrivateKeyFromP12File(new File(fileUrl.toURI())).setServiceAccountScopes(SCOPES)
				//TODO: Comment
				.build();
		
		//TODO: Comment
		credential.refreshToken();
		return credential;
	}

	/**
	 * @return
	 * @throws GeneralSecurityException
	 * @throws IOException
	 */
	@Bean
	public NetHttpTransport httpTransport() throws GeneralSecurityException, IOException {
		return GoogleNetHttpTransport.newTrustedTransport();
	}

	/**
	 * @return
	 */
	@Bean
	public JsonFactory jsonFactory() {
		return JacksonFactory.getDefaultInstance();
	}

	// Build a new authorized API client service.
	/**
	 * @param httpTransport
	 * @param jsonFactory
	 * @param googleCredential
	 * @return
	 */
	@Bean
	public Sheets sheetsService(NetHttpTransport httpTransport, JsonFactory jsonFactory,
			GoogleCredential googleCredential) {
		return new Sheets.Builder(httpTransport, jsonFactory, googleCredential).setApplicationName("My Application")
				.build();
	}

}
