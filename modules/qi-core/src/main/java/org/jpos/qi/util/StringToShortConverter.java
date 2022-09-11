/*
 * jPOS Project [http://jpos.org]
 * Copyright (C) 2000-2021 jPOS Software SRL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.jpos.qi.util;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

public class StringToShortConverter implements Converter<String,Short> {

    public StringToShortConverter() {
        super();
    }

    @Override
    public Result<Short> convertToModel(String value, ValueContext context) {
        if (value != null && !value.isEmpty())
            try {
                return Result.ok(Short.valueOf(value));
            } catch (NumberFormatException e) {
                return Result.error("invalid value");
            }
        return Result.ok(null);
    }

    @Override
    public String convertToPresentation(Short value, ValueContext context) {
        if (value != null && value > 0)
            return value.toString();
        return "";
    }
}
