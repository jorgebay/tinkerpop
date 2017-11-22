﻿#region License

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

#endregion

using System;
using Gremlin.Net.Driver;
using Gremlin.Net.Process.Remote;

namespace Gremlin.Net.IntegrationTest.Process.Traversal.DriverRemoteConnection
{
    internal class RemoteConnectionFactory
    {
        private static readonly string TestHost = ConfigProvider.Configuration["TestServerIpAddress"];
        private static readonly int TestPort = Convert.ToInt32(ConfigProvider.Configuration["TestServerPort"]);

        public IRemoteConnection CreateRemoteConnection()
        {
            // gmodern is the standard test traversalsource that the main body of test uses
            return CreateRemoteConnection("gmodern");
        }

        public IRemoteConnection CreateRemoteConnection(string traversalSource)
        {
            return new Net.Driver.Remote.DriverRemoteConnection(
                new GremlinClient(new GremlinServer(TestHost, TestPort)), traversalSource);
        }
    }
}