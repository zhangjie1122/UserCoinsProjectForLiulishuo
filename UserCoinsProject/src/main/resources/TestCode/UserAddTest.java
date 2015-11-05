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
 
public class UserAddTest extends JerseyTest {
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
	@Override
    protected TestContainerFactory getTestContainerFactory() {
        return new GrizzlyWebTestContainerFactory();
    }
	
	@Test
	public void testUserAddSuccess() {
		WebResource webResource = client().resource("http://localhost:8080/");
		Form form = new Form();
		form.add("userid", "113");
		form.add("coins","113");
		
		String res = webResource.path("/UserCoinsProject/webapi/UserCoinsResource/user/add")
				.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				.post(String.class,form);
		assertEquals("user add success", res);
	}
	@Test
	public void testUserAddFailure1() {
		WebResource webResource = client().resource("http://localhost:8080/");
		Form form = new Form();
		form.add("userid", "1");
		form.add("coins","111");
		
		String res = webResource.path("/UserCoinsProject/webapi/UserCoinsResource/user/add")
				.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				.post(String.class,form);
		assertEquals("user is existed!", res);
	}
	@Test
	public void testUserAddFailure2() {
		WebResource webResource = client().resource("http://localhost:8080/");
		Form form = new Form();
		form.add("userid", "112");
		form.add("coins","-7");
		
		String res = webResource.path("/UserCoinsProject/webapi/UserCoinsResource/user/add")
				.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				.post(String.class,form);
		assertEquals("input of coins is invalid", res);
	}
}
