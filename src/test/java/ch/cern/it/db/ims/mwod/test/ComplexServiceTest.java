package ch.cern.it.db.ims.mwod.test;

import org.junit.Test;

import ch.cern.it.db.ims.mwod.services.ComplexServices;
import ch.cern.it.db.ims.mwod.services.ComplexServicesImpl;

public class ComplexServiceTest {
	
	private ComplexServices complexServices = new ComplexServicesImpl();
	
	@Test
	public void complexMethodTest(){
		complexServices.complexMethod();
	}
}
