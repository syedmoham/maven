package com.syedm.app.repository;

import java.util.List;

public interface MessageRepository {
    List<String> getMessages() throws Exception;
}
