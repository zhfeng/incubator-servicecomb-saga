/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.saga.demo.pack.booking;

import org.apache.servicecomb.saga.omega.context.annotations.SagaStart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="mailto:zh.feng@google.com>Zheng Feng</a>
 */
@RestController
public class BookingController {
  @Autowired
  private RestTemplate template;

  @SagaStart
  @PostMapping("/booking/{name}/{rooms}/{cars}")
  public String order(@PathVariable String name,  @PathVariable Integer rooms, @PathVariable Integer cars) {
    template.postForEntity(
        "http://pack-car.servicecomb.io:8080/order/{name}/{cars}",
        null, String.class, name, cars);

    template.postForEntity(
        "http://pack-hotel.servicecomb.io:8080/order/{name}/{rooms}",
        null, String.class, name, rooms);

    return name + " booking " + rooms + " rooms and " + cars + " cars OK";
  }
}
