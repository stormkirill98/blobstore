package com.project.car.client.application.home;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import javax.inject.Inject;

public class HomeView extends ViewWithUiHandlers<HomePresenter> implements HomePresenter.MyView {
    interface Binder extends UiBinder<Widget, HomeView> {
    }

    @UiField
    FormPanel uploadForm;

    @UiField
    FileUpload fileUpload;

    @UiField
    Image img;

    @Inject
    HomeView(HomeView.Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }


    @UiHandler("fileUpload")
    public void onFileUploadChange(ChangeEvent event){
        if (!fileUpload.getFilename().isEmpty()){
            GWT.log("FileUpload ChangeHandler fileName = " + fileUpload.getFilename());

            getUiHandlers().createUploadUrl();
        }
    }

    @UiHandler("uploadForm")
    public void onUploadFormSubmit(FormPanel.SubmitEvent event){
        GWT.log("SubmitHandler onSubmit selectedFile = " + fileUpload.getFilename());
    }

    @UiHandler("uploadForm")
    public void onUploadFormSubmitComplete(FormPanel.SubmitCompleteEvent event){
        GWT.log("SubmitCompleteHandler onSubmitComplete event.GetResults = " + event.getResults());

        String result = event.getResults();

        int start = result.lastIndexOf("src=\"") + 5;
        int end = result.lastIndexOf("\"");

        String urlImg = event.getResults().substring(start, end);

        GWT.log("SubmitCompleteHandler onSubmitComplete urlImg = " + urlImg);

        img.setUrl(urlImg);
    }

    @Override
    public void setActionUploadForm(String uploadUrl) {
        GWT.log("setActionUploadForm uploadUrl = " + uploadUrl);

        uploadForm.setAction(uploadUrl);
    }

    @UiHandler("submit")
    public void onSubmit(ClickEvent event){
        GWT.log("onSubmit");
        uploadForm.submit();
    }
}
