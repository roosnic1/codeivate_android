package com.koki.codeivate;

/**
 * Created by koki on 15/02/15.
 */
public interface IContentLoaderCallback {
    public void contentLoaderSuccess();
    public void contentLoaderFail(String errorMessage);

}
