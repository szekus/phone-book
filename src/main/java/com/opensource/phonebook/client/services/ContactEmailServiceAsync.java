package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactEmailDTO;

public interface ContactEmailServiceAsync
{
    void add(ContactEmailDTO record, AsyncCallback<ContactEmailDTO> callback);

    void remove(ContactEmailDTO record, AsyncCallback<Void> callback);

    void update(ContactEmailDTO record, AsyncCallback<ContactEmailDTO> callback);

    void fetch(ContactEmailDTO record, AsyncCallback<List<ContactEmailDTO>> callback);

    void fetch(ContactDTO record, AsyncCallback<List<ContactEmailDTO>> callback);

    void fetch(AsyncCallback<List<ContactEmailDTO>> callback);

    void fetch(long id, AsyncCallback<ContactEmailDTO> callback);

}
