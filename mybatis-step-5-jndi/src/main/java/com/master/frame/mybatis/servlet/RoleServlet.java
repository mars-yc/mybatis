package com.master.frame.mybatis.servlet;

import com.master.frame.mybatis.domain.Role;
import com.master.frame.mybatis.service.RoleService;
import com.master.frame.mybatis.service.impl.RoleServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RoleServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(RoleServlet.class);
    private RoleService roleService = RoleServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doGet");
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("DEVELOPER");
        Role role3 = new Role("BUSINESS");
        roleService.add(role1);
        roleService.add(role2);
        roleService.add(role3);
        List<Role> list = roleService.load();
        StringBuffer buffer = new StringBuffer();
        buffer.append("<h2>");
        for(Role role : list) {
            buffer.append(role + "<br>");
        }
        buffer.append("</h2>");
        resp.getWriter().print(buffer);
    }

}