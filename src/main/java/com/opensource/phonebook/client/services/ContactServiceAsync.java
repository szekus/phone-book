package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.UserDTO;

public interface ContactServiceAsync {

    void add(ContactDTO record, AsyncCallback<ContactDTO> callback);
    void remove(ContactDTO record, AsyncCallback<Void> callback);
    void update(ContactDTO record, AsyncCallback<Void> callback);
    void fetch (ContactDTO record, AsyncCallback<List<ContactDTO>> callback);
    void fetch (UserDTO record, AsyncCallback<List<ContactDTO>> callback);
    void fetch(AsyncCallback<List<ContactDTO>> callback);
    void fetch(long id, AsyncCallback<ContactDTO> callback);

}