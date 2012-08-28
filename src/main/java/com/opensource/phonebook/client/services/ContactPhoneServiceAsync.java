package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactPhoneDTO;

public interface ContactPhoneServiceAsync
{
    void add(ContactPhoneDTO record, AsyncCallback<ContactPhoneDTO> callback);

    void remove(ContactPhoneDTO record, AsyncCallback<Void> callback);

    void fetch(ContactPhoneDTO record, AsyncCallback<List<ContactPhoneDTO>> callback);

    void fetch(ContactDTO record, AsyncCallback<List<ContactPhoneDTO>> callback);

    void fetch(AsyncCallback<List<ContactPhoneDTO>> callback);

    void fetch(long id, AsyncCallback<ContactPhoneDTO> callback);

    void update(ContactPhoneDTO testRec, AsyncCallback<ContactPhoneDTO> asyncCallback);

}
