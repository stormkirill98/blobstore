package com.project.car.server.dispatch.common;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.project.car.client.place.NameTokens;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UploadServlet extends HttpServlet {

    private static final BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        System.out.println("            UploadServlet doPost");

        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("myImage");

        if (blobKeys == null || blobKeys.isEmpty()) {
            System.out.println("            blobKeys is null or empty");

            res.sendRedirect("/");
        } else {
            System.out.println("            blobKeys[0] = " + blobKeys.get(0).getKeyString());
            System.out.println("            blobKeys.size = " + blobKeys.size());

            res.sendRedirect("/serve?blob-key=" + blobKeys.get(0).getKeyString());
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
        blobstoreService.serve(blobKey, resp);
    }
}
