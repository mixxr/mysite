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
package com.mysite.core.models;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import java.util.Optional;

@Model(adaptables = Resource.class)
public class HelloWorldModel {

    @ValueMapValue(name=PROPERTY_RESOURCE_TYPE, injectionStrategy=InjectionStrategy.OPTIONAL)
    @Default(values="No resourceType")
    protected String resourceType;

    @SlingObject
    private Resource currentResource;
    @SlingObject
    private ResourceResolver resourceResolver;

    private String message;

    @PostConstruct
    protected void init() {
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        String currentPagePath = Optional.ofNullable(pageManager)
                .map(pm -> pm.getContainingPage(currentResource))
                .map(Page::getPath).orElse("");
        
                Page currentPage = pageManager.getContainingPage(currentResource);
                String locale = currentPage.getLanguage().getLanguage();
                String parentPath = currentPage.getAbsoluteParent(2).getPath();

        message = "Hello World!\n"
            + "Resource type is: " + resourceType + "\n"
            + "ParentPath is: " + parentPath + "\n"
            + "Locale is: " + locale + "\n"
            + "Current page is:  " + currentPagePath + "\n";

        System.getenv().forEach((k, v) -> {
            //System.out.println(k + ":" + v);
            message = message + "\n ENV("+k+")="+v;
        });

        System.getProperties().forEach((k, v) -> {
            //System.out.println(k + ":" + v);
            message = message + "\n PROP("+k+")="+v;
        });

        long mb = 1024*1024;
        // Get current size of heap in bytes
        long heapSize = Runtime.getRuntime().totalMemory() / mb; 

        // Get maximum size of heap in bytes. The heap cannot grow beyond this size.// Any attempt will result in an OutOfMemoryException.
        long heapMaxSize = Runtime.getRuntime().maxMemory() / mb;

        // Get amount of free memory within the heap in bytes. This size will increase // after garbage collection and decrease as new objects are created.
        long heapFreeSize = Runtime.getRuntime().freeMemory() / mb; 

        message = message + "\n MEM(heapSize MB)="+heapSize;
        message = message + "\n MEM(heapMaxSize MB)="+heapMaxSize;
        message = message + "\n MEM(heapFreeSize MB)="+heapFreeSize;
    }

    public String getMessage() {
        return message;
    }

}
