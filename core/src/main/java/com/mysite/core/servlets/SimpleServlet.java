/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.mysite.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

import com.day.cq.wcm.api.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

/**
 * Servlet that writes some sample content into the response. It is mounted for
 * all resources of a specific Sling resource type. The
 * {@link SlingSafeMethodsServlet} shall be used for HTTP methods that are
 * idempotent. For write operations use the {@link SlingAllMethodsServlet}.
 */

@Component(
    service = Servlet.class,
    property = {
        "sling.servlet.selectors=" + SimpleServlet.DEFAULT_SELECTOR,
        "sling.servlet.resourceTypes=cq/Page",
        "sling.servlet.extensions=txt",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET
    }
)
@ServiceDescription("Simple Demo Servlet")
public class SimpleServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;
    protected static final String DEFAULT_SELECTOR = "simplesearch";
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleServlet.class);

    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        final Resource resource = req.getResource();

        Page currentPage = Optional.ofNullable(req.getResourceResolver().adaptTo(PageManager.class))
            .map(pm -> pm.getContainingPage(req.getResource()))
            .orElse(null);

        resp.setContentType("text/plain");

        if (currentPage != null) {
            LOGGER.debug("servlet.currentPage="+currentPage.getPath());
            LOGGER.debug("servlet.mySearchPath="+currentPage.getProperties().get("mySearchPath"));

            resp.getWriter().write(",currentPage = " + currentPage.getPath());
            resp.getWriter().write(",mySearchPath = " + currentPage.getProperties().get("mySearchPath"));
        }
        
        //resp.getWriter().write(",Title = " + resource.getValueMap().get(JcrConstants.JCR_TITLE));
    }
}
