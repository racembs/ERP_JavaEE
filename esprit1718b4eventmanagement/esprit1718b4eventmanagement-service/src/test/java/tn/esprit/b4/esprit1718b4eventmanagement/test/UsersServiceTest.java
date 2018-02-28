package tn.esprit.b4.esprit1718b4eventmanagement.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import tn.esprit.b4.esprit1718b4eventmanagement.services.UserService;

@RunWith(Arquillian.class)
public class UsersServiceTest {

	@Deployment
	public static Archive<?> createDeployment() {

		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war").addClasses(UserService.class)// List Of All classes included in
																						// the test scenario
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");

		System.out.println(war.toString(true));

		return war;
	}

	@Before
	public void background() {

	}

	@Test
	public void itShouldFindUser() {

	}

	@After
	public void clean() {

	}

}
