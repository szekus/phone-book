package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.shared.dto.EmailTypeDTO;

public interface EmailTypeServiceAsync {

	void add(EmailTypeDTO record, AsyncCallback<EmailTypeDTO> callback);
	void remove(EmailTypeDTO record, AsyncCallback<Void> callback);
	void update(EmailTypeDTO record, AsyncCallback<Void> callback);
	void fetch (EmailTypeDTO record, AsyncCallback<List<EmailTypeDTO>> callback);
	void fetch(AsyncCallback<List<EmailTypeDTO>> callback);
	void fetch(long id, AsyncCallback<EmailTypeDTO> callback);

}
