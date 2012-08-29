package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactLinkDTO;

@RemoteServiceRelativePath("contactLinkRpc")
public interface ContactLinkService extends RemoteService
{

    ContactLinkDTO add(ContactLinkDTO record);

    void remove(ContactLinkDTO record);

    ContactLinkDTO update(ContactLinkDTO record);

    List<ContactLinkDTO> fetch(ContactLinkDTO record);

    List<ContactLinkDTO> fetch(ContactDTO record);

    List<ContactLinkDTO> fetch();

    ContactLinkDTO fetch(long id);

}