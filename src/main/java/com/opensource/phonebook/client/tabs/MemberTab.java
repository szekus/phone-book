package com.opensource.phonebook.client.tabs;

import com.opensource.phonebook.client.widgets.ContactWidget;
import com.opensource.phonebook.client.widgets.ProfileWidget;
import com.opensource.phonebook.shared.dto.UserDTO;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class MemberTab extends Canvas
{
	public MemberTab(UserDTO userDto) {
		super();
		
		VStack mainLayout = new VStack();
		mainLayout.setWidth100();
		mainLayout.setHeight100();
		mainLayout.setBackgroundColor("#3300ff");
		mainLayout.setBorder("1px solid black");
		mainLayout.setAlign(Alignment.CENTER);
		
		final TabSet topTabSet = new TabSet();  
        topTabSet.setTabBarPosition(Side.TOP);  
        topTabSet.setWidth100();  
        topTabSet.setHeight100();  
  
        Tab tTab1 = new Tab("Your Info");
        tTab1.setPane(new ProfileWidget(userDto));
        topTabSet.addTab(tTab1);  
          
        Tab tTab2 = new Tab("Contacts");
        tTab2.setPane(new ContactWidget(userDto));
        topTabSet.addTab(tTab2); 
                    
        mainLayout.addMember(topTabSet);
		
		addChild(mainLayout);
	}
	  
}
