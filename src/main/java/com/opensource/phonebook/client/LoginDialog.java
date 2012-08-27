package com.opensource.phonebook.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.client.datasource.LoginDS;
import com.opensource.phonebook.client.datasource.UserDS;
import com.opensource.phonebook.client.services.LoginService;
import com.opensource.phonebook.client.services.LoginServiceAsync;
import com.opensource.phonebook.shared.Constants;
import com.opensource.phonebook.shared.dto.UserDTO;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.events.SubmitValuesEvent;
import com.smartgwt.client.widgets.form.events.SubmitValuesHandler;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class LoginDialog extends Window
{
    private final HandlerManager eventBus = null;

    final UserDS userDS = UserDS.getInstance();

    static private LoginDialog instance = null;

    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    private final LoginServiceAsync loginService = GWT.create(LoginService.class);
    private final ClientResources resources = (ClientResources) GWT.create(ClientResources.class);

    final static public long LOGIN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // increase
                                                                         // later
    final static LoginDS loginDS = LoginDS.getInstance();

    private LoginDialogCallback callback;

    private DynamicForm signupForm = new DynamicForm();
    private TextItem idField;
    private TextItem usernameField = new TextItem(Constants.USER_USERNAME);
    private TextItem passwordField = new TextItem(Constants.USER_PASSWORD);
    private TextItem otherPasswordField = new TextItem(Constants.USER_OTHER_PASSWORD);
    private TextItem firstnameField = new TextItem(Constants.USER_FIRST_NAME);
    private TextItem lastnameField = new TextItem(Constants.USER_LAST_NAME);
    private TextItem emailField = new TextItem(Constants.USER_EMAIL);
    private TextItem securityQuestion1Field = new TextItem(Constants.USER_SECURITY_QUESTION_1);
    private TextItem securityAnswer1Field = new TextItem(Constants.USER_SECURITY_ANSWER_1);
    private TextItem securityQuestion2Field = new TextItem(Constants.USER_SECURITY_QUESTION_1);
    private TextItem securityAnswer2Field = new TextItem(Constants.USER_SECURITY_ANSWER_1);
    private TextItem positionField = new TextItem(Constants.USER_POSITION_ID);
    private DateItem birthdateField = new DateItem(Constants.USER_BIRTHDATE);

    private DynamicForm loginForm;
    private TextItem loginUserField;
    private PasswordItem loginPasswordField;

    static public LoginDialog getInstance()
    {
        if (instance == null)
        {
            instance = new LoginDialog();
        }
        return instance;
    }

    private LoginDialog()
    {
        super();
        callback = null;
        setIsModal(true);
        setShowModalMask(true);
        setShowTitle(false);
        setWidth(1000);
        setHeight(630);
        centerInPage();
        setAutoSize(true);
        setCanDragReposition(false);
        setShowMinimizeButton(false);
        setShowCloseButton(false);
        setBorder("1px solid black");

        addMember(getHeader());
        addMember(getWorkArea());
        addMember(getFooter());
    }

    private HLayout getWorkArea()
    {

        HLayout workArea = new HLayout();
        workArea.setPadding(Constants.UI_PADDING_2);
        workArea.setHeight("80%");
        workArea.setWidth100();
        workArea.setBorder("1px solid orange");

        VLayout login = new VLayout();
        login.setPadding(Constants.UI_PADDING_2);
        login.setHeight100();
        login.setWidth("50%");
        // login.setBorder("1px solid yellow");

        // *************Login Form**************
        loginForm = new DynamicForm();
        // loginForm.setBorder("1px solid black");
        loginForm.setPadding(Constants.UI_PADDING_2);
        loginForm.setAutoHeight();
        loginForm.setDataSource(loginDS);
        loginForm.setNumCols(3);
        loginForm.setAutoFocus(true);
        loginForm.setTitleWidth(200);
        loginForm.setWidth(400);
        loginForm.setAlign(Alignment.CENTER);
        loginForm.setSaveOnEnter(true);
        loginForm.setLayoutAlign(VerticalAlignment.CENTER);

        loginUserField = new TextItem("username");
        loginUserField.setIconVAlign(VerticalAlignment.CENTER);
        loginUserField.setTabIndex(0);
        loginUserField.setRequired(true);
        loginUserField.setSelectOnFocus(true);
        loginUserField.setWidth(150);
        loginUserField.setTitle("PhoneBook Id");

        loginPasswordField = new PasswordItem("password");
        // loginPasswordField.setIconVAlign(VerticalAlignment.CENTER);
        loginPasswordField.setTabIndex(1);
        loginPasswordField.setRequired(true);
        loginPasswordField.setWidth(150);
        loginPasswordField.setTitle("Password");
        // loginPasswordField.setDefaultValue("Password");

        SubmitItem submitButton = new SubmitItem();
        submitButton.setTitle(resources.login_submitButtonText());
        submitButton.setStartRow(false);
        submitButton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler()
        {
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event)
            {
                loginForm.submit();

            }
        });

        loginForm.addSubmitValuesHandler(new SubmitValuesHandler()
        {

            public void onSubmitValues(SubmitValuesEvent event)
            {
                String username = loginUserField.getValueAsString();
                String password = loginPasswordField.getValueAsString();

                loginService.login(username, password, new AsyncCallback<UserDTO>()
                {

                    @Override
                    public void onFailure(Throwable arg0)
                    {
                        SC.say("Unable to authenticate username/password!");
                    }

                    @Override
                    public void onSuccess(UserDTO userDTO)
                    {
                        // SC.say("onSuccess: " + userDTO.toString());

                        if (userDTO == null)
                        {
                            SC.say("Invalid username/password combination");
                            loginUserField.focusInItem();
                        }
                        else
                        {
                            String username = userDTO.getUsername();
                            String phoneBookId = Long.toString(userDTO.getId());
                            String userType = userDTO.getPosition().getCode();
                            String validPhoneBookId = Long.toString(userDTO.getId());
                            long expiresUTC = new Date().getTime() + LOGIN_EXPIRATION;
                            Date expiresDate = new Date(expiresUTC);

                            if (username != null)
                            {
                                Cookies.setCookie(Constants.COOKIES_USERNAME, username, expiresDate);
                            }
                            if (phoneBookId != null)
                            {
                                Cookies.setCookie(Constants.COOKIES_PHONEBOOK_ID, phoneBookId, expiresDate);
                            }
                            if (validPhoneBookId != null)
                            {
                                Cookies.setCookie(Constants.COOKIES_VALID_PHONEBOOK_ID, validPhoneBookId, expiresDate);
                            }
                            if (userType != null)
                            {
                                Cookies.setCookie(Constants.COOKIES_USER_TYPE, userType, expiresDate);
                            }
                            hide();
                            if (callback != null)
                            {
                                callback.execute();
                            }
                            else
                            {
                                if (userType != null)
                                {
                                    Cookies.setCookie(Constants.COOKIES_USER_TYPE, userType, expiresDate);
                                    if (userType.equalsIgnoreCase(Constants.USER))
                                    {
                                        PhoneBookMain phonebookMain = new PhoneBookMain(eventBus, userDTO);
                                        phonebookMain.draw();
                                    }
                                    else if (userType.equalsIgnoreCase(Constants.ADMIN))
                                    {
                                        PhoneBookAdmin PhoneBookAdmin = new PhoneBookAdmin(eventBus, userDTO);
                                        PhoneBookAdmin.draw();
                                    }
                                }
                                else
                                {
                                    // TODO How do we process error?
                                }
                            }
                        }
                    }
                });
            }
        });

        loginForm.setFields(loginUserField, loginPasswordField, submitButton);
        loginForm.setAutoFocus(true);

        VLayout messageLayout = getMessageLayout();

        final VLayout xx = getSignupLayout();
        xx.setBorder("1px solid black");

        IButton enrolButton;
        enrolButton = new IButton("Sign Me Up!");
        enrolButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                // hide();
                // EnrolmentDialog.getInstance().display();
                xx.setVisible(true);
            }
        });

        enrolButton.setHeight(21);
        enrolButton.setStyleName("messagebutton");
        enrolButton.setValign(VerticalAlignment.CENTER);

        VLayout signup = new VLayout();
        signup.setPadding(Constants.UI_PADDING_2);
        signup.setHeight100();
        signup.setWidth("50%");
        // signup.setBorder("1px solid yellow");

        signup.addMember(xx);

        login.addMember(loginForm);
        login.addMember(messageLayout);
        login.addMember(enrolButton);

        workArea.addMember(login);
        workArea.addMember(signup);

        return workArea;
    }

    private HLayout getFooter()
    {
        HLayout footer = new HLayout();
        footer.setHeight("5%");
        footer.setWidth100();
        // footer.setBorder("1px solid black");
        footer.setBackgroundColor("blue");

        return footer;
    }

    private HLayout getHeader()
    {
        HLayout topHeader = new HLayout();
        topHeader.setPadding(Constants.UI_PADDING_2);
        topHeader.setHeight("15%");
        topHeader.setWidth100();
        // topHeader.setBorder("1px solid green");
        topHeader.setBackgroundColor("blue");

        Label header = new Label("header");
        header.setPadding(Constants.UI_PADDING_2);
        header.setTitle("my header");
        header.setAutoHeight();
        header.setAutoWidth();
        header.setAlign(Alignment.CENTER);
        header.setValign(VerticalAlignment.CENTER);
        header.setBorder("1px solid white");

        topHeader.addMember(header);

        return topHeader;
    }

    private VLayout getSignupLayout()
    {
        VLayout signupLayout = new VLayout();
        // signupLayout.setBorder("5px solid red");

        // signupForm.setBorder("1px solid orange");
        signupForm.setDataSource(userDS);
        signupForm.setTitleWidth(200);
        signupForm.setWidth(400);

        idField = new TextItem(Constants.USER_ID);
        idField.setIconVAlign(VerticalAlignment.CENTER);
        idField.setTabIndex(0);
        idField.setRequired(true);
        idField.setSelectOnFocus(true);
        idField.setDefaultValue(0);
        idField.setVisible(false);

        usernameField.setTitle(Constants.TITLE_USER_USERNAME);
        usernameField.setIconVAlign(VerticalAlignment.CENTER);
        usernameField.setRequired(true);

        passwordField.setTitle(Constants.TITLE_USER_PASSWORD);
        passwordField.setIconVAlign(VerticalAlignment.CENTER);
        passwordField.setRequired(true);

        otherPasswordField.setTitle(Constants.TITLE_USER_OTHER_PASSWORD);
        otherPasswordField.setIconVAlign(VerticalAlignment.CENTER);
        otherPasswordField.setRequired(true);

        firstnameField.setTitle(Constants.TITLE_USER_FIRST_NAME);
        firstnameField.setIconVAlign(VerticalAlignment.CENTER);
        firstnameField.setRequired(true);

        lastnameField.setTitle(Constants.TITLE_USER_LAST_NAME);
        lastnameField.setIconVAlign(VerticalAlignment.CENTER);
        lastnameField.setRequired(true);

        emailField.setTitle(Constants.TITLE_USER_EMAIL);
        emailField.setIconVAlign(VerticalAlignment.CENTER);
        emailField.setRequired(true);

        securityQuestion1Field.setTitle(Constants.TITLE_USER_SECURITY_QUESTION_1);
        securityQuestion1Field.setIconVAlign(VerticalAlignment.CENTER);
        securityQuestion1Field.setRequired(true);

        securityAnswer1Field.setTitle(Constants.TITLE_USER_SECURITY_ANSWER_1);
        securityAnswer1Field.setIconVAlign(VerticalAlignment.CENTER);
        securityAnswer1Field.setRequired(true);

        securityQuestion2Field.setTitle(Constants.TITLE_USER_SECURITY_QUESTION_2);
        securityQuestion2Field.setIconVAlign(VerticalAlignment.CENTER);
        securityQuestion2Field.setRequired(true);

        securityAnswer2Field.setTitle(Constants.TITLE_USER_SECURITY_ANSWER_2);
        securityAnswer2Field.setIconVAlign(VerticalAlignment.CENTER);
        securityAnswer2Field.setRequired(true);

        positionField.setTitle(Constants.TITLE_USER_POSITION_ID);
        positionField.setDefaultValue(2);
        positionField.setVisible(false);

        birthdateField.setTitle(Constants.TITLE_USER_BIRTHDATE);
        birthdateField.setVisible(true);
        birthdateField.setRequired(true);

        HLayout spacerLayout = new HLayout();
        // spacerLayout.setBorder("5px solid black");
        Label spacerLabel = new Label();
        spacerLabel.setContents("&nbsp;");
        spacerLayout.addMember(spacerLabel);

        HLayout buttonLayout = new HLayout();
        // buttonLayout.setBorder("5px solid red");
        buttonLayout.setAlign(Alignment.CENTER);
        buttonLayout.setLayoutAlign(VerticalAlignment.CENTER);

        IButton saveBtn = new IButton("Save");
        saveBtn.setAlign(Alignment.CENTER);
        saveBtn.setValign(VerticalAlignment.CENTER);
        saveBtn.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                String signupValidationMessage = getSignupValidation(signupForm);
                if (signupValidationMessage == null || "".equals(signupValidationMessage))
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
                    record.setAttribute(Constants.USER_SECURITY_QUESTION_1, securityQuestion1Field.getValueAsString());
                    record.setAttribute(Constants.USER_SECURITY_QUESTION_2, securityQuestion2Field.getValueAsString());
                    record.setAttribute(Constants.USER_SECURITY_ANSWER_1, securityAnswer1Field.getValueAsString());
                    record.setAttribute(Constants.USER_SECURITY_ANSWER_2, securityAnswer2Field.getValueAsString());
                    record.setAttribute(Constants.USER_BIRTHDATE, birthdateField.getValueAsDate());
                    userDS.addData(record);
                }
                else
                {
                    SC.say("Signup", signupValidationMessage);
                }
            }
        });

        IButton resetBtn = new IButton("Reset");
        resetBtn.setAlign(Alignment.CENTER);
        resetBtn.setValign(VerticalAlignment.CENTER);
        resetBtn.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                signupForm.clearValues();
            }
        });

        buttonLayout.addMember(saveBtn);
        buttonLayout.addMember(spacerLabel);
        buttonLayout.addMember(resetBtn);

        signupForm.setFields(idField, usernameField, passwordField, otherPasswordField, firstnameField, lastnameField,
            emailField, positionField, securityQuestion1Field, securityAnswer1Field, securityQuestion2Field,
            securityAnswer2Field, birthdateField);

        signupLayout.addMember(getSignupMessage());
        signupLayout.addMember(signupForm);
        signupLayout.addMember(spacerLayout);
        signupLayout.addMember(buttonLayout);
        signupLayout.setVisible(false);

        return signupLayout;
    }

    private String getSignupValidation(DynamicForm signupForm)
    {
        String signupValidationMessage = null;
        StringBuffer sb = new StringBuffer();
        if (usernameField.getValue() == null)
        {
            sb.append("Username cannot be left blank!<br/>");
        }
        if (passwordField.getValue() == null)
        {
            sb.append("Password cannot be left blank!<br/>");
        }
        if (otherPasswordField.getValue() == null)
        {
            sb.append("Re-type Password cannot be left blank!<br/>");
        }
        if (firstnameField.getValue() == null)
        {
            sb.append("User First Name cannot be left blank!<br/>");
        }
        if (lastnameField.getValue() == null)
        {
            sb.append("User Last Name cannot be left blank!<br/>");
        }
        if (emailField.getValue() == null)
        {
            sb.append("User Email Address cannot be left blank!<br/>");
        }
        if (securityQuestion1Field.getValue() == null)
        {
            sb.append("Security Question 1 cannot be left blank!<br/>");
        }
        if (securityAnswer1Field.getValue() == null)
        {
            sb.append("Security Answer 1 cannot be left blank!<br/>");
        }
        if (securityQuestion2Field.getValue() == null)
        {
            sb.append("Security Question 2 cannot be left blank!<br/>");
        }
        if (securityAnswer2Field.getValue() == null)
        {
            sb.append("Security Answer 2 cannot be left blank!<br/>");
        }
        if (birthdateField.getValue() == null)
        {
            sb.append("Contact Birth Date cannot be left blank!<br/>");
        }
        signupValidationMessage = sb.toString();
        return signupValidationMessage;
    }

    private Label getSignupMessage()
    {
        Label signupMessageLabel = new Label("signupMessage");
        // signupMessageLabel.setBorder("1px solid green");
        signupMessageLabel.setAlign(Alignment.CENTER);
        signupMessageLabel.setValign(VerticalAlignment.CENTER);
        signupMessageLabel.setBaseStyle("signupHeaderLabel");

        String message = new String();
        message = "<b>Every field is required!<br/>" + "Please enter all fields before submitting!</b>";

        signupMessageLabel.setContents(message);

        return signupMessageLabel;
    }

    private VLayout getMessageLayout()
    {
        VLayout messageLayout = new VLayout();
        messageLayout.setPadding(Constants.UI_PADDING_2);
        // messageLayout.setBorder("1px solid red");

        String contents =
            "Welcome to the Demo Contact Database Application.<br/>"
                + "This application users Spring, Maven, Tomcat, MySQL, and SmartGWT, using GWT-RPC as a way to move code between the client and server.<br/><br/>"
                + "I wrote this application as a start to a CRUD based application; we should be able to create our new contacts;<br/>"
                + "then we should be able to Create a new contact, and multiple web-links, emails, and phone numbers for them.<br/>"
                + "this demonstrates the many-to-one database relationship.<br/><br/>"
                + "There will always be the first contact added, but without links, emails, or phone numbers.<br/>"
                + "The demo name and password is demo/demo.  You can create emails, links, and phone numbers for that user.<br/><br/>"
                + "feel free to signup, and create a username and password, and you'll be to create your own contacts.<br/><br>"
                + "Yes, I know there are alot of other apps in the world to do the same thing.  I personally use Google for my contacts.<br/>"
                + "However, this was a sample code application which could be used as a base for other apps.";

        Label welcomeMsg = new Label("welcome");
        welcomeMsg.setPadding(Constants.UI_PADDING_2);
        welcomeMsg.setContents(contents);
        // welcomeMsg.setBorder("1px solid black");

        messageLayout.addMember(welcomeMsg);

        return messageLayout;
    }

    public void displayIfRequired(final LoginDialogCallback callback)
    {
        // com.google.gwt.user.client.Window.alert("execute");
        String phonebookId = Cookies.getCookie(Constants.COOKIES_PHONEBOOK_ID);
        String username = Cookies.getCookie(Constants.COOKIES_USERNAME);
        String validPhoneBookId = Cookies.getCookie(Constants.COOKIES_VALID_PHONEBOOK_ID);
        final String userType = Cookies.getCookie(Constants.COOKIES_USER_TYPE);

        if (phonebookId != null)
        {
            if (callback != null)
            {
                callback.execute();
            }
            else
            {
                loginService.login(phonebookId, new AsyncCallback<UserDTO>()
                {

                    @Override
                    public void onFailure(Throwable arg0)
                    {
                        SC.say("Unable to get user!");
                    }

                    @Override
                    public void onSuccess(UserDTO userDto)
                    {
                        if (Constants.USER.equalsIgnoreCase(userType))
                        {
                            PhoneBookMain phonebookMain = new PhoneBookMain(eventBus, userDto);
                            phonebookMain.draw();
                        }
                        else if (Constants.ADMIN.equalsIgnoreCase(userType))
                        {
                            PhoneBookAdmin PhoneBookAdmin = new PhoneBookAdmin(eventBus, userDto);
                            PhoneBookAdmin.draw();
                        }
                        else
                        {
                            // TODO How do we process error?
                        }
                    }

                });
            }
            return;

        }
        else
        {
            this.callback = callback;
            show();
        }
    }

}
