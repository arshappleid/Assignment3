package paypal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.paypal.http.HttpResponse;
import com.paypal.http.exceptions.HttpException;
import com.paypal.orders.*;

public class CreateOrder {
	public static void main(String[] args) {
		create_order("100");
	}

	public static void create_order(String amount) {
		parseAmount(amount);
		Order order = null;
		// Construct a request object and set desired parameters
		// Here, OrdersCreateRequest() creates a POST request to /v2/checkout/orders

		// we expect the default currency to be CAD, so we will not change that.

		OrderRequest orderRequest = new OrderRequest();
		orderRequest.checkoutPaymentIntent("CAPTURE");
		List<PurchaseUnitRequest> purchaseUnits = new ArrayList<>();
		PurchaseUnitRequest purchase_request = new PurchaseUnitRequest()
				.amountWithBreakdown(new AmountWithBreakdown().currencyCode("CAD").value(amount));
		purchaseUnits.add(purchase_request);
		orderRequest.purchaseUnits(purchaseUnits);
		OrdersCreateRequest request = new OrdersCreateRequest().requestBody(orderRequest);

		try {
			// Call API with your client and get a response for your call
			HttpResponse<Order> response = Credentials.client.execute(request);

			// If call returns body in response, you can get the de-serialized version by
			// calling result() on the response
			order = response.result();
			System.out.println("Order ID: " + order.id());
			order.links().forEach(link -> System.out.println(link.rel() + " => " + link.method() + ":" + link.href()));
		} catch (IOException ioe) {
			if (ioe instanceof HttpException) {
				// Something went wrong server-side
				HttpException he = (HttpException) ioe;
				System.out.println(he.getMessage());
				he.headers().forEach(x -> System.out.println(x + " :" + he.headers().header(x)));
			} else {
				// Something went wrong client-side
				System.out.println("Something went wrong on client side, check Client Credentials");
			}
		}

		System.out.println("Trasaction Completed");
	}

	public static void parseAmount(String amount) {
		boolean contains_zeros = false;

		for (int i = 0; i < amount.length(); i++) {
			if (amount.charAt(i) == '.') {
				contains_zeros = true;
			}
		}
		if (!contains_zeros) {
			amount += ".00";
		}
	}

}