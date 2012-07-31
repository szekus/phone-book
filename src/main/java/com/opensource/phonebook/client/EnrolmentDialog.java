package com.opensource.phonebook.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.opensource.phonebook.client.datasource.EnrolmentDS;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ImageStyle;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.MatchesFieldValidator;
import com.smartgwt.client.widgets.layout.HStack;
import com.smartgwt.client.widgets.layout.VStack;

public class EnrolmentDialog extends Window {
	final static public long LOGIN_EXPIRATION = 12 * 60 * 60 * 1000; // increase later
	static private EnrolmentDialog instance = null;
	static public EnrolmentDialog getInstance() {
		if (instance==null) {
			instance = new EnrolmentDialog();
		}
		return instance;
	}
	private EnrolmentDialog() {
		super();

		final ClientResources resources = (ClientResources)GWT.create(ClientResources.class);

		final DynamicForm enrolmentForm;
		//final CheckboxItem anonymous = new CheckboxItem();  
       // final RadioGroupItem enrolmentTypeItem = new RadioGroupItem();
        final TextItem userField;
        final PasswordItem passwordField;
        final PasswordItem passwordAgainField;
        final TextItem emailField;
        final TextItem firstNameField;
        final TextItem lastNameField;
        final DateItem dobField;
        final TextItem questionField;
        final TextItem answerField;

        setIsModal(true);
		setShowModalMask(true);
		setShowTitle(false);
		setWidth(500);
		setHeight(450);
		centerInPage();
		setShowMinimizeButton(false);
		setShowCloseButton(false);

        enrolmentForm = new DynamicForm();
		enrolmentForm.setDataSource(EnrolmentDS.getInstance());
        enrolmentForm.setLayoutAlign(Alignment.LEFT);
        enrolmentForm.setAutoFocus(true);
        enrolmentForm.setTitleWidth(200);
        enrolmentForm.setNumCols(4);

        HeaderItem header = new HeaderItem();  
        header.setDefaultValue(resources.enroll_title());

		/*anonymous.setName("anonymous");  
		anonymous.setRedrawOnChange(true);  
		anonymous.setValue(false);
		anonymous.setColSpan(4);
		anonymous.setDisabled(true);
		
        enrolmentTypeItem.setColSpan(4);
		enrolmentTypeItem.setName("enrolmentType");
		enrolmentTypeItem.setRequired(true);
		enrolmentTypeItem.setTitleOrientation(TitleOrientation.LEFT);
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();  
		valueMap.put(resources.enroll_idTypeConsumer(), resources.enroll_typeConsumer());
		valueMap.put(resources.enroll_idTypeSupplier(), resources.enroll_typeSupplier());
		valueMap.put(resources.enroll_idTypeAffinityGroup(), resources.enroll_typeAffinityGroup());
		enrolmentTypeItem.setValueMap(valueMap); 
		enrolmentTypeItem.setRedrawOnChange(true);  */


        userField = new TextItem("userID");
        userField.setColSpan(4);
        userField.setTabIndex(0);
        userField.setRequired(true);

        MatchesFieldValidator validator = new MatchesFieldValidator();  
        validator.setOtherField("passwordAgain");  
        validator.setErrorMessage("Passwords do not match");        
        
        passwordField = new PasswordItem("password");
        passwordField.setColSpan(4);
        passwordField.setTabIndex(1);
        passwordField.setRequired(true);
        passwordField.setValidators(validator);
        
        passwordAgainField = new PasswordItem("passwordAgain");
        passwordAgainField.setColSpan(4);
        passwordAgainField.setTabIndex(2);
        passwordAgainField.setRequired(true);

        emailField = new TextItem("email");
        emailField.setColSpan(4);
        emailField.setTabIndex(3);
        emailField.setRequired(false);

        lastNameField = new TextItem("lastName");
        lastNameField.setColSpan(4);
        lastNameField.setTabIndex(3);
        lastNameField.setRequired(false);

        firstNameField = new TextItem("firstName");
        firstNameField.setColSpan(4);
        firstNameField.setTabIndex(3);
        firstNameField.setRequired(false);

        dobField = new DateItem();
        dobField.setName("dob");
        dobField.setColSpan(4);
        dobField.setTabIndex(3);
        dobField.setRequired(false);
        dobField.setEndDate(new Date());
        dobField.setStartDate(new Date(new Date().getTime()-4730400000000L));

        questionField = new TextItem("question");
        questionField.setColSpan(4);
        questionField.setTabIndex(3);
        questionField.setRequired(false);

        answerField = new TextItem("answer");
        answerField.setColSpan(4);
        answerField.setTabIndex(3);
        answerField.setRequired(false);

        
        
        final VStack continueStack;
        continueStack = new VStack(20);
        continueStack.setAlign(VerticalAlignment.CENTER);
        
        Label continueMessage;
        continueMessage = new Label(resources.enroll_success());
        continueMessage.setStyleName("defaultLabel");
        continueMessage.setAlign(Alignment.CENTER);
        IButton continueButton;
        continueButton = new IButton(resources.enroll_okButtonText());
        continueButton.setAlign(Alignment.CENTER);
        continueButton.setLayoutAlign(Alignment.CENTER);
        
		String continueLogoImage = "myOfferSafe_logo.jpg";  
		Img continueImgLogo = new Img(continueLogoImage, 250, 60);  
		continueImgLogo.setImageType(ImageStyle.STRETCH);
		continueImgLogo.setLayoutAlign(Alignment.CENTER);
        continueStack.addMember(continueImgLogo);
        continueStack.addMember(continueMessage);
        continueStack.addMember(continueButton);
        continueStack.setVisible(false);
        continueButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				hide();
				LoginDialog.getInstance().displayIfRequired(null);
			}
        });
        
        final HStack actionStack = new HStack(10);
        actionStack.setMargin(20);
        IButton submitItem;
        submitItem = new IButton(resources.enroll_submitButtonText());
        submitItem.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				if (enrolmentForm.validate()) {
					enrolmentForm.saveData(new DSCallback() {
						public void execute(DSResponse response, Object rawData, DSRequest request) {
							if (response.getStatus()==RPCResponse.STATUS_SUCCESS) {
								String userID = response.getAttribute("userID");
								String offerSafeID = response.getAttribute("offerSafeID");
								String validOfferSafeID = response.getAttribute("validOfferSafeID");
								long expiresUTC = new Date().getTime()+LOGIN_EXPIRATION;
								Date expiresDate = new Date(expiresUTC);
								if (userID!=null) {
									Cookies.setCookie("userID", userID, expiresDate);
								}
								if (offerSafeID != null) {
									Cookies.setCookie("offerSafeID", offerSafeID, expiresDate);
								}
								if (validOfferSafeID != null) {
									Cookies.setCookie("validOfferSafeID", validOfferSafeID, expiresDate);
								}
								enrolmentForm.hide();
								actionStack.hide();
								continueStack.show();
							}
						}			
					});
					
				}
			}
        });

        IButton cancelItem;
        cancelItem = new IButton(resources.enroll_cancelButtonText());
        cancelItem.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				hide();
				LoginDialog.getInstance().displayIfRequired(null);
			}
        });
        actionStack.addMember(submitItem);
        actionStack.addMember(cancelItem);

       // enrolmentForm.setFields(header, enrolmentTypeItem, anonymous, userField, passwordField, passwordAgainField, emailField, firstNameField, lastNameField, dobField, questionField, answerField);
        //enrolmentForm.setFields(header, userField, passwordField, passwordAgainField, emailField, firstNameField, lastNameField, dobField, questionField, answerField);
        
		String enrollLogoImage = "myOfferSafe_logo.jpg";  
		Img imgLogo = new Img(enrollLogoImage, 250, 60);  
		imgLogo.setImageType(ImageStyle.STRETCH);
        HStack logoStack = new HStack();
        logoStack.addMember(imgLogo);
        CanvasItem logoItem = new CanvasItem();
        logoItem.setTitle("");
        logoItem.setCanvas(logoStack);        
        
        enrolmentForm.setFields(logoItem, header, userField, passwordField, passwordAgainField);
        enrolmentForm.setMargin(20);

		/*ChangedHandler showHideAnonymousCheckbox = new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				if (resources.enroll_idTypeConsumer().equals((String)(enrolmentTypeItem.getValue()))) {
					anonymous.enable();
				}
				else {
					anonymous.disable();
				}
			}			
		};*/
    /*    ChangedHandler showHideFields = new ChangedHandler() {
			public void onChanged(ChangedEvent event) {
				if (resources.enroll_idTypeConsumer().equals((String)(enrolmentTypeItem.getValue()))&&((Boolean)(anonymous.getValue()))) {
					userField.disable();
					userField.setRequired(false);
					passwordField.disable();
					passwordField.setRequired(false);
					passwordAgainField.disable();
					passwordAgainField.setRequired(false);
					emailField.disable();
					emailField.setRequired(false);
					lastNameField.disable();
					firstNameField.disable();
					dobField.disable();
					questionField.disable();
					answerField.disable();
				}
				else {
					userField.enable();
					userField.setRequired(true);
					passwordField.enable();
					passwordField.setRequired(true);
					passwordAgainField.enable();					
					passwordAgainField.setRequired(true);
					emailField.enable();					
					lastNameField.enable();					
					firstNameField.enable();					
					dobField.enable();					
					questionField.enable();					
					answerField.enable();					
				}
			}
        };*/

		/*anonymous.addChangedHandler(showHideFields);
		enrolmentTypeItem.addChangedHandler(showHideAnonymousCheckbox);
		enrolmentTypeItem.addChangedHandler(showHideFields);
*/
        addItem(enrolmentForm);
        addItem(continueStack);
        addItem(actionStack);
	}
	public void display() {
		show();
	}
}
