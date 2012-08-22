package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.opensource.phonebook.shared.dto.ContactDTO;
import com.opensource.phonebook.shared.dto.UserDTO;

@RemoteServiceRelativePath("contactRpc")
public interface ContactService extends RemoteService  {

    ContactDTO add(ContactDTO record);
    void remove(ContactDTO record);
    void update(ContactDTO record);
    List<ContactDTO> fetch(ContactDTO record);
    List<ContactDTO> fetch(UserDTO record);
    List<ContactDTO> fetch ();
    ContactDTO fetch(long id);
    
}
