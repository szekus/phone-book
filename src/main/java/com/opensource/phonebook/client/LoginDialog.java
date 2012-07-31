package com.opensource.phonebook.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.opensource.phonebook.client.datasource.LoginDS_OLD;
import com.opensource.phonebook.shared.Constants;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ImageStyle;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.events.SubmitValuesEvent;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VStack;

public class LoginDialog extends Window {
	
	static private LoginDialog instance = null;
	
	static public LoginDialog getInstance() {
		if (instance==null) {
			instance = new LoginDialog();
		}
		return instance;
	}
	
	final static public long LOGIN_EXPIRATION = 7*24*60*60*1000; // increase later
	
	private LoginDialogCallback callback;
	
	private LoginDialog() {
		super();
		callback = null;
		ClientResources resources = (ClientResources)GWT.create(ClientResources.class);
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
		
		VStack layout = new VStack();//main layout
		layout.setWidth("100%");
		layout.setHeight("100%");
       
		/*Label header = new Label(resources.login_title()); 
        header.setHeight("20%");
        header.setWidth("100%");
        header.setAlign(Alignment.CENTER);
        header.setStyleName("loginHeader");
        layout.addMember(header);*/
		
		/*LayoutSpacer space10 = new LayoutSpacer();
        space10.setHeight(10);
        LayoutSpacer space15 = new LayoutSpacer();
        space15.setHeight(15);
        LayoutSpacer space20 = new LayoutSpacer();
        space20.setHeight(20);
        
		VStack vspace80 = new VStack();
        vspace80.setWidth(80);        
        VStack vspace35 = new VStack();
        vspace35.setWidth(35);
        VStack vspace30 = new VStack();
        vspace30.setWidth(30);*/
   
		//*************Start Header**************
		HStack header = new HStack();
		header.setHeight(100);
		
		HStack headerLeft = new HStack();
		headerLeft.setWidth(400);
		headerLeft.setHeight("100%");
		header.addMember(headerLeft);
		
		VStack headerRight = new VStack();
		headerRight.setHeight("100%");
		headerRight.setAlign(Alignment.LEFT);
		
		header.addMember(headerRight);
		header.setStyleName("loginheader");

		//*************Logo**************
		String logoImage = "logo.png";  
		Img imgLogo = new Img(logoImage, 200, 58);  
		imgLogo.setImageType(ImageStyle.STRETCH);
		imgLogo.setLayoutAlign(VerticalAlignment.TOP);
		
		headerLeft.addMember(imgLogo);

		//*************Login Form**************
		final DynamicForm loginForm;
        loginForm = new DynamicForm();
        loginForm.setHeight("100%");
		loginForm.setDataSource(LoginDS_OLD.getInstance());
		loginForm.setNumCols(3);
        loginForm.setAutoFocus(true);
        loginForm.setTitleWidth(200);
        loginForm.setWidth(400);
        loginForm.setAlign(Alignment.CENTER);
        loginForm.setSaveOnEnter(true);
        loginForm.setLayoutAlign(VerticalAlignment.CENTER);

        final TextItem userField;
        userField = new TextItem("userID");
		userField.setIconVAlign(VerticalAlignment.CENTER);
        userField.setTabIndex(0);
        userField.setRequired(true);
        userField.setSelectOnFocus(true);
        userField.setWidth(150);
        //userField.setDefaultValue("PhoneBook Id");
         
        userField.setTitle("PhoneBook Id");
        userField.setTitleStyle("greenLabel");
        userField.setHintStyle("greenLabel");
       


        final PasswordItem passwordField;
        passwordField = new PasswordItem("password");
		//passwordField.setIconVAlign(VerticalAlignment.CENTER);
        passwordField.setTabIndex(1);
        passwordField.setRequired(true);
        passwordField.setWidth(150);
        passwordField.setTitle("Password");
        passwordField.setTitleStyle("greenLabel");
        //passwordField.setDefaultValue("Password");
   
        
        SubmitItem submitButton = new SubmitItem();
        submitButton.setTitle(resources.login_submitButtonText());
        submitButton.setStartRow(false); 
        submitButton.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				loginForm.submit();
		
			}
     });
      
        
        loginForm.addSubmitValuesHandler(new com.smartgwt.client.widgets.form.events.SubmitValuesHandler(){
        	
        	public void onSubmitValues(SubmitValuesEvent event) {
				if (loginForm.validate()) {
					loginForm.saveData(new DSCallback() {
						
						public void execute(DSResponse response, Object rawData, DSRequest request) {
							//com.google.gwt.user.client.Window.alert("execute");
							if (response.getStatus()==RPCResponse.STATUS_SUCCESS) {
								String userID = response.getAttribute("userID");
								String phonebookID = response.getAttribute("phonebookID");
								String userType = response.getAttribute("userType");
								String validPhoneBookID = response.getAttribute("validPhoneBookID");
								long expiresUTC = new Date().getTime()+LOGIN_EXPIRATION;
								Date expiresDate = new Date(expiresUTC);
								if (userID!=null)
								{
									Cookies.setCookie("userID", userID, expiresDate);
								}
								if (phonebookID!=null)
								{
									Cookies.setCookie("phonebookID", phonebookID, expiresDate);
								}
								if (validPhoneBookID!=null)
								{
									Cookies.setCookie("validPhoneBookID", validPhoneBookID, expiresDate);
								}
								if (userType!=null)
								{
									Cookies.setCookie("userType", userType, expiresDate);
								}
								hide();
								if (callback!=null) {
									callback.execute();
								} else {
									if (userType != null)
									{
										Cookies.setCookie("userType", userType, expiresDate);
										if (userType.equals(Constants.USER))
										{
											PhoneBookMain phonebookMain = new PhoneBookMain();
											phonebookMain.draw();
										}
										else if (userType.equals(Constants.ADMIN))
										{
											PhoneBookAdmin phonebookAdmin = new PhoneBookAdmin();
											phonebookAdmin.draw();
											if (!phonebookAdmin.isVisible())
											{
												//phonebookAdmin.reloadAllData();
												//phonebookAdmin.setNavigationGridSelection(0);
												//phonebookAdmin.show();
											}
										}
									}
									else
									{
										//TODO How do we process error?
									}
								}
							}
						}			
					});
					
				}
			}

	    });
        
        loginForm.setFields(userField, passwordField,submitButton);
        loginForm.setAutoFocus(true);
        
        headerRight.addMember(loginForm);
               
        
      //*************Start of Enrol Layout Parts**************
        
      //*************Left Message Stack**************
        VStack leftMessage = new VStack();
        leftMessage.setWidth(520);
        leftMessage.setHeight("100%");
        
        Label test = new Label();
        test.setContents("What is an PhoneBook?");
        test.setStyleName("logintitle1");
        test.setHeight(40);
        Label test2 = new Label("An PhoneBook is a spam-free anonymous service " +   
                "from Act3 Technologies that matches consumers " +
                "specified interests to suppliers online offers " +
                "with the highest redemption rates available.");
        test2.setStyleName("paragraph1");

      //*************Consumers - Left Message Stack**************
        HStack consumerarea = new HStack();
        consumerarea.setHeight(110);
        
        HStack consumerLeft = new HStack();
        consumerLeft.setWidth(100);
        consumerLeft.setHeight("100%");
        
		VStack consumerRight = new VStack();
		consumerRight.setHeight("100%");
		consumerRight.setWidth(420);
		consumerRight.setAlign(Alignment.LEFT);
		
        String logoConsumer = "consumer.png";  
		Img imgconsumer = new Img(logoConsumer, 75, 75);  
		imgconsumer.setImageType(ImageStyle.STRETCH);
		imgconsumer.setLayoutAlign(VerticalAlignment.TOP);
        
        Label consumer = new Label();
        consumer.setContents("Consumers");
        consumer.setStyleName("logintitle2");
        consumer.setHeight(25);
        
        Label consumerDesc = new Label("Anonymously specify their online product interests. " +
                " Offersafe will match these interests to special " +
                " supplier offers.   " );     
        consumerDesc.setStyleName("paragraph2");
        consumerDesc.setHeight(40);
        
        consumerLeft.addMember(imgconsumer);
        consumerRight.addMember(consumer);
        consumerRight.addMember(consumerDesc);
        
        consumerarea.addMember(consumerLeft);
        consumerarea.addMember(consumerRight);

      //*************Suppliers - Left Message Stack**************
        HStack suppliersarea = new HStack();
        suppliersarea.setHeight(110);
        
        HStack suppliersLeft = new HStack();
        suppliersLeft.setWidth(100);
        suppliersLeft.setHeight("100%");
        
		VStack suppliersRight = new VStack();
		suppliersRight.setHeight("100%");
		suppliersRight.setWidth(420);
		suppliersRight.setAlign(Alignment.LEFT);
		
        String logosuppliers = "suppliers.png";  
		Img imgsuppliers = new Img(logosuppliers, 75, 75);  
		imgsuppliers.setImageType(ImageStyle.STRETCH);
		imgsuppliers.setLayoutAlign(VerticalAlignment.TOP);
		
        Label supplier = new Label();
        supplier.setContents("Suppliers");
        supplier.setStyleName("logintitle2");
        supplier.setHeight(25);
        
        Label supplierDesc = new Label("Provision feature rich offers targeted at these interested " +
                "consumers. ");    
        supplierDesc.setStyleName("paragraph2");
        supplierDesc.setHeight(40);
        
        suppliersLeft.addMember(imgsuppliers);
        suppliersRight.addMember(supplier);
        suppliersRight.addMember(supplierDesc);
        
        suppliersarea.addMember(suppliersLeft);
        suppliersarea.addMember(suppliersRight);
        
      //*************Right Message Stack**************
        VStack rightMessage = new VStack();
        rightMessage.setWidth("35%"); 
        rightMessage.setHeight("100%");
        rightMessage.setStyleName("rightmessage");

      //*************Sign up - Right Message Stack**************
        Label signup = new Label();
        signup.setContents("Sign Up");
        signup.setStyleName("logintitle2");
        signup.setHeight(30);

        Label signupContents = new Label("If you are not already a registered user " +   
                "of PhoneBook signing up is free and easy. " +
                "Just click on the link below to start using " +
                "PhoneBook today!" );
        signupContents.setStyleName("paragraph3");
        
       IButton enrolButton;
        enrolButton = new IButton("Sign Me Up!");
        enrolButton.addClickHandler(new ClickHandler() {
 			public void onClick(ClickEvent event) {
 				hide();
				EnrolmentDialog.getInstance().display();
 		
 			}
        });
        
        enrolButton.setHeight(21);
        enrolButton.setStyleName("messagebutton");
        enrolButton.setValign(VerticalAlignment.CENTER);
        
      //*************Tour - Right Message Stack**************
        Label tour = new Label();
        tour.setContents("Watch a quick tour");
        tour.setStyleName("logintitle2");
        tour.setHeight(30);

        Label tourContents = new Label( "If you want to see PhoneBook in action " +   
                "for yourself to see how easy it is, use " +
                " the link below to watch a quick video tour. " +
                "PhoneBook today!" );
        tourContents.setStyleName("paragraph3");
        
        
        IButton tourButton;
        tourButton = new IButton("Watch Tour!");
        tourButton.addClickHandler(new ClickHandler() {
 			public void onClick(ClickEvent event) {
 		      SC.say("Under Construction!");
 		
 			}
        });
        
        
        tourButton.setHeight(21);
        tourButton.setStyleName("messagebutton");
        tourButton.setValign(VerticalAlignment.CENTER);
        
        LayoutSpacer space25 = new LayoutSpacer();
        space25.setHeight(25);
        LayoutSpacer space35 = new LayoutSpacer();
        space35.setHeight(35);
        
      //*************Assemble Left Message**************
        leftMessage.addMember(test);
        leftMessage.addMember(test2);
        leftMessage.addMember(space25);
        leftMessage.addMember(consumerarea);
        leftMessage.addMember(suppliersarea);
        
      //*************Assemble Right Message**************
        rightMessage.addMember(signup);
        rightMessage.addMember(signupContents);
        rightMessage.addMember(enrolButton);
        rightMessage.addMember(space35);
        rightMessage.addMember(tour);
        rightMessage.addMember(tourContents);
        rightMessage.addMember(tourButton);
               
      //*************Assemble Enrol Layout**************
        HStack enrolLayout = new HStack();
        enrolLayout.setHeight(400);
        enrolLayout.setStyleName("loginmainbak");
        enrolLayout.addMember(leftMessage);
        enrolLayout.addMember(rightMessage);

        /*End enrol layout*/
        
        /*Footer*/
      
        LinkItem link1 = new LinkItem("Terms of User");
        link1.setLinkTitle("Terms of User");
        
        link1.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				SC.say("Terms Of User");
				
			}
        });
        link1.setShowTitle(false);
        
        
        LinkItem link2 = new LinkItem("Privacy Policy");
        link2.setLinkTitle("Privacy Policy");
        
        link2.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
          
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				SC.say("Privacy Policy");
				
			}
        });
        link2.setShowTitle(false);
        
        LinkItem link3 = new LinkItem("Copyright Policy");
        link3.setLinkTitle("Copyright Policy");
        
        link3.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
          
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				SC.say("Copyright Policy");
				
			}
        });
        link3.setShowTitle(false);
        
        LinkItem link4 = new LinkItem("About Us");
        link4.setLinkTitle("About Us");
        
        link4.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
          
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				SC.say("About Us");
				
			}
        });
        link4.setShowTitle(false);
        
        LinkItem link5 = new LinkItem("Contact Us");
        link5.setLinkTitle("Contact Us");
       
        link5.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
          
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				SC.say("Contact Us");
				
			}
        });
        link5.setShowTitle(false);



        
        Label actRight = new Label("Act3 Technologies,LLC 2009");
        actRight.setWrap(false);
        actRight.setHeight(10);
        actRight.setStyleName("copyright");
        
        HStack loginarea = new HStack();
        loginarea.setHeight(80);
        loginarea.setWidth("100%");
        
        VStack loginFooter = new VStack();
        loginFooter.setHeight(80);
        
       
        loginarea.setStyleName("loginfooter");
        
        DynamicForm footerForm = new DynamicForm();
        footerForm.setNumCols(5);
        footerForm.setWidth(300);
        footerForm.setFields(link1,link2,link3,link4,link5);
        
        VStack vspace35 = new VStack();
        vspace35.setWidth(35);
        
        LayoutSpacer space20 = new LayoutSpacer();
        space20.setHeight(20);
        
        loginFooter.addMember(space20);
        loginFooter.addMember(footerForm);
        loginFooter.addMember(actRight);
        
        loginarea.addMember (vspace35);
        loginarea.addMember (loginFooter);
        
        layout.addMember(header);
        layout.addMember(enrolLayout);
        layout.addMember(loginarea);

        addItem(layout);
	}
	public void displayIfRequired(LoginDialogCallback callback) {
		//com.google.gwt.user.client.Window.alert("execute");
		String phonebookID = Cookies.getCookie("phonebookID");
		String userID = Cookies.getCookie("userID");
		String validPhoneBookID = Cookies.getCookie("validPhoneBookID");
		if (phonebookID!=null) {
			if (callback!=null) {
				callback.execute();
			}
			else {
				PhoneBookMain phonebookMain = new PhoneBookMain();
				phonebookMain.draw();								
			}
			return;
		}
		else {
			this.callback = callback;
			show();			
		}
	}

}
