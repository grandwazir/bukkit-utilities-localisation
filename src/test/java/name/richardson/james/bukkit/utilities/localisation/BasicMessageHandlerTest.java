/*
 * Copyright (c) 2014 James Richardson.
 *
 * BasicMessageHandlerTest.java is part of bukkit-utilities-localisation.
 *
 * bukkit-utilities-localisation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * bukkit-utilities-localisation is distributed in the hope that it will be useful, but WITHOUTANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along wit bukkit-utilities-localisation. If not, see <http://www.gnu.org/licenses/>.
 */

package name.richardson.james.bukkit.utilities.localisation;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BasicMessageHandlerTest {

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");
	private TestMessages handler;

	@Before
	public void setup() {
		handler =  (TestMessages) Proxy.newProxyInstance(TestMessages.class.getClassLoader(), new Class[]{TestMessages.class}, new BasicMessageHandler(RESOURCE_BUNDLE));
	}

	@Test
	public void resolveKeySucessfully() {
		Assert.assertEquals("Key has not been resolved correctly!", handler.one(), "one");
	}

	@Test
	public void resolveKeyUnsucessfullyWithoutException() {
		Assert.assertNotEquals("Key has been resolved correctly!", handler.three(), "three");
	}

	@Test
	public void resolveCamelCaseMethodToKey() {
		Assert.assertEquals("Key has not been resolved correctly!", handler.testingCamelCaseNames(), "test");
	}

	@Test
	public void formatProvidedArgumentsCorrectly() {
		Assert.assertEquals("Key has not been formatted correctly!", "test: one two three", handler.format("one", "two", "three"));
	}

	@Test
	public void formatPluralCountOfNoneSuccessfully() {
		Assert.assertEquals("Key has not been formatted correctly!", "no users were found", handler.usersFound(0));
	}

	@Test
	public void formatPluralCountOfOneSuccessfully() {
		Assert.assertEquals("Key has not been formatted correctly!", "one user was found", handler.usersFound(1));
	}

	@Test
	public void formatPluralCountOfTwoSuccessfully() {
		Assert.assertEquals("Key has not been formatted correctly!", "2 users were found", handler.usersFound(2));
	}

	@Test
	public void formatPluralCountOfManySuccessfully() {
		Assert.assertEquals("Key has not been formatted correctly!", "3 users were found", handler.usersFound(3));
	}


}
