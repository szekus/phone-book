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

public class PhoneBookMain extends Canvas
{
    private final HandlerManager eventBus;

    private UserDTO userDto;

    private final static int HEADER_TITLE_SIZE = 70;
    private final static int HEADER_LABEL_SIZE = 250;

    public PhoneBookMain(HandlerManager eventBus, UserDTO userDto)
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

        Tab tTab1 = new Tab("Profile");
        tTab1.setPane(new MemberTab(userDto));
        topTabSet.addTab(tTab1);

        body.addMember(topTabSet);

        main.addMember(header);
        main.addMember(body);

        canvas.addChild(main);
        canvas.draw();
    }

}
