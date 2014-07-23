/*
 * Copyright (c) 2014 James Richardson.
 *
 * MessageType.java is part of BukkitUtilitiesLocalisation.
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

import org.bukkit.ChatColor;

public enum MessageType {
	ERROR(ChatColor.RED, ChatColor.YELLOW),
	WARNING(ChatColor.YELLOW, ChatColor.GREEN),
	NOTICE(ChatColor.GREEN, ChatColor.AQUA),
	DEBUG(ChatColor.DARK_PURPLE, ChatColor.GREEN),
	HEADER(ChatColor.LIGHT_PURPLE, ChatColor.AQUA);

	private final ChatColor highlight;
	private final ChatColor normal;

	MessageType(ChatColor normal, ChatColor highlight) {
		this.normal = normal;
		this.highlight = highlight;
	}

	public ChatColor colour() {
		return normal;
	}

	public ChatColor highlight() {
		return highlight;
	}
}
