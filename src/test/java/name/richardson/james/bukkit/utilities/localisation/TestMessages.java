/*
 * Copyright (c) 2014 James Richardson.
 *
 * TestMessages.java is part of bukkit-utilities-localisation.
 *
 * bukkit-utilities-localisation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * bukkit-utilities-localisation is distributed in the hope that it will be useful, but WITHOUTANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along wit bukkit-utilities-localisation. If not, see <http://www.gnu.org/licenses/>.
 */

package name.richardson.james.bukkit.utilities.localisation;

public interface TestMessages {

	@ColouredMessage(type = MessageType.ERROR, highlight = false)
	String error();
	String format(String one, String two, String three);
	String one();
	String testing();
	String testingCamelCaseNames();
	String three();
	String two();
	@ColouredMessage(type = MessageType.WARNING, highlight = true)
	String warning(String one);
	String usersFound(@PluralCount int userCount);

}
