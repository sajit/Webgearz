package misc;

import junit.framework.Assert;

import org.junit.Test;

import com.webgearz.tb.domain.models.Sample;

public class TestAbstractModelStuff {
	
	@Test
	public void testCollectionNameSet(){
		
		Sample newSample = new Sample();
		Assert.assertEquals("Sample",newSample.getCOLLECTION_NAME());
		Sample secondSample = new Sample();
		Assert.assertEquals("Sample", secondSample.getCOLLECTION_NAME());
		
	}

}
