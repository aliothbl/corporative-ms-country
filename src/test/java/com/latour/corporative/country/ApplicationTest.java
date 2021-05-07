package com.latour.corporative.country;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Alioth Latour
 * @datetime 5/7/2021 4:57 PM
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {
	
	@Test
	public void testMain() {
		Application.main(new String[] {});
	}
	
}