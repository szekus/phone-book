package com.opensource.phonebook.client;

import com.google.gwt.event.shared.HandlerManager;
import com.opensource.phonebook.client.tabs.MemberTab;
import com.opensource.phonebook.client.widgets.HeaderWidget;
import com.opensource.phonebook.shared.dto.UserDTO;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class PhoneBookAdmin extends Canvas
{
    private final HandlerManager eventBus;

    private UserDTO userDto;

    public PhoneBookAdmin(HandlerManager eventBus, UserDTO userDto)
    {
        super();
        this.eventBus = null;
        this.userDto = userDto;

        Canvas canvas = new Canvas();
        // canvas.setBorder("1px solid orange");
        canvas.setHeight100();
        canvas.setWidth100();

        VLayout main = new VLayout();
        main.setBorder("1px solid black");
        main.setHeight100();
        main.setWidth100();
        main.setMembersMargin(5);
        main.setLayoutMargin(10);

        HeaderWidget header = new HeaderWidget(userDto);

        HLayout body = new HLayout();
        body.setBorder("1px solid blue");
        body.setWidth100();
        body.setHeight100();
        body.setPadding(10);
        body.setBackgroundColor("#e0e0e0");
        body.setMembersMargin(5);
        body.setLayoutMargin(10);

        final TabSet topTabSet = new TabSet();
        topTabSet.setTabBarPosition(Side.TOP);
        topTabSet.setWidth100();
        topTabSet.setHeight100();

        Tab tTab1 = new Tab("Your Info");
        tTab1.setPane(new MemberTab(userDto));
        topTabSet.addTab(tTab1);

        Tab tTab3 = new Tab("MB Counselor");
        // tTab3.setPane(new AdministrationTab())
        topTabSet.addTab(tTab3);

        body.addMember(topTabSet);

        main.addMember(header);
        main.addMember(body);

        canvas.addChild(main);
        canvas.draw();
    }

}
