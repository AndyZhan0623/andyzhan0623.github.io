package andyzhan.gamebox.infrastructure.filter;

import andyzhan.gamebox.infrastructure.database.id.UserId;
import andyzhan.gamebox.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            String token = header.substring(7);
            try{
                UserId userId = JwtService.parseToken(token);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userId, null, new ArrayList<>()
                        );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }catch(Exception e){
                System.out.println(e.getMessage());
                SecurityContextHolder.clearContext();
            }
        }

        chain.doFilter(request, response);

    }
}
