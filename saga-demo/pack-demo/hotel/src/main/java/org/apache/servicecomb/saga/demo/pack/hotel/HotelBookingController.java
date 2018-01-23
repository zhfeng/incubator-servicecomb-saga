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

package org.apache.servicecomb.saga.demo.pack.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:zh.feng@google.com>Zheng Feng</a>
 */
@RestController
public class HotelBookingController {
  @Autowired
  private HotelBookingService service;

  private AtomicInteger id = new AtomicInteger(0);

  @GetMapping("/bookings")
  List<HotelBooking> getAll() {
    List<HotelBooking> result = new ArrayList<>();
    result.addAll(service.getAllBookings());
    return result;
  }

  @PostMapping("/order/{name}/{rooms}")
  HotelBooking order(@PathVariable String name, @PathVariable Integer rooms) {
    HotelBooking booking = new HotelBooking();
    booking.setId(id.incrementAndGet());
    booking.setName(name);
    booking.setRooms(rooms);
    service.order(booking);
    return booking;
  }
}
