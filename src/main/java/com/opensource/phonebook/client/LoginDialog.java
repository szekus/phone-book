package com.opensource.phonebook.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.opensource.phonebook.client.datasource.LoginDS;
import com.opensource.phonebook.client.services.LoginService;
import com.opensource.phonebook.client.services.LoginServiceAsync;
import com.opensource.phonebook.shared.Constants;
import com.opensource.phonebook.shared.dto.UserDTO;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.JSONEncoder;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.util.StringUtil;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.events.SubmitValuesEvent;
import com.smartgwt.client.widgets.form.events.SubmitValuesHandler;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class LoginDialog extends Window {

	static private LoginDialog instance = null;

	static public LoginDialog getInstance() {
		if (instance == null) {
			instance = new LoginDialog();
		}
		return instance;
	}
	
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final LoginServiceAsync loginService = GWT.create(LoginService.class);

	final static public long LOGIN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // increase later
	
	final static LoginDS loginDS = LoginDS.getInstance();
 
	private LoginDialogCallback callback;

	private LoginDialog() {
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
		//loginForm.setBorder("1px solid black");
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
		submitButton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				loginForm.submit();

			}
		});

		loginForm.addSubmitValuesHandler(new SubmitValuesHandler()
		{
			public void onSubmitValues(SubmitValuesEvent event) 
			{
				String username = userField.getValueAsString();
				String password = passwordField.getValueAsString();
				
				loginService.login(username, password, new AsyncCallback<UserDTO>(){

					@Override
					public void onFailure(Throwable arg0) {
						SC.say("onFailure: " + arg0.toString());
					}

					@Override
					public void onSuccess(UserDTO arg0) {
						SC.say("onSuccess: " + arg0.toString());
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
		enrolButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//hide();
				//EnrolmentDialog.getInstance().display();
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
		//signup.setBorder("1px solid yellow");
		
		
		
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
	
	private VLayout getSignupLayout(){
		VLayout signupLayout = new VLayout();
		
		DynamicForm signupForm = new DynamicForm();
		
		TextItem username = new TextItem("username");
		username.setTitle(Constants.USERNAME);
		
		TextItem password = new TextItem("password");
		password.setTitle(Constants.PASSWORD);
	
		TextItem firstname = new TextItem("firstname");
		firstname.setTitle(Constants.FIRSTNAME);
		
		TextItem lastname = new TextItem("lastname");
		lastname.setTitle(Constants.LASTNAME);
		
		TextItem email = new TextItem("email");
		email.setTitle(Constants.EMAIL);
		
		TextItem securityQuestion1 = new TextItem("securityQuestion1");
		securityQuestion1.setTitle(Constants.SECURITY_QUESTION_1);
		
		TextItem securityAnswer1 = new TextItem("securityAnswer1");
		securityAnswer1.setTitle(Constants.SECURITY_ANSWER_1);
		
		TextItem securityQuestion2 = new TextItem("securityQuestion2");
		securityQuestion2.setTitle(Constants.SECURITY_QUESTION_2);
		
		TextItem securityAnswer2 = new TextItem("securityAnswer2");
		securityAnswer2.setTitle(Constants.SECURITY_ANSWER_2);
				
		  ButtonItem saveBtn = new ButtonItem("Save");  
	        saveBtn.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {  
	            @Override  
	            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {  
	                event.getForm().saveData(new DSCallback() {  
	                    @Override  
	                    public void execute(DSResponse response, Object rawData, DSRequest request) {  
	                        Record rec = response.getData()[0];  
	                        JavaScriptObject rawItems = rec.getAttributeAsJavaScriptObject("items");  
	                        String html = StringUtil.asHTML(new JSONEncoder().encode(rawItems));  
	                        SC.say("Record saved with items:" + html);  
	                    }  
	                });  
	            }  
	        });  
		
		signupForm.setItems(username,password,firstname,lastname,email,securityQuestion1,securityAnswer1,securityQuestion2,securityAnswer1,saveBtn);
		signupForm.setBorder("1px solid red");
		
		signupLayout.addMember(signupForm);
		signupLayout.setVisible(false);
		
		return signupLayout;
	}
	
	private VLayout getMessageLayout(){
		VLayout messageLayout = new VLayout();
		messageLayout.setPadding(Constants.UI_PADDING_2);
		//messageLayout.setBorder("1px solid red");
		
		String contents = "Welcome to the Demo Contact Database Application.<br/>"
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
		//welcomeMsg.setBorder("1px solid black");
		
		messageLayout.addMember(welcomeMsg);
		
		return messageLayout;
	}
	
	public void displayIfRequired(LoginDialogCallback callback) {
		// com.google.gwt.user.client.Window.alert("execute");
		String phonebookID = Cookies.getCookie("phonebookID");
		String userID = Cookies.getCookie("userID");
		String validPhoneBookID = Cookies.getCookie("validPhoneBookID");
		if (phonebookID != null) {
			if (callback != null) {
				callback.execute();
			} else {
				PhoneBookMain phonebookMain = new PhoneBookMain();
				phonebookMain.draw();
			}
			return;
		} else {
			this.callback = callback;
			show();
		}
	}

}
