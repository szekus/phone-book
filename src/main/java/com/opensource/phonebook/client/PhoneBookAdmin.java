package com.opensource.phonebook.client;

import com.google.gwt.event.shared.HandlerManager;
import com.opensource.phonebook.client.tabs.MemberTab;
import com.opensource.phonebook.shared.dto.UserDTO;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class PhoneBookAdmin extends Canvas
{
    private final HandlerManager eventBus;

    private UserDTO userDto;
    
    private final static int HEADER_TITLE_SIZE = 70;
    private final static int HEADER_LABEL_SIZE = 250;

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

        VLayout header = createHeader();

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

    private VLayout createHeader()
    {
        VLayout header = new VLayout();
        header.setBorder("1px solid black");
        header.setWidth100();
        header.setHeight(90);
        header.setPadding(10);
        header.setBackgroundColor("#cecece");
        header.setMembersMargin(5);
        header.setLayoutMargin(10);

        HLayout line1 = new HLayout();
        //line1.setBorder("5px solid green");
        line1.setAutoHeight();
        line1.setAutoWidth();
        
        Label nameTitle = new Label("nameTitle");
        nameTitle.setStyleName("headerTitle");
        nameTitle.setContents("Name:");
        nameTitle.setAutoWidth();

        Label nameLabel = new Label("nameLabel");
        nameLabel.setStyleName("headerLabel");
        nameLabel.setContents(userDto.getLastname() + ", " + userDto.getFirstname());
        nameLabel.setWidth(HEADER_LABEL_SIZE);
        
        Label emailTitle = new Label("emailTitle");
        emailTitle.setStyleName("headerTitle");
        emailTitle.setContents("Email:");
        emailTitle.setAutoWidth();

        Label emailLabel = new Label("email");
        emailLabel.setStyleName("headerLabel");
        emailLabel.setContents(userDto.getEmail());
        emailLabel.setWidth(HEADER_LABEL_SIZE);
        
        Label positionTitle = new Label("positionTitle");
        positionTitle.setStyleName("headerTitle");
        positionTitle.setContents("Position:");
        positionTitle.setAutoWidth();

        Label positionLabel = new Label("position");
        positionLabel.setStyleName("headerLabel");
        positionLabel.setContents(userDto.getPosition().getDescription());
        positionLabel.setWidth(HEADER_LABEL_SIZE);
        
        line1.addMember(nameTitle);
        line1.addMember(nameLabel);
        line1.addMember(emailTitle);
        line1.addMember(emailLabel);
        line1.addMember(positionTitle);
        line1.addMember(positionLabel);
        
        HLayout line2 = new HLayout();
        //line2.setBorder("5px solid blue");
        line2.setAutoHeight();
        line2.setAutoWidth();
        
        Label usernameTitle = new Label("usernameTitle");
        usernameTitle.setStyleName("headerTitle");
        usernameTitle.setContents("Username:");
        usernameTitle.setAutoWidth();

        Label usernameLabel = new Label("username");
        usernameLabel.setStyleName("headerLabel");
        usernameLabel.setContents(userDto.getUsername());
        usernameLabel.setWidth(HEADER_LABEL_SIZE);
        
        Label dobTitle = new Label("usernameTitle");
        dobTitle.setStyleName("headerTitle");
        dobTitle.setContents("Date of Birth:");
        dobTitle.setWidth(104);

        Label dobLabel = new Label("username");
        dobLabel.setStyleName("headerLabel");
        dobLabel.setContents("11/03/1966");
        dobLabel.setWidth(HEADER_LABEL_SIZE);
        
        line2.addMember(usernameTitle);
        line2.addMember(usernameLabel);
        line2.addMember(dobTitle);
        line2.addMember(dobLabel);
        
        HLayout line3 = new HLayout();
        //line3.setBorder("5px solid orange");
        line3.setAutoHeight();
        line3.setAutoWidth();
        
        Label contactsTitle = new Label("contactsTitle");
        contactsTitle.setStyleName("headerTitle");
        contactsTitle.setContents("Number of Contacts:");
        contactsTitle.setWidth(160);

        Label contactsLabel = new Label("contacts");
        contactsLabel.setStyleName("headerLabel");
        contactsLabel.setContents(Integer.toString(userDto.getContacts().size()));
        contactsLabel.setWidth(HEADER_LABEL_SIZE);
        
        line3.addMember(contactsTitle);
        line3.addMember(contactsLabel);
        
        header.addMember(line1);
        header.addMember(line2);
        header.addMember(line3);

        return header;
    }
}
