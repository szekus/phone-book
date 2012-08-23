package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactEmailDTO;

@RemoteServiceRelativePath("contactEmailRpc")
public interface ContactEmailService extends RemoteService
{

    ContactEmailDTO add(ContactEmailDTO record);

    void remove(ContactEmailDTO record);

    void update(ContactEmailDTO record);

    List<ContactEmailDTO> fetch(ContactEmailDTO record);

    List<ContactEmailDTO> fetch(ContactDTO record);

    List<ContactEmailDTO> fetch();

    ContactEmailDTO fetch(long id);

}
