package com.project.car.server.dispatch.common;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;
import com.project.car.server.dispatch.MyAbstractActionHandler;
import com.project.car.shared.action.CreateUploadUrlAction;
import com.project.car.shared.action.CreateUploadUrlResult;

public class CreateUploadUrlHandler extends MyAbstractActionHandler<CreateUploadUrlAction, CreateUploadUrlResult> {
    @Inject
    public CreateUploadUrlHandler() {
        super(CreateUploadUrlAction.class);
    }

    @Override
    public CreateUploadUrlResult execute(CreateUploadUrlAction action, ExecutionContext context) throws ActionException {
        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

        String uploadUrl = blobstoreService.createUploadUrl("/upload");

        System.out.println("            CreateUploadUrlHandler uploadUrl = " + uploadUrl);

        return new CreateUploadUrlResult(uploadUrl);
    }
}
