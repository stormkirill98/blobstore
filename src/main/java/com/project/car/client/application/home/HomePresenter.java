package com.project.car.client.application.home;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.project.car.client.application.ApplicationPresenter;
import com.project.car.client.dispatch.AsyncCallbackImpl;
import com.project.car.client.place.NameTokens;
import com.project.car.shared.action.CreateUploadUrlAction;
import com.project.car.shared.action.CreateUploadUrlResult;


public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> implements HomeUiHandlers {
    interface MyView extends View, HasUiHandlers<HomePresenter> {
        void setActionUploadForm(String uploadUrl);
    }

    @ProxyStandard
    @NameToken(NameTokens.HOME)
    interface MyProxy extends ProxyPlace<HomePresenter> {
    }

    private final DispatchAsync dispatcher;
    private final PlaceManager placeManager;

    @Inject
    HomePresenter(
            EventBus eventBus,
            HomePresenter.MyView view,
            HomePresenter.MyProxy proxy,
            DispatchAsync dispatcher,
            PlaceManager placeManager) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);

        this.dispatcher = dispatcher;
        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    public void onReveal(){
        GWT.log("onReveal");
    }

    @Override
    protected void onBind() {
        super.onBind();

        GWT.log("onBind");
    }

    @Override
    public void prepareFromRequest(final PlaceRequest placeRequest){
        super.prepareFromRequest(placeRequest);

        GWT.log("prepareFromRequest");

    }

    @Override
    public void createUploadUrl(){

        dispatcher.execute(new CreateUploadUrlAction(), new AsyncCallbackImpl<CreateUploadUrlResult>() {
            @Override
            protected void onCustomSuccess(CreateUploadUrlResult result) {
                GWT.log("createUploadUrl onCustomSuccess result.getUploadUrl = " + result.getUploadUrl());

                getView().setActionUploadForm(result.getUploadUrl());
            }
        });
    }
}
