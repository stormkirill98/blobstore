package com.project.car.server.dispatch;

import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;
import com.project.car.server.dispatch.common.CreateUploadUrlHandler;
import com.project.car.shared.action.CreateUploadUrlAction;

public class MyHandlerModule extends HandlerModule {
    @Override
    protected void configureHandlers() {

        bindHandler(CreateUploadUrlAction.class, CreateUploadUrlHandler.class);
    }
}
