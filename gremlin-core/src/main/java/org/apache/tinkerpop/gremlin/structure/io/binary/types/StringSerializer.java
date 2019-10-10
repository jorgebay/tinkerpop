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

import java.nio.charset.StandardCharsets;

public class StringSerializer extends SimpleTypeSerializer<String> {
    public StringSerializer() {
        super(DataType.STRING);
    }

    @Override
    protected String readValue(final Buffer buffer, final GraphBinaryReader context) {
        // Use Netty 4.0 API (avoid ByteBuf#readCharSequence() method) to maximize compatibility
        final byte[] bytes = new byte[buffer.readInt()];
        buffer.readBytes(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Override
    protected void writeValue(final String value, final Buffer buffer, final GraphBinaryWriter context) {
        final byte[] stringBytes = value.getBytes(StandardCharsets.UTF_8);
        buffer.writeInt(stringBytes.length).writeBytes(stringBytes);
    }
}
