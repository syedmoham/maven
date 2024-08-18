package com.syedm.app.service;

import java.util.List;

public interface MessageService {
    public List<String> getMessages() throws Exception;
    public void logNormalThread();
    public void logAsyncThread();
}
