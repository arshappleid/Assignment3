package paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

public class Credentials {
	static String clientId = "AWQrZtu7a4LxgWrpp6bQ7JTS60ChNjJkDlIWSb4KO9YQtMloDoZEb3Mmkrw-gThXDyZGEvAz1f6Xxkj0";
	static String secret = "EEk8JC_OBpukiEyxQCi0p-AuD15pASyTmy2cJWPGwsoAe9K5llfBumfjswBa-_W4Ml4XdH5sbzGHHCDw";

	// Creating a sandbox environment
	private static PayPalEnvironment environment = new PayPalEnvironment.Sandbox(clientId, secret);

	// Creating a client for the environment
	static PayPalHttpClient client = new PayPalHttpClient(environment);
}