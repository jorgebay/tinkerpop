/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tinkerpop.gremlin.structure.io.binary.types;

import org.apache.tinkerpop.gremlin.structure.io.binary.DataType;
import org.apache.tinkerpop.gremlin.structure.io.binary.GraphBinaryReader;
import org.apache.tinkerpop.gremlin.structure.io.binary.GraphBinaryWriter;
import org.apache.tinkerpop.gremlin.structure.io.Buffer;

import java.sql.Timestamp;
import java.util.Date;
import java.util.function.Function;

/**
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
public class DateSerializer<T extends Date> extends SimpleTypeSerializer<T> {

    public static final DateSerializer<Date> DateSerializer = new DateSerializer<>(DataType.DATE, Date::new);
    public static final DateSerializer<Timestamp> TimestampSerializer = new DateSerializer<>(DataType.TIMESTAMP, Timestamp::new);

    private final Function<Long, T> reader;

    private DateSerializer(final DataType type, final Function<Long, T> reader) {
        super(type);
        this.reader = reader;
    }

    @Override
    protected T readValue(final Buffer buffer, final GraphBinaryReader context) {
        return reader.apply(buffer.readLong());
    }

    @Override
    protected void writeValue(final T value, final Buffer buffer, final GraphBinaryWriter context) {
        buffer.writeLong(value.getTime());
    }
}
