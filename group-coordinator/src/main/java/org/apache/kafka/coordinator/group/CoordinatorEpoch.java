/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.kafka.coordinator.group;

import java.util.Objects;
import java.util.OptionalInt;

public class CoordinatorEpoch {
    public static CoordinatorEpoch empty() {
        return new CoordinatorEpoch();
    }

    public static CoordinatorEpoch of(int coordinatorEpoch) {
        return new CoordinatorEpoch(coordinatorEpoch);
    }

    /**
     * The leader epoch of the partition as an optional value.
     * An empty value means that the topic was deleted.
     */
    private OptionalInt coordinatorEpoch;

    private CoordinatorEpoch() {
        this.coordinatorEpoch = OptionalInt.empty();
    }

    private CoordinatorEpoch(int coordinatorEpoch) {
        this.coordinatorEpoch = OptionalInt.of(coordinatorEpoch);
    }

    public boolean isDeleted() {
        return !coordinatorEpoch.isPresent();
    }

    public int epoch() {
        return coordinatorEpoch.getAsInt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatorEpoch that = (CoordinatorEpoch) o;
        return Objects.equals(coordinatorEpoch, that.coordinatorEpoch);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(coordinatorEpoch);
    }

    @Override
    public String toString() {
        if (coordinatorEpoch.isPresent()) {
            return String.valueOf(coordinatorEpoch.getAsInt());
        } else {
            return "empty";
        }
    }
}
