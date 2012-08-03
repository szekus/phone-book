package com.opensource.phonebook.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.shared.dto.LinkTypeDTO;

public interface LinkTypeServiceAsync {

	void add(LinkTypeDTO record, AsyncCallback<LinkTypeDTO> callback);
	void remove(LinkTypeDTO record, AsyncCallback<Void> callback);
	void update(LinkTypeDTO record, AsyncCallback<Void> callback);
	void fetch (LinkTypeDTO record, AsyncCallback<List<LinkTypeDTO>> callback);
	void fetch(AsyncCallback<List<LinkTypeDTO>> callback);
	void fetch(long id, AsyncCallback<LinkTypeDTO> callback);

}
