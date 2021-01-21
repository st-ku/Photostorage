package info.freeit.photostorage.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import info.freeit.photostorage.model.Role;
import info.freeit.photostorage.model.User;
import info.freeit.photostorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

@Configuration
public class ApplicationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;
    private static final String [] open = {
            "/webjars/**"};

    private static final String [] closed = {"/admin/**"};

    @Bean
    public Cloudinary cloudinaryConfig(@Value("${cloudinary.cloud.name}") String cloudName,
                                       @Value("${cloudinary.api.key}") String apiKey,
                                       @Value("${cloudinary.api.secret}") String apiSecret) {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers().frameOptions().sameOrigin();

        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(open).permitAll()
                .antMatchers(closed).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
    }


    @EventListener
    public void saveUserToLocal(AuthenticationSuccessEvent event) {
        DefaultOidcUser principal = (DefaultOidcUser) event.getAuthentication().getPrincipal();
        User user = new User();
        user.setId(principal.getEmail());
        user.setFamilyName(principal.getFamilyName());
        user.setGivenName(principal.getGivenName());
        user.setRole(Role.USER);
        user.setPictureUrl(principal.getPicture());
        userService.saveUser(user);
    }
}
