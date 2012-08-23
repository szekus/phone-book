package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactLinkDTO;

public interface ContactLinkServiceAsync
{
    void add(ContactLinkDTO record, AsyncCallback<ContactLinkDTO> callback);

    void remove(ContactLinkDTO record, AsyncCallback<Void> callback);

    void update(ContactLinkDTO record, AsyncCallback<Void> callback);

    void fetch(ContactLinkDTO record, AsyncCallback<List<ContactLinkDTO>> callback);

    void fetch(ContactDTO record, AsyncCallback<List<ContactLinkDTO>> callback);

    void fetch(AsyncCallback<List<ContactLinkDTO>> callback);

    void fetch(long id, AsyncCallback<ContactLinkDTO> callback);

}
