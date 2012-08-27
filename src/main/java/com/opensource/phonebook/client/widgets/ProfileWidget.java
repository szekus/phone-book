package com.opensource.phonebook.client.widgets;

import com.opensource.phonebook.client.datasource.UserDS;
import com.opensource.phonebook.shared.Constants;
import com.opensource.phonebook.shared.dto.UserDTO;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ProfileWidget extends HLayout
{
    final UserDS userDS = UserDS.getInstance();

    private UserDTO userDto;

    private final DynamicForm profileForm = new DynamicForm();
    private TextItem idField;
    private TextItem usernameField = new TextItem(Constants.USER_USERNAME);
    private TextItem firstnameField = new TextItem(Constants.USER_FIRST_NAME);
    private TextItem lastnameField = new TextItem(Constants.USER_LAST_NAME);
    private TextItem emailField = new TextItem(Constants.USER_EMAIL);
    private TextItem securityQuestion1Field = new TextItem(Constants.USER_SECURITY_QUESTION_1);
    private TextItem securityAnswer1Field = new TextItem(Constants.USER_SECURITY_ANSWER_1);
    private TextItem securityQuestion2Field = new TextItem(Constants.USER_SECURITY_QUESTION_2);
    private TextItem securityAnswer2Field = new TextItem(Constants.USER_SECURITY_ANSWER_2);
    private TextItem positionField = new TextItem(Constants.USER_POSITION_ID);
    private DateItem birthdateField = new DateItem(Constants.USER_BIRTHDATE);

    private final DynamicForm passwordForm = new DynamicForm();
    private PasswordItem oldPasswordField = new PasswordItem(Constants.USER_OLD_PASSWORD);
    private PasswordItem newPasswordField = new PasswordItem(Constants.USER_NEW_PASSWORD);
    private PasswordItem retypePasswordField = new PasswordItem(Constants.USER_RETYPE_PASSWORD);

    public ProfileWidget(UserDTO userDto)
    {
        super();
        this.userDto = userDto;

        setWidth100();
        setHeight100();
        // setBorder("1px solid black");

        addMember(getProfileLayout());
        addMember(getPasswordLayout());
    }

    private VLayout getProfileLayout()
    {
        VLayout profileLayout = new VLayout();
        profileLayout.setAutoWidth();
        profileLayout.setHeight100();
        profileLayout.setPadding(10);
        profileLayout.setBorder("5px solid red");

        IButton saveProfileBtn = new IButton("Save");
        saveProfileBtn.addClickHandler(new ClickHandler()
        {

            @Override
            public void onClick(ClickEvent event)
            {
                Record record = new ListGridRecord();
                record.setAttribute(Constants.USER_ID, Long.parseLong(idField.getValueAsString()));
                record.setAttribute(Constants.USER_ACTIVE, true);
                record.setAttribute(Constants.USER_POSITION_ID, 2);
                record.setAttribute(Constants.USER_USERNAME, usernameField.getValueAsString());
                record.setAttribute(Constants.USER_EMAIL, emailField.getValueAsString());
                record.setAttribute(Constants.USER_FIRST_NAME, firstnameField.getValueAsString());
                record.setAttribute(Constants.USER_LAST_NAME, lastnameField.getValueAsString());
                record.setAttribute(Constants.USER_SECURITY_QUESTION_1, securityQuestion1Field.getValueAsString());
                record.setAttribute(Constants.USER_SECURITY_QUESTION_2, securityQuestion2Field.getValueAsString());
                record.setAttribute(Constants.USER_SECURITY_ANSWER_1, securityAnswer1Field.getValueAsString());
                record.setAttribute(Constants.USER_SECURITY_ANSWER_2, securityAnswer2Field.getValueAsString());
                record.setAttribute(Constants.USER_BIRTHDATE, birthdateField.getValueAsDate());
                userDS.updateData(record);
            }

        });

        profileLayout.addMember(getProfileForm());
        profileLayout.addMember(saveProfileBtn);

        return profileLayout;
    }

    private DynamicForm getProfileForm()
    {
        profileForm.setDataSource(userDS);
        profileForm.setIsGroup(true);
        profileForm.setGroupTitle("Update Your Profile");
        profileForm.setTitleWidth(200);
        profileForm.setWidth(400);
        // profileForm.setBorder("1px solid blue");

        idField = new TextItem(Constants.USER_ID);
        idField.setIconVAlign(VerticalAlignment.CENTER);
        idField.setTabIndex(0);
        idField.setRequired(true);
        idField.setSelectOnFocus(true);
        idField.setDefaultValue(Long.toString(userDto.getId()));
        idField.setVisible(false);

        usernameField.setTitle(Constants.TITLE_USER_USERNAME);
        usernameField.setIconVAlign(VerticalAlignment.CENTER);
        usernameField.setRequired(true);
        usernameField.setDisabled(true);
        usernameField.setDefaultValue(userDto.getUsername());

        firstnameField.setTitle(Constants.TITLE_USER_FIRST_NAME);
        firstnameField.setIconVAlign(VerticalAlignment.CENTER);
        firstnameField.setRequired(true);
        firstnameField.setDefaultValue(userDto.getFirstname());

        lastnameField.setTitle(Constants.TITLE_USER_LAST_NAME);
        lastnameField.setIconVAlign(VerticalAlignment.CENTER);
        lastnameField.setRequired(true);
        lastnameField.setDefaultValue(userDto.getLastname());

        emailField.setTitle(Constants.TITLE_USER_EMAIL);
        emailField.setIconVAlign(VerticalAlignment.CENTER);
        emailField.setRequired(true);
        emailField.setDefaultValue(userDto.getEmail());

        securityQuestion1Field.setTitle(Constants.TITLE_USER_SECURITY_QUESTION_1);
        securityQuestion1Field.setIconVAlign(VerticalAlignment.CENTER);
        securityQuestion1Field.setRequired(true);
        securityQuestion1Field.setDefaultValue(userDto.getSecurityQuestion1());

        securityAnswer1Field.setTitle(Constants.TITLE_USER_SECURITY_ANSWER_1);
        securityAnswer1Field.setIconVAlign(VerticalAlignment.CENTER);
        securityAnswer1Field.setRequired(true);
        securityAnswer1Field.setDefaultValue(userDto.getSecurityAnswer1());

        securityQuestion2Field.setTitle(Constants.TITLE_USER_SECURITY_QUESTION_2);
        securityQuestion2Field.setIconVAlign(VerticalAlignment.CENTER);
        securityQuestion2Field.setRequired(true);
        securityQuestion2Field.setDefaultValue(userDto.getSecurityQuestion2());

        securityAnswer2Field.setTitle(Constants.TITLE_USER_SECURITY_ANSWER_2);
        securityAnswer2Field.setIconVAlign(VerticalAlignment.CENTER);
        securityAnswer2Field.setRequired(true);
        securityAnswer2Field.setDefaultValue(userDto.getSecurityAnswer2());

        positionField.setTitle(Constants.TITLE_USER_POSITION_ID);
        positionField.setDefaultValue(2);
        positionField.setVisible(false);

        birthdateField.setTitle(Constants.TITLE_USER_BIRTHDATE);
        birthdateField.setIconVAlign(VerticalAlignment.CENTER);
        birthdateField.setDefaultValue(userDto.getBirthdate());
        birthdateField.setVisible(true);
        birthdateField.setRequired(true);

        profileForm.setFields(idField, usernameField, firstnameField, lastnameField, emailField, positionField,
            securityQuestion1Field, securityAnswer1Field, securityQuestion2Field, securityAnswer2Field, birthdateField);

        return profileForm;
    }

    private VLayout getPasswordLayout()
    {
        VLayout passwordLayout = new VLayout();
        passwordLayout.setAutoWidth();
        passwordLayout.setHeight100();
        passwordLayout.setPadding(10);
        passwordLayout.setBorder("5px solid red");

        IButton savePasswordBtn = new IButton("Save");
        savePasswordBtn.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                String passwordValidationMessage = getPasswordValidation(passwordForm);
                if (passwordValidationMessage == null || "".equals(passwordValidationMessage))
                {
                    Record record = new ListGridRecord();
                    record.setAttribute(Constants.USER_ID, Long.parseLong(idField.getValueAsString()));
                    record.setAttribute(Constants.USER_ACTIVE, true);
                    record.setAttribute(Constants.USER_POSITION_ID, 2);
                    record.setAttribute(Constants.USER_NEW_PASSWORD, newPasswordField.getValueAsString());
                    userDS.updateData(record);
                }
                else
                {
                    SC.say("Update Password", passwordValidationMessage);
                }
            }

        });

        passwordLayout.addMember(getPasswordForm());
        passwordLayout.addMember(savePasswordBtn);

        return passwordLayout;
    }

    private String getPasswordValidation(DynamicForm passwordForm)
    {
        String passwordValidationMessage = null;
        StringBuffer sb = new StringBuffer();
        if (oldPasswordField.getValue() == null)
        {
            sb.append("Old Password cannot be left blank!<br/>");
        }
        if (newPasswordField.getValue() == null)
        {
            sb.append("New Password cannot be left blank!<br/>");
        }
        if (retypePasswordField.getValue() == null)
        {
            sb.append("Retype Password cannot be left blank!<br/>");
        }
        if (!userDto.getPassword().equals(oldPasswordField.getValue()))
        {
            sb.append("Old Password does not match current password!<br/>");
        }
        if (newPasswordField.getValue() != null && retypePasswordField.getValue() != null)
        {
            if (!newPasswordField.getValue().equals(retypePasswordField.getValue()))
            {
                sb.append("ReTyped Password does not match new password!<br/>");
            }
        }
        passwordValidationMessage = sb.toString();
        return passwordValidationMessage;
    }

    private DynamicForm getPasswordForm()
    {
        passwordForm.setDataSource(userDS);
        passwordForm.setIsGroup(true);
        passwordForm.setGroupTitle("Change Your Password");
        passwordForm.setTitleWidth(200);
        passwordForm.setWidth(400);
        // passwordForm.setBorder("1px solid blue");

        idField = new TextItem(Constants.USER_ID);
        idField.setIconVAlign(VerticalAlignment.CENTER);
        idField.setTabIndex(0);
        idField.setRequired(true);
        idField.setSelectOnFocus(true);
        idField.setDefaultValue(Long.toString(userDto.getId()));
        idField.setVisible(false);

        oldPasswordField.setTitle(Constants.TITLE_USER_OLD_PASSWORD);
        oldPasswordField.setIconVAlign(VerticalAlignment.CENTER);
        oldPasswordField.setRequired(true);

        newPasswordField.setTitle(Constants.TITLE_USER_NEW_PASSWORD);
        newPasswordField.setIconVAlign(VerticalAlignment.CENTER);
        newPasswordField.setRequired(true);

        retypePasswordField.setTitle(Constants.TITLE_USER_RETYPE_PASSWORD);
        retypePasswordField.setIconVAlign(VerticalAlignment.CENTER);
        retypePasswordField.setRequired(true);

        passwordForm.setFields(idField, oldPasswordField, newPasswordField, retypePasswordField);

        return passwordForm;
    }

}
