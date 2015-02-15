package com.koki.codeivate;

import com.koki.codeivate.Models.CodeivateUser;

/**
 * Created by koki on 15/02/15.
 */
public interface IContentLoaderCallback {
    public void contentLoaderSuccess(CodeivateUser user);
    public void contentLoaderFail(String errorMessage);

}
