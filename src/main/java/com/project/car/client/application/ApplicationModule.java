package com.project.car.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.project.car.client.application.home.HomeModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new HomeModule());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}
