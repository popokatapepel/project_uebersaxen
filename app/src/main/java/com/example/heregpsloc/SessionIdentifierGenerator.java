package com.example.heregpsloc;

import java.security.SecureRandom;
import java.math.BigInteger;

// http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string

public final class SessionIdentifierGenerator {
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}