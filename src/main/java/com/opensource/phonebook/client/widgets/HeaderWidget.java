package com.opensource.phonebook.client.widgets;

import com.opensource.phonebook.shared.dto.UserDTO;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class HeaderWidget extends VLayout
{
    private final static int HEADER_TITLE_SIZE = 70;
    private final static int HEADER_LABEL_SIZE = 250;

    private UserDTO userDto;
    private String userId;

    public HeaderWidget(UserDTO userDto)
    {
        super();
        this.userDto = userDto;

        userId = Long.toString(userDto.getId());

        setBorder("1px solid black");
        setWidth100();
        setHeight(90);
        setPadding(10);
        setBackgroundColor("#cecece");
        setMembersMargin(5);
        setLayoutMargin(10);

        addMember(createLine1());
        addMember(createLine2());
        addMember(createLine3());
    }

    private HLayout createLine1()
    {
        HLayout line1 = new HLayout();
        // line1.setBorder("5px solid green");
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

        return line1;
    }

    private HLayout createLine2()
    {
        HLayout line2 = new HLayout();
        // line2.setBorder("5px solid blue");
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

        return line2;
    }

    private HLayout createLine3()
    {

        HLayout line3 = new HLayout();
        // line3.setBorder("5px solid orange");
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

        return line3;
    }

}
