package com.opensource.phonebook.client.widgets;

import com.google.gwt.user.client.Cookies;
import com.opensource.phonebook.client.LoginDialog;
import com.opensource.phonebook.shared.CalendarUtil;
import com.opensource.phonebook.shared.Constants;
import com.opensource.phonebook.shared.dto.UserDTO;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class HeaderWidget extends HLayout
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

        addMember(createBox1());
        addMember(createBox2());
        addMember(createBox3());
        addMember(createBox4());
    }

    private VLayout createBox1()
    {
        VLayout box1 = new VLayout();
        // box1.setBorder("5px solid red");
        box1.setAutoHeight();
        box1.setAutoWidth();

        HLayout line1 = new HLayout();
        Label nameTitle = new Label("nameTitle");
        nameTitle.setStyleName("headerTitle");
        nameTitle.setContents("Name:");
        nameTitle.setAutoWidth();

        Label nameLabel = new Label("nameLabel");
        nameLabel.setStyleName("headerLabel");
        nameLabel.setContents(userDto.getLastname() + ", " + userDto.getFirstname());
        nameLabel.setWidth(HEADER_LABEL_SIZE);

        line1.addMember(nameTitle);
        line1.addMember(nameLabel);

        HLayout line2 = new HLayout();
        Label usernameTitle = new Label("usernameTitle");
        usernameTitle.setStyleName("headerTitle");
        usernameTitle.setContents("Username:");
        usernameTitle.setAutoWidth();

        Label usernameLabel = new Label("username");
        usernameLabel.setStyleName("headerLabel");
        usernameLabel.setContents(userDto.getUsername());
        usernameLabel.setWidth(HEADER_LABEL_SIZE);

        line2.addMember(usernameTitle);
        line2.addMember(usernameLabel);

        HLayout line3 = new HLayout();
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

        box1.addMember(line1);
        box1.addMember(line2);
        box1.addMember(line3);

        return box1;
    }

    private VLayout createBox2()
    {
        VLayout box2 = new VLayout();
        // box2.setBorder("5px solid green");
        box2.setAutoHeight();
        box2.setAutoWidth();

        HLayout line1 = new HLayout();
        Label emailTitle = new Label("emailTitle");
        emailTitle.setStyleName("headerTitle");
        emailTitle.setContents("Email:");
        emailTitle.setAutoWidth();

        Label emailLabel = new Label("email");
        emailLabel.setStyleName("headerLabel");
        emailLabel.setContents(userDto.getEmail());
        emailLabel.setWidth(HEADER_LABEL_SIZE);

        line1.addMember(emailTitle);
        line1.addMember(emailLabel);

        HLayout line2 = new HLayout();
        Label dobTitle = new Label("usernameTitle");
        dobTitle.setStyleName("headerTitle");
        dobTitle.setContents("Date of Birth:");
        dobTitle.setWidth(104);

        String dob = CalendarUtil.getStringFromDate(userDto.getBirthdate());
        Label dobLabel = new Label("username");
        dobLabel.setStyleName("headerLabel");
        dobLabel.setContents(dob);
        dobLabel.setWidth(HEADER_LABEL_SIZE);

        line2.addMember(dobTitle);
        line2.addMember(dobLabel);

        box2.addMember(line1);
        box2.addMember(line2);

        return box2;
    }

    private VLayout createBox3()
    {
        VLayout box3 = new VLayout();
        // box3.setBorder("5px solid orange");
        box3.setAutoHeight();
        box3.setAutoWidth();

        HLayout line1 = new HLayout();
        Label positionTitle = new Label("positionTitle");
        positionTitle.setStyleName("headerTitle");
        positionTitle.setContents("Position:");
        positionTitle.setAutoWidth();

        Label positionLabel = new Label("position");
        positionLabel.setStyleName("headerLabel");
        positionLabel.setContents(userDto.getPosition().getDescription());
        positionLabel.setWidth(HEADER_LABEL_SIZE);

        line1.addMember(positionTitle);
        line1.addMember(positionLabel);

        HLayout line2 = new HLayout();
        Label ageTitle = new Label("ageTitle");
        ageTitle.setStyleName("headerTitle");
        ageTitle.setContents("Age:");
        ageTitle.setAutoWidth();

        int age = CalendarUtil.getAge(userDto.getBirthdate());
        String status = "";
        if (age >= 18)
        {
            status = " (Adult)";
        }
        else
        {
            status = " (Youth)";
        }

        Label ageLabel = new Label("age");
        ageLabel.setStyleName("headerLabel");
        ageLabel.setContents(Integer.toString(age) + status);
        ageLabel.setWidth(HEADER_LABEL_SIZE);

        line2.addMember(ageTitle);
        line2.addMember(ageLabel);

        box3.addMember(line1);
        box3.addMember(line2);

        return box3;
    }

    private VLayout createBox4()
    {
        VLayout box4 = new VLayout();
        // box4.setBorder("5px solid red");
        box4.setAutoHeight();
        box4.setAutoWidth();

        IButton logoutButton = new IButton(Constants.LOGOUT);
        logoutButton.setLayoutAlign(VerticalAlignment.CENTER);
        logoutButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                Cookies.removeCookie(Constants.COOKIES_USERNAME);
                Cookies.removeCookie(Constants.COOKIES_PHONEBOOK_ID);
                Cookies.removeCookie(Constants.COOKIES_USER_TYPE);
                Cookies.removeCookie(Constants.COOKIES_VALID_PHONEBOOK_ID);
                hide();
                LoginDialog.getInstance().displayIfRequired(null);
            }
        });

        box4.addMember(logoutButton);

        return box4;
    }

}
