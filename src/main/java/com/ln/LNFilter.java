//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ln;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LNFilter extends HttpServlet implements Filter {
    public LNFilter() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        if(Const.LMSG != 1) {
            res.sendRedirect(req.getContextPath() + "/system/InLicense.jsp?message=" + Const.LMSG);
        } else {
            chain.doFilter(request, response);
        }

    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}
