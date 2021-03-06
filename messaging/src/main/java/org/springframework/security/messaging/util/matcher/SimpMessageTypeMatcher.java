/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.springframework.security.messaging.util.matcher;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.util.Assert;

/**
 * A {@link MessageMatcher} that matches if the provided {@link Message} has a
 * type that is the same as the {@link SimpMessageType} that was specified in
 * the constructor.
 *
 * @since 4.0
 * @author Rob Winch
 *
 */
public class SimpMessageTypeMatcher implements MessageMatcher<Object> {
    private final SimpMessageType typeToMatch;

    /**
     * Creates a new instance
     *
     * @param typeToMatch the {@link SimpMessageType} that will result in a match. Cannot be null.
     */
    public SimpMessageTypeMatcher(SimpMessageType typeToMatch) {
        Assert.notNull(typeToMatch, "typeToMatch cannot be null");
        this.typeToMatch = typeToMatch;
    }

    public boolean matches(Message<? extends Object> message) {
        MessageHeaders headers = message.getHeaders();
        SimpMessageType messageType = SimpMessageHeaderAccessor
                .getMessageType(headers);

        return typeToMatch == messageType;
    }
}