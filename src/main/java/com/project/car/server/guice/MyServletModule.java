package com.project.car.server.guice;

import com.gargoylesoftware.htmlunit.WebClient;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;
import com.gwtplatform.dispatch.rpc.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.rpc.shared.ActionImpl;
import com.project.car.client.place.NameTokens;
import com.project.car.server.dispatch.common.UploadServlet;

public class MyServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/" + ActionImpl.DEFAULT_SERVICE_NAME  + "*").with(DispatchServiceImpl.class);

        // Objectify filter
        bind(ObjectifyFilter.class).in(Singleton.class);
        filter("/*").through(ObjectifyFilter.class);

        // my servlets
        bind(UploadServlet.class).in(Singleton.class);
        serve(NameTokens.UPLOAD_PATH).with(UploadServlet.class);
        serve(NameTokens.SERVE_PATH).with(UploadServlet.class);

/*        bind(ServeServlet.class).in(Singleton.class);
        serve(NameTokens.SERVE_PATH).with(ServeServlet.class);*/
    }

    @Singleton
    @Provides
    WebClient getWebClient() {
        return new WebClient();
    }
}

