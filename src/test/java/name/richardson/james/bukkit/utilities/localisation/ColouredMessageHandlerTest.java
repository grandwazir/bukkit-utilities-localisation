/*
 * Copyright (c) 2014 James Richardson.
 *
 * ColouredMessageHandlerTest.java is part of bukkit-utilities-localisation.
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

public class ColouredMessageHandlerTest {

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");
	private TestMessages handler;

	@Before
	public void setup() {
		handler =  (TestMessages) Proxy.newProxyInstance(TestMessages.class.getClassLoader(), new Class[]{TestMessages.class}, new ColouredMessageHandler(RESOURCE_BUNDLE));
	}

	@Test
	public void colourBasicMessageCorrectly() {
		Assert.assertEquals("Message has not been formatted correctly!", "§cwarning! warning!", handler.error());
	}

	@Test
	public void colourMessageWithArgumentsCorrectly() {
		Assert.assertEquals("Message has not been formatted correctly!", "§eWarning §afrank§e, there is a problem!", handler.warning("frank"));
	}

}
