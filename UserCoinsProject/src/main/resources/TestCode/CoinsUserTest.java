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
 
public class CoinsUserTest extends JerseyTest {
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
	@Override
    protected TestContainerFactory getTestContainerFactory() {
        return new GrizzlyWebTestContainerFactory();
    }
	
	@Test
	public void testCoinsUserSuccess() {
		WebResource webResource = client().resource("http://localhost:8080/");
		Form form = new Form();
		form.add("userid", "3");   
		
		String res = webResource.path("/UserCoinsProject/webapi/UserCoinsResource/coins/user")
				.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				.post(String.class,form);
		assertEquals("user 3 coins is 20", res);
	}
	
	@Test
	public void testCoinsUserFailure() {
		WebResource webResource = client().resource("http://localhost:8080/");
		Form form = new Form();
		form.add("userid", "10");   
		
		String res = webResource.path("/UserCoinsProject/webapi/UserCoinsResource/coins/user")
				.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				.post(String.class,form);
		assertEquals("user is not existed", res);
	}
}
