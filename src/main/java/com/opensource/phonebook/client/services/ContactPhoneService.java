package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.ContactPhoneDTO;

@RemoteServiceRelativePath("contactPhoneRpc")
public interface ContactPhoneService extends RemoteService
{

    ContactPhoneDTO add(ContactPhoneDTO record);

    void remove(ContactPhoneDTO record);

    void update(ContactPhoneDTO record);

    List<ContactPhoneDTO> fetch(ContactPhoneDTO record);

    List<ContactPhoneDTO> fetch(ContactDTO record);

    List<ContactPhoneDTO> fetch();

    ContactPhoneDTO fetch(long id);

}