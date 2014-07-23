/*
 * Copyright (c) 2014 James Richardson.
 *
 * AbstractMessageHandler.java is part of BukkitUtilitiesLocalisation.
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

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import com.google.common.base.CaseFormat;

public abstract class AbstractMessageHandler implements java.lang.reflect.InvocationHandler {

	private final ResourceBundle bundle;

	public AbstractMessageHandler(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	protected static final String methodNameToBundleKey(String name) {
		String key = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
		key = key.replaceAll("_", ".");
		return key;
	}

	protected final String formatMessage(Method method, String message, Object[] arguments) {
		Annotation[][] paramAnnotations = method.getParameterAnnotations();
		for (int i = 0; i < paramAnnotations.length; i++) {
			for (Annotation a : paramAnnotations[i]) {
				if (a instanceof PluralCount) {
					arguments[i] = getPluralParameter(method, arguments[i]);
				}
			}
		}
		return MessageFormat.format(message, arguments);
	}

	protected final ResourceBundle getBundle() {
		return bundle;
	}

	protected final String getMessage(String key) {
		String message;
		boolean keyPresent = getBundle().containsKey(key);
		if (keyPresent) {
			message = getBundle().getString(key);
		} else {
			message = "!" + key + "!";
		}
		return message;
	}

	protected final String getPluralParameter(Member method, Object parameter) {
		String key = methodNameToBundleKey(method.getName());
		String parameterKey;
		String message;
		Integer count = (Integer) parameter;
		switch (count) {
			case 0:
				parameterKey = "[none]";
				break;
			case 1:
				parameterKey = "[one]";
				break;
			case 2:
				parameterKey = "[many]";
				break;
			default:
				parameterKey = "[many]";
				break;
		}
		key = key + parameterKey;
		message = bundle.getString(key);
		if (count > 1) {
			message = MessageFormat.format(message, count);
		}
		return message;
	}
}
