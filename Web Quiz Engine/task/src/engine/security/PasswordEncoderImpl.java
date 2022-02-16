package engine.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderImpl implements PasswordEncoder {

    private final static PasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
    private final String encodedPassword = null;
    private final CharSequence rawPassword = null;

    @Override
    public String encode(CharSequence rawPassword) {
        var encoded = bcryptPasswordEncoder.encode(rawPassword);
        return encoded;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return bcryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
