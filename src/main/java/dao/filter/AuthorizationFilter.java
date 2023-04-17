package dao.filter;

import dto.ReadAdminDto;
import dto.ReadBuyerDto;
import hibDto.ReadBuyerDtoHib;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    public static final List<String> PUBLIC_PATHS = List.of("/registration", "/authentication");



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {



        var uri = ((HttpServletRequest)servletRequest).getRequestURI();

        if(isPublicPath(uri) || isUserLoggedIn(servletRequest)){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else{

            //var prevPage = ((HttpServletRequest)servletRequest).getHeader("referer");
            ((HttpServletResponse)servletResponse).sendRedirect("/authentication");
        }

    }

    private boolean isPublicPath(String uri) {

        return PUBLIC_PATHS.stream().anyMatch(uri::startsWith);
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {

        var buyerDto = (ReadBuyerDtoHib)((HttpServletRequest)servletRequest).getSession().getAttribute("buyer");
        var adminDto = (ReadAdminDto)((HttpServletRequest)servletRequest).getSession().getAttribute("admin");


        return buyerDto != null || adminDto != null;
    }

}
