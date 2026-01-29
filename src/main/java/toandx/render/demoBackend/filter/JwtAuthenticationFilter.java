package toandx.render.demoBackend.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import toandx.render.demoBackend.entity.User;
import toandx.render.demoBackend.service.UserService;
import toandx.render.demoBackend.util.JwtTokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
// Step 5. Create JwtAuthenticationFilter read all API request, get JWT, setAuthentication
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);
            //System.out.println("Token validate "+Boolean.toString(tokenProvider.validateToken(jwt)));
            if (StringUtils.hasText(jwt) && (tokenProvider.validateToken(jwt)))
            {
                String username = tokenProvider.getUsernameFromToken(jwt);
                User user = userService.loadUserByUsername(username);
                if (user!=null) {
                    //System.out.println("Token OK, Authen OK!");

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities());
                    auth.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));
                    // After setting the Authentication in the context, we specify
                    // that the current user is authenticated. So it passes the
                    // Spring Security Configurations successfully.
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }


        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String requestTokenHeader = request.getHeader("Authorization");
        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        /*if (StringUtils.hasText(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")) {
            return requestTokenHeader.substring(7);
        }
        return null;*/
        return requestTokenHeader;
    }
}


