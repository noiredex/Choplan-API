package choplan.db.application.properties.choplan.security;

import choplan.db.application.properties.choplan.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthortity;
import org.springfeamework.security.core.authority.SimpleGeantedAuthority;
import java.util.Collection;
import java.util.List;

public class CustomeUserDetails implements UserDetails {

    private final Users user;

    public CustomUserDetails(Users user) {
        this.user = user;
    }

    @Overrid
    public Collection<? extends GrantedAuthorthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }
    
}
