package TestCode;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;
import com.sun.jersey.test.framework.spi.container.grizzly2.web.GrizzlyWebTestContainerFactory;
 
public class TransactionTransferTest extends JerseyTest {
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
	@Override
    protected TestContainerFactory getTestContainerFactory() {
        return new GrizzlyWebTestContainerFactory();
    }
	
	@Test
	public void testTransactionTransferSuccess() {
		WebResource webResource = client().resource("http://localhost:8080/");
		Form form = new Form();
		form.add("from_user_id", "1");
		form.add("to_user_id","2");
		form.add("coins","1");
		
		String res = webResource.path("/UserCoinsProject/webapi/UserCoinsResource/transaction/transfer")
				.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				.post(String.class,form);
		assertEquals("transfer success", res);
	}
	@Test
	public void testTransactionTransferFailure1() {
		WebResource webResource = client().resource("http://localhost:8080/");
		Form form = new Form();
		form.add("from_user_id", "210");
		form.add("to_user_id","2");
		form.add("coins","1");
		
		String res = webResource.path("/UserCoinsProject/webapi/UserCoinsResource/transaction/transfer")
				.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				.post(String.class,form);
		assertEquals("from user is not exist", res);
	}
	@Test
	public void testTransactionTransferFailure2() {
		WebResource webResource = client().resource("http://localhost:8080/");
		Form form = new Form();
		form.add("from_user_id", "1");
		form.add("to_user_id","210");
		form.add("coins","1");
		
		String res = webResource.path("/UserCoinsProject/webapi/UserCoinsResource/transaction/transfer")
				.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				.post(String.class,form);
		assertEquals("to user is not exist", res);
	}
	@Test
	public void testTransactionTransferFailure3() {
		WebResource webResource = client().resource("http://localhost:8080/");
		Form form = new Form();
		form.add("from_user_id", "1");
		form.add("to_user_id","2");
		form.add("coins","1000");
		
		String res = webResource.path("/UserCoinsProject/webapi/UserCoinsResource/transaction/transfer")
				.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				.post(String.class,form);
		assertEquals("from user coins is not enough", res);
	}
}
