/*
 * Copyright (c) 2014 James Richardson.
 *
 * ColouredMessageHandler.java is part of BukkitUtilitiesLocalisation.
 *
 * BukkitUtilitiesLocalisation is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * BukkitUtilitiesLocalisation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public License along wit BukkitUtilitiesLocalisation. If not,
 * see <http://www.gnu.org/licenses/>.
 */

package name.richardson.james.bukkit.utilities.localisation;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

public final class ColouredMessageHandler extends AbstractMessageHandler {

	public ColouredMessageHandler(ResourceBundle bundle) {
		super(bundle);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String key = methodNameToBundleKey(method.getName());
		String message = getMessage(key);
		ColouredMessage annotation = method.getAnnotation(ColouredMessage
			.class);
		if (annotation != null) {
			message = colourMessage(annotation.type(), message);
			if (annotation.highlight()) {
				message = highlightMessage(annotation.type(), message);
			}
		}
		message = formatMessage(method, message, args);
		return message;
	}

	private static String colourMessage(MessageType type, String message) {
		return type.colour() + message;
	}

	private static String highlightMessage(MessageType type, String message) {
		String formatted = message.replaceAll("\\{", type.highlight() + "\\{");
		formatted = formatted.replaceAll("\\}", "\\}" + type.colour());
		return formatted;
	}
}
