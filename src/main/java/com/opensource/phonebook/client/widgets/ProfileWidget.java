package com.opensource.phonebook.client.widgets;

import com.opensource.phonebook.client.datasource.UserDS;
import com.opensource.phonebook.shared.Constants;
import com.opensource.phonebook.shared.dto.UserDTO;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ProfileWidget extends HLayout
{

    final UserDS userDS = UserDS.getInstance();
    
    private UserDTO userDto; 

    public ProfileWidget(UserDTO userDto)
    {
        super();
        this.userDto = userDto;
        
        setWidth100();
        setHeight100();
        setBorder("1px solid black");

        addMember(getSignupLayout());
    }

    private VLayout getSignupLayout()
    {
        VLayout signupLayout = new VLayout();
        signupLayout.setWidth(500);
        signupLayout.setHeight100();
        // signupLayout.setBorder("1px solid green");

        final DynamicForm signupForm = new DynamicForm();
        signupForm.setDataSource(userDS);
        signupForm.setTitleWidth(200);
        signupForm.setWidth(400);

        final TextItem idField;
        idField = new TextItem(Constants.USER_ID);
        idField.setIconVAlign(VerticalAlignment.CENTER);
        idField.setTabIndex(0);
        idField.setRequired(true);
        idField.setSelectOnFocus(true);
        idField.setDefaultValue(Long.toString(userDto.getId()));
        idField.setVisible(false);

        final TextItem usernameField = new TextItem(Constants.USER_USERNAME);
        usernameField.setTitle(Constants.TITLE_USERNAME);
        usernameField.setIconVAlign(VerticalAlignment.CENTER);
        usernameField.setRequired(true);
        usernameField.setDisabled(true);
        usernameField.setDefaultValue(userDto.getUsername());

        final PasswordItem passwordField = new PasswordItem(Constants.USER_PASSWORD);
        passwordField.setTitle(Constants.TITLE_PASSWORD);
        passwordField.setIconVAlign(VerticalAlignment.CENTER);
        passwordField.setRequired(true);
        passwordField.setDefaultValue(userDto.getPassword());

        final TextItem firstnameField = new TextItem(Constants.USER_FIRST_NAME);
        firstnameField.setTitle(Constants.TITLE_FIRSTNAME);
        firstnameField.setIconVAlign(VerticalAlignment.CENTER);
        firstnameField.setRequired(true);
        firstnameField.setDefaultValue(userDto.getFirstname());

        final TextItem lastnameField = new TextItem(Constants.USER_LAST_NAME);
        lastnameField.setTitle(Constants.TITLE_LASTNAME);
        lastnameField.setIconVAlign(VerticalAlignment.CENTER);
        lastnameField.setRequired(true);
        lastnameField.setDefaultValue(userDto.getLastname());

        final TextItem emailField = new TextItem(Constants.USER_EMAIL);
        emailField.setTitle(Constants.TITLE_EMAIL);
        emailField.setIconVAlign(VerticalAlignment.CENTER);
        emailField.setRequired(true);
        emailField.setDefaultValue(userDto.getEmail());

        final TextItem securityQuestion1 = new TextItem("securityQuestion1");
        securityQuestion1.setTitle(Constants.SECURITY_QUESTION_1);
        securityQuestion1.setIconVAlign(VerticalAlignment.CENTER);
        securityQuestion1.setRequired(true);
        securityQuestion1.setDefaultValue(userDto.getSecurityQuestion1());

        final TextItem securityAnswer1 = new TextItem("securityAnswer1");
        securityAnswer1.setTitle(Constants.SECURITY_ANSWER_1);
        securityAnswer1.setIconVAlign(VerticalAlignment.CENTER);
        securityAnswer1.setRequired(true);
        securityAnswer1.setDefaultValue(userDto.getSecurityAnswer1());

        final TextItem securityQuestion2 = new TextItem("securityQuestion2");
        securityQuestion2.setTitle(Constants.SECURITY_QUESTION_2);
        securityQuestion2.setIconVAlign(VerticalAlignment.CENTER);
        securityQuestion2.setRequired(true);
        securityQuestion2.setDefaultValue(userDto.getSecurityQuestion2());

        final TextItem securityAnswer2 = new TextItem("securityAnswer2");
        securityAnswer2.setTitle(Constants.SECURITY_ANSWER_2);
        securityAnswer2.setIconVAlign(VerticalAlignment.CENTER);
        securityAnswer2.setRequired(true);
        securityAnswer2.setDefaultValue(userDto.getSecurityAnswer2());

        final TextItem positionField = new TextItem(Constants.USER_POSITION_ID);
        positionField.setTitle(Constants.TITLE_POSITION);
        positionField.setDefaultValue(2);
        positionField.setVisible(false);

        IButton saveBtn = new IButton("Save");
        saveBtn.addClickHandler(new ClickHandler()
        {

            @Override
            public void onClick(ClickEvent event)
            {
                Record record = new ListGridRecord();
                record.setAttribute(Constants.USER_ID, idField.getValue());
                record.setAttribute(Constants.USER_ACTIVE, true);
                record.setAttribute(Constants.USER_POSITION_ID, 2);
                record.setAttribute(Constants.USER_USERNAME, usernameField.getValueAsString());
                record.setAttribute(Constants.USER_PASSWORD, passwordField.getValueAsString());
                record.setAttribute(Constants.USER_EMAIL, emailField.getValueAsString());
                record.setAttribute(Constants.USER_FIRST_NAME, firstnameField.getValueAsString());
                record.setAttribute(Constants.USER_LAST_NAME, lastnameField.getValueAsString());
                record.setAttribute(Constants.USER_SECURITY_QUESTION_1, securityQuestion1.getValueAsString());
                record.setAttribute(Constants.USER_SECURITY_QUESTION_2, securityQuestion2.getValueAsString());
                record.setAttribute(Constants.USER_SECURITY_ANSWER_1, securityAnswer1.getValueAsString());
                record.setAttribute(Constants.USER_SECURITY_ANSWER_2, securityAnswer2.getValueAsString());
                userDS.addData(record);
            }

        });

        signupForm.setFields(idField, usernameField, passwordField, firstnameField, lastnameField, emailField,
            positionField, securityQuestion1, securityAnswer1, securityQuestion2, securityAnswer2);

        signupForm.setBorder("1px solid blue");

        signupLayout.addMember(signupForm);
        signupLayout.addMember(saveBtn);

        return signupLayout;
    }

}
