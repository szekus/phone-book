package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.shared.dto.PhoneTypeDTO;

public interface PhoneTypeServiceAsync {

	void add(PhoneTypeDTO record, AsyncCallback<PhoneTypeDTO> callback);
	void remove(PhoneTypeDTO record, AsyncCallback<Void> callback);
	void update(PhoneTypeDTO record, AsyncCallback<Void> callback);
	void fetch (PhoneTypeDTO record, AsyncCallback<List<PhoneTypeDTO>> callback);
	void fetch(AsyncCallback<List<PhoneTypeDTO>> callback);
	void fetch(long id, AsyncCallback<PhoneTypeDTO> callback);

}
