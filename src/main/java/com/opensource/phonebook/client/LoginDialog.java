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

    static public LoginDialog getInstance()
    {
        if (instance == null)
        {
            instance = new LoginDialog();
        }
        return instance;
    }

    /**
     * Create a remote service proxy to talk to the server-side Greeting
     * service.
     */
    private final LoginServiceAsync loginService = GWT.create(LoginService.class);

    final static public long LOGIN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // increase
                                                                         // later
    final static LoginDS loginDS = LoginDS.getInstance();
    
    private final int SIGNUP_FORM_TITLE_WIDTH = 200;

    private LoginDialogCallback callback;

    private LoginDialog()
    {
        super();
        callback = null;
        ClientResources resources = (ClientResources) GWT.create(ClientResources.class);
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
        final DynamicForm loginForm;
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

        final TextItem userField;
        userField = new TextItem("username");
        userField.setIconVAlign(VerticalAlignment.CENTER);
        userField.setTabIndex(0);
        userField.setRequired(true);
        userField.setSelectOnFocus(true);
        userField.setWidth(150);
        // userField.setDefaultValue("OfferSafe Id");

        userField.setTitle("PhoneBook Id");

        final PasswordItem passwordField;
        passwordField = new PasswordItem("password");
        // passwordField.setIconVAlign(VerticalAlignment.CENTER);
        passwordField.setTabIndex(1);
        passwordField.setRequired(true);
        passwordField.setWidth(150);
        passwordField.setTitle("Password");
        // passwordField.setDefaultValue("Password");

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
                String username = userField.getValueAsString();
                String password = passwordField.getValueAsString();

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

                        if(userDTO == null)
                        {
                            SC.say("Invalid username/password combination");
                            userField.focusInItem();
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

        loginForm.setFields(userField, passwordField, submitButton);
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

        HLayout footer = new HLayout();
        footer.setHeight("5%");
        footer.setWidth100();
        // footer.setBorder("1px solid black");
        footer.setBackgroundColor("blue");

        addMember(topHeader);
        addMember(workArea);
        addMember(footer);
    }

    private VLayout getSignupLayout()
    {
        VLayout signupLayout = new VLayout();

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
        idField.setDefaultValue(0);
        idField.setVisible(false);

        final TextItem usernameField = new TextItem(Constants.USER_USERNAME);
        usernameField.setTitle(Constants.TITLE_USERNAME);
        usernameField.setIconVAlign(VerticalAlignment.CENTER);
        usernameField.setRequired(true);

        final TextItem passwordField = new TextItem(Constants.USER_PASSWORD);
        passwordField.setTitle(Constants.TITLE_PASSWORD);
        passwordField.setIconVAlign(VerticalAlignment.CENTER);
        passwordField.setRequired(true);

        final TextItem firstnameField = new TextItem(Constants.USER_FIRST_NAME);
        firstnameField.setTitle(Constants.TITLE_FIRSTNAME);
        firstnameField.setIconVAlign(VerticalAlignment.CENTER);
        firstnameField.setRequired(true);

        final TextItem lastnameField = new TextItem(Constants.USER_LAST_NAME);
        lastnameField.setTitle(Constants.TITLE_LASTNAME);
        lastnameField.setIconVAlign(VerticalAlignment.CENTER);
        lastnameField.setRequired(true);

        final TextItem emailField = new TextItem(Constants.USER_EMAIL);
        emailField.setTitle(Constants.TITLE_EMAIL);
        emailField.setIconVAlign(VerticalAlignment.CENTER);
        emailField.setRequired(true);

        final TextItem securityQuestion1 = new TextItem("securityQuestion1");
        securityQuestion1.setTitle(Constants.SECURITY_QUESTION_1);
        securityQuestion1.setIconVAlign(VerticalAlignment.CENTER);
        securityQuestion1.setRequired(true);

        final TextItem securityAnswer1 = new TextItem("securityAnswer1");
        securityAnswer1.setTitle(Constants.SECURITY_ANSWER_1);
        securityAnswer1.setIconVAlign(VerticalAlignment.CENTER);
        securityAnswer1.setRequired(true);

        final TextItem securityQuestion2 = new TextItem("securityQuestion2");
        securityQuestion2.setTitle(Constants.SECURITY_QUESTION_2);
        securityQuestion2.setIconVAlign(VerticalAlignment.CENTER);
        securityQuestion2.setRequired(true);

        final TextItem securityAnswer2 = new TextItem("securityAnswer2");
        securityAnswer2.setTitle(Constants.SECURITY_ANSWER_2);
        securityAnswer2.setIconVAlign(VerticalAlignment.CENTER);
        securityAnswer2.setRequired(true);
        
        final TextItem positionField = new TextItem(Constants.USER_POSITION_ID);
        positionField.setTitle(Constants.TITLE_POSITION);
        positionField.setDefaultValue(2);
        positionField.setVisible(false);

        IButton saveBtn = new IButton("Save");
        saveBtn.addClickHandler(new ClickHandler() {

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
        
        signupForm.setFields(idField, usernameField,passwordField,firstnameField,
            lastnameField,emailField,positionField,securityQuestion1,securityAnswer1,securityQuestion2,securityAnswer2);
                
        signupForm.setBorder("1px solid red");

        signupLayout.addMember(signupForm);
        signupLayout.addMember(saveBtn);
        signupLayout.setVisible(false);

        return signupLayout;
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
