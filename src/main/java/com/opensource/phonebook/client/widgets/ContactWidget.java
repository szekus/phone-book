package com.opensource.phonebook.client.widgets;

import java.util.Date;

import com.opensource.phonebook.client.datasource.ContactDS;
import com.opensource.phonebook.client.datasource.ContactEmailDS;
import com.opensource.phonebook.client.datasource.ContactLinkDS;
import com.opensource.phonebook.client.datasource.ContactPhoneDS;
import com.opensource.phonebook.client.datasource.EmailTypesDS;
import com.opensource.phonebook.client.datasource.LinkTypesDS;
import com.opensource.phonebook.client.datasource.PhoneTypesDS;
import com.opensource.phonebook.shared.Constants;
import com.opensource.phonebook.shared.dto.UserDTO;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class ContactWidget extends HLayout
{
    private final static int GRID_PADDING = 10;

    private final static String SECTION_TITLE_C_PHONES = "Phones";
    private final static String SECTION_TITLE_C_EMAILS = "Emails";
    private final static String SECTION_TITLE_C_LINKS = "Links";

    private final ContactDS contactDS = ContactDS.getInstance();

    private final ContactPhoneDS contactPhoneDS = ContactPhoneDS.getInstance();
    private final ContactEmailDS contactEmailDS = ContactEmailDS.getInstance();
    private final ContactLinkDS contactLinkDS = ContactLinkDS.getInstance();

    private final PhoneTypesDS phoneTypesDS = PhoneTypesDS.getInstance();
    private final EmailTypesDS emailTypesDS = EmailTypesDS.getInstance();
    private final LinkTypesDS linkTypesDS = LinkTypesDS.getInstance();

    private final ListGrid phonesListGrid = new ListGrid();
    private final ListGrid emailsListGrid = new ListGrid();
    private final ListGrid linksListGrid = new ListGrid();

    private UserDTO userDto;
    private String userId;
    private String contactId = "0";

    private final DynamicForm contactForm = new DynamicForm();
    private TextItem userIdField;
    private TextItem IdField;
    private TextItem prefixField = new TextItem(Constants.CONTACT_PREFIX);
    private TextItem firstnameField = new TextItem(Constants.CONTACT_FIRST_NAME);
    private TextItem middlenameField = new TextItem(Constants.CONTACT_MIDDLE_NAME);
    private TextItem lastnameField = new TextItem(Constants.CONTACT_LAST_NAME);
    private TextItem suffixField = new TextItem(Constants.CONTACT_SUFFIX);
    private TextItem address1Field = new TextItem(Constants.CONTACT_ADDRESS1);
    private TextItem address2Field = new TextItem(Constants.CONTACT_ADDRESS2);
    private TextItem cityField = new TextItem(Constants.CONTACT_CITY);
    private TextItem stateField = new TextItem(Constants.CONTACT_STATE);
    private TextItem zipField = new TextItem(Constants.CONTACT_ZIP);
    private DateItem birthdateField = new DateItem(Constants.CONTACT_BIRTHDATE);

    private TextItem enteredByField = new TextItem(Constants.CONTACT_ENTERED_BY);
    private DateItem enteredDateField = new DateItem(Constants.CONTACT_ENTERED_DATE);
    private TextItem editedByField = new TextItem(Constants.CONTACT_EDITED_BY);
    private DateItem editedDateField = new DateItem(Constants.CONTACT_EDITED_DATE);

    private ListGrid contactListGrid = new ListGrid();

    public ContactWidget(UserDTO userDto)
    {
        super();
        this.userDto = userDto;

        userId = Long.toString(userDto.getId());

        setWidth100();
        setHeight100();
        setBorder("1px solid black");

        addMember(getContactGridFormLayout());
        addMember(getContactGridsLayout());
    }

    private VLayout getContactGridsLayout()
    {
        VLayout contactGridsLayout = new VLayout();
        contactGridsLayout.setWidth100();
        contactGridsLayout.setHeight100();
        contactGridsLayout.setBorder("1px solid pink");

        SectionStack gridSectionStack = new SectionStack();
        gridSectionStack.setWidth100();
        gridSectionStack.setHeight100();
        gridSectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
        gridSectionStack.setOverflow(Overflow.VISIBLE);

        SectionStackSection sectionPhones = new SectionStackSection();
        sectionPhones.setTitle(SECTION_TITLE_C_PHONES);
        sectionPhones.setCanCollapse(true);
        sectionPhones.setExpanded(true);
        sectionPhones.addItem(gridPhones());

        SectionStackSection sectionEmails = new SectionStackSection();
        sectionEmails.setTitle(SECTION_TITLE_C_EMAILS);
        sectionEmails.setCanCollapse(true);
        sectionEmails.setExpanded(true);
        sectionEmails.addItem(gridEmails());

        SectionStackSection sectionLinks = new SectionStackSection();
        sectionLinks.setTitle(SECTION_TITLE_C_LINKS);
        sectionLinks.setCanCollapse(true);
        sectionLinks.setExpanded(true);
        sectionLinks.addItem(gridLinks());

        gridSectionStack.addSection(sectionPhones);
        gridSectionStack.addSection(sectionEmails);
        gridSectionStack.addSection(sectionLinks);

        contactGridsLayout.addMember(gridSectionStack);

        return contactGridsLayout;
    }

    private ListGrid gridPhones()
    {
        phonesListGrid.setWidth100();
        phonesListGrid.setHeight(150);
        phonesListGrid.setInitialCriteria(new Criteria(Constants.C_PHONE_CONTACT_ID, contactId));
        phonesListGrid.setDataSource(contactPhoneDS);
        phonesListGrid.setPadding(GRID_PADDING);

        ListGridField phoneField = new ListGridField(Constants.C_PHONE_NUMBER, Constants.TITLE_C_PHONE_NUMBER);
        phoneField.setCanEdit(true);

        SelectItem phoneTypeList = new SelectItem();
        phoneTypeList.setOptionDataSource(phoneTypesDS);
        phoneTypeList.setValueField(Constants.PHONE_TYPE_ID);
        phoneTypeList.setDisplayField(Constants.PHONE_TYPE_DESCRIPTION);

        ListGridField phoneTypeField = new ListGridField(Constants.C_PHONE_TYPE_ID, Constants.TITLE_C_PHONE_TYPE_ID);
        phoneTypeField.setOptionDataSource(phoneTypesDS);
        phoneTypeField.setEditorType(phoneTypeList);
        phoneTypeField.setValueField(Constants.PHONE_TYPE_ID);
        phoneTypeField.setDisplayField(Constants.PHONE_TYPE_DESCRIPTION);
        phoneTypeField.setAutoFetchDisplayMap(true);
        phoneTypeField.setCanEdit(true);

        ListGridField enteredDateField =
            new ListGridField(Constants.C_PHONE_ENTERED_DATE, Constants.TITLE_C_PHONE_ENTERED_DATE);
        enteredDateField.setCanEdit(false);

        phonesListGrid.setFields(phoneField, phoneTypeField, enteredDateField);
        phonesListGrid.setAutoFetchDisplayMap(true);
        phonesListGrid.setAutoFetchData(true);

        return phonesListGrid;
    }

    private ListGrid gridEmails()
    {
        emailsListGrid.setWidth100();
        emailsListGrid.setHeight(150);
        emailsListGrid.setInitialCriteria(new Criteria(Constants.C_EMAIL_CONTACT_ID, contactId));
        emailsListGrid.setDataSource(contactEmailDS);
        emailsListGrid.setPadding(GRID_PADDING);

        ListGridField emailField = new ListGridField(Constants.C_EMAIL_ADDRESS, Constants.TITLE_C_EMAIL_ADDRESS);
        emailField.setCanEdit(true);

        SelectItem emailTypeList = new SelectItem();
        emailTypeList.setOptionDataSource(emailTypesDS);
        emailTypeList.setValueField(Constants.EMAIL_TYPE_ID);
        emailTypeList.setDisplayField(Constants.EMAIL_TYPE_DESCRIPTION);

        ListGridField emailTypeField = new ListGridField(Constants.C_EMAIL_TYPE_ID, Constants.TITLE_C_EMAIL_TYPE_ID);
        emailTypeField.setOptionDataSource(emailTypesDS);
        emailTypeField.setEditorType(emailTypeList);
        emailTypeField.setValueField(Constants.EMAIL_TYPE_ID);
        emailTypeField.setDisplayField(Constants.EMAIL_TYPE_DESCRIPTION);
        emailTypeField.setAutoFetchDisplayMap(true);
        emailTypeField.setCanEdit(true);

        ListGridField enteredDateField =
            new ListGridField(Constants.C_EMAIL_ENTERED_DATE, Constants.TITLE_C_EMAIL_ENTERED_DATE);
        enteredDateField.setCanEdit(false);

        emailsListGrid.setFields(emailField, emailTypeField, enteredDateField);
        emailsListGrid.setAutoFetchDisplayMap(true);
        emailsListGrid.setAutoFetchData(true);

        return emailsListGrid;
    }

    private ListGrid gridLinks()
    {
        linksListGrid.setWidth100();
        linksListGrid.setHeight(150);
        linksListGrid.setInitialCriteria(new Criteria(Constants.C_LINK_CONTACT_ID, contactId));
        linksListGrid.setDataSource(contactLinkDS);
        linksListGrid.setPadding(GRID_PADDING);

        ListGridField linkField = new ListGridField(Constants.C_LINK_URL, Constants.TITLE_C_LINK_URL);
        linkField.setCanEdit(true);

        SelectItem linkTypeList = new SelectItem();
        linkTypeList.setOptionDataSource(linkTypesDS);
        linkTypeList.setValueField(Constants.LINK_TYPE_ID);
        linkTypeList.setDisplayField(Constants.LINK_TYPE_DESCRIPTION);

        ListGridField linkTypeField = new ListGridField(Constants.C_LINK_TYPE_ID, Constants.TITLE_C_LINK_TYPE_ID);
        linkTypeField.setOptionDataSource(linkTypesDS);
        linkTypeField.setEditorType(linkTypeList);
        linkTypeField.setValueField(Constants.LINK_TYPE_ID);
        linkTypeField.setDisplayField(Constants.LINK_TYPE_DESCRIPTION);
        linkTypeField.setAutoFetchDisplayMap(true);
        linkTypeField.setCanEdit(true);

        ListGridField enteredDateField =
            new ListGridField(Constants.C_LINK_ENTERED_DATE, Constants.TITLE_C_LINK_ENTERED_DATE);
        enteredDateField.setCanEdit(false);

        linksListGrid.setFields(linkField, linkTypeField, enteredDateField);
        linksListGrid.setAutoFetchDisplayMap(true);
        linksListGrid.setAutoFetchData(true);

        return linksListGrid;
    }

    private VLayout getContactGridFormLayout()
    {
        VLayout contactGridFormLayout = new VLayout();
        contactGridFormLayout.setWidth(500);
        contactGridFormLayout.setHeight100();
        contactGridFormLayout.setBorder("1px solid red");

        contactGridFormLayout.addMember(getContactGridLayout());
        contactGridFormLayout.addMember(getContactFormLayout());

        return contactGridFormLayout;
    }

    private VLayout getContactGridLayout()
    {
        VLayout contactGridLayout = new VLayout();
        contactGridLayout.setWidth(500);
        contactGridLayout.setHeight("50%");
        contactGridLayout.setBorder("1px solid blue");

        contactListGrid.setWidth100();
        contactListGrid.setHeight(200);
        contactListGrid.setInitialCriteria(new Criteria(Constants.CONTACT_USER_ID, userId));
        contactListGrid.setDataSource(contactDS);
        contactListGrid.setAutoFetchData(true);
        contactListGrid.setPadding(GRID_PADDING);

        ListGridField contactIdField = new ListGridField(Constants.CONTACT_ID, Constants.TITLE_CONTACT_ID);
        contactIdField.setCanEdit(false);
        contactIdField.setHidden(true);

        ListGridField lastnameField = new ListGridField(Constants.CONTACT_LAST_NAME, Constants.TITLE_CONTACT_LAST_NAME);
        ListGridField firstnameField =
            new ListGridField(Constants.CONTACT_FIRST_NAME, Constants.TITLE_CONTACT_FIRST_NAME);
        ListGridField cityField = new ListGridField(Constants.CONTACT_CITY, Constants.TITLE_CONTACT_CITY);
        ListGridField stateField = new ListGridField(Constants.CONTACT_STATE, Constants.TITLE_CONTACT_STATE);
        ListGridField birthdateField =
            new ListGridField(Constants.CONTACT_BIRTHDATE, Constants.TITLE_CONTACT_BIRTHDATE);

        contactListGrid.setFields(lastnameField, firstnameField, cityField, stateField, birthdateField, contactIdField);

        contactListGrid.addRecordClickHandler(new RecordClickHandler()
        {

            @Override
            public void onRecordClick(RecordClickEvent event)
            {
                contactId = event.getRecord().getAttribute(Constants.CONTACT_ID);

                phonesListGrid.setCriteria(new Criteria(Constants.C_PHONE_CONTACT_ID, contactId));
                phonesListGrid.invalidateCache();

                emailsListGrid.setCriteria(new Criteria(Constants.C_EMAIL_CONTACT_ID, contactId));
                emailsListGrid.invalidateCache();

                linksListGrid.setCriteria(new Criteria(Constants.C_LINK_CONTACT_ID, contactId));
                linksListGrid.invalidateCache();

                contactForm.reset();
                contactForm.editSelectedData(contactListGrid);
            }

        });

        contactGridLayout.addMember(contactListGrid);

        return contactGridLayout;
    }

    private VLayout getContactFormLayout()
    {
        VLayout contactFormLayout = new VLayout();
        contactFormLayout.setWidth(500);
        contactFormLayout.setHeight("50%");
        contactFormLayout.setBorder("1px solid green");

        // contactForm.setBorder("1px solid blue");
        contactForm.setPadding(GRID_PADDING);
        contactForm.setDataSource(contactDS);
        contactForm.setTitleWidth(200);
        contactForm.setWidth(400);

        userIdField = new TextItem(Constants.CONTACT_USER_ID);
        userIdField.setIconVAlign(VerticalAlignment.CENTER);
        userIdField.setTabIndex(0);
        userIdField.setRequired(true);
        userIdField.setSelectOnFocus(true);
        userIdField.setDefaultValue(userId);
        userIdField.setVisible(false);

        IdField = new TextItem(Constants.CONTACT_ID);
        IdField.setIconVAlign(VerticalAlignment.CENTER);
        IdField.setTabIndex(1);
        IdField.setRequired(true);
        IdField.setSelectOnFocus(true);
        IdField.setDefaultValue(0);
        IdField.setVisible(false);

        prefixField.setTitle(Constants.TITLE_CONTACT_PREFIX);
        prefixField.setIconVAlign(VerticalAlignment.CENTER);
        prefixField.setTabIndex(2);
        prefixField.setSelectOnFocus(true);

        firstnameField.setTitle(Constants.TITLE_CONTACT_FIRST_NAME);
        firstnameField.setIconVAlign(VerticalAlignment.CENTER);
        firstnameField.setRequired(true);
        firstnameField.setTabIndex(3);

        middlenameField.setTitle(Constants.TITLE_CONTACT_MIDDLE_NAME);
        middlenameField.setIconVAlign(VerticalAlignment.CENTER);
        middlenameField.setTabIndex(4);

        lastnameField.setTitle(Constants.TITLE_CONTACT_LAST_NAME);
        lastnameField.setIconVAlign(VerticalAlignment.CENTER);
        lastnameField.setRequired(true);
        lastnameField.setTabIndex(5);

        suffixField.setTitle(Constants.TITLE_CONTACT_SUFFIX);
        suffixField.setIconVAlign(VerticalAlignment.CENTER);
        suffixField.setTabIndex(6);

        address1Field.setTitle(Constants.TITLE_CONTACT_ADDRESS1);
        address1Field.setIconVAlign(VerticalAlignment.CENTER);
        address1Field.setRequired(true);
        address1Field.setTabIndex(7);

        address2Field.setTitle(Constants.TITLE_CONTACT_ADDRESS2);
        address2Field.setIconVAlign(VerticalAlignment.CENTER);
        address2Field.setTabIndex(8);

        cityField.setTitle(Constants.TITLE_CONTACT_CITY);
        cityField.setIconVAlign(VerticalAlignment.CENTER);
        cityField.setRequired(true);
        cityField.setTabIndex(9);

        stateField.setTitle(Constants.TITLE_CONTACT_STATE);
        stateField.setIconVAlign(VerticalAlignment.CENTER);
        stateField.setRequired(true);
        stateField.setTabIndex(10);
        stateField.setLength(2);

        zipField.setTitle(Constants.TITLE_CONTACT_ZIP);
        zipField.setIconVAlign(VerticalAlignment.CENTER);
        zipField.setRequired(true);
        zipField.setTabIndex(11);
        zipField.setLength(10);

        birthdateField.setTitle(Constants.TITLE_CONTACT_BIRTHDATE);
        birthdateField.setIconVAlign(VerticalAlignment.CENTER);
        birthdateField.setRequired(true);
        birthdateField.setTabIndex(12);

        enteredByField.setTitle(Constants.TITLE_CONTACT_ENTERED_BY);
        enteredByField.setIconVAlign(VerticalAlignment.CENTER);
        enteredByField.setRequired(true);
        enteredByField.setTabIndex(13);
        enteredByField.setDefaultValue(userId);
        enteredByField.setVisible(false);

        enteredDateField.setTitle(Constants.TITLE_CONTACT_ENTERED_DATE);
        enteredDateField.setIconVAlign(VerticalAlignment.CENTER);
        enteredDateField.setRequired(true);
        enteredDateField.setTabIndex(14);
        enteredDateField.setDefaultValue(new Date());
        enteredDateField.setVisible(false);

        editedByField.setTitle(Constants.TITLE_CONTACT_EDITED_BY);
        editedByField.setIconVAlign(VerticalAlignment.CENTER);
        editedByField.setRequired(true);
        editedByField.setTabIndex(15);
        editedByField.setDefaultValue(userId);
        editedByField.setVisible(false);

        editedDateField.setTitle(Constants.TITLE_CONTACT_EDITED_DATE);
        editedDateField.setIconVAlign(VerticalAlignment.CENTER);
        editedDateField.setRequired(true);
        editedDateField.setTabIndex(16);
        editedDateField.setDefaultValue(new Date());
        editedDateField.setVisible(false);

        HLayout buttonLayout = new HLayout();
        // buttonLayout.setBorder("5px solid red");
        buttonLayout.setAlign(Alignment.CENTER);
        buttonLayout.setLayoutAlign(VerticalAlignment.CENTER);

        HLayout spacerLayout = new HLayout();
        // spacerLayout.setBorder("5px solid black");
        Label spacerLabel = new Label();
        spacerLabel.setContents("&nbsp;");
        spacerLayout.addMember(spacerLabel);

        IButton saveButton = new IButton("Save");
        saveButton.setAlign(Alignment.CENTER);
        saveButton.setValign(VerticalAlignment.CENTER);
        saveButton.addClickHandler(new ClickHandler()
        {

            @Override
            public void onClick(ClickEvent event)
            {
                String contactValidationMessage = getContactValidation(contactForm);
                if (contactValidationMessage == null || "".equals(contactValidationMessage))
                {
                    long contactId = Long.parseLong(IdField.getValueAsString());
                    long userId = Long.parseLong(userIdField.getValueAsString());

                    Record record = new ListGridRecord();
                    record.setAttribute(Constants.CONTACT_ID, contactId);
                    record.setAttribute(Constants.CONTACT_USER_ID, userId);

                    record.setAttribute(Constants.CONTACT_PREFIX, prefixField.getValueAsString());
                    record.setAttribute(Constants.CONTACT_FIRST_NAME, firstnameField.getValueAsString());
                    record.setAttribute(Constants.CONTACT_MIDDLE_NAME, middlenameField.getValueAsString());
                    record.setAttribute(Constants.CONTACT_LAST_NAME, lastnameField.getValueAsString());
                    record.setAttribute(Constants.CONTACT_SUFFIX, suffixField.getValueAsString());

                    record.setAttribute(Constants.CONTACT_ADDRESS1, address1Field.getValueAsString());
                    record.setAttribute(Constants.CONTACT_ADDRESS2, address2Field.getValueAsString());
                    record.setAttribute(Constants.CONTACT_CITY, cityField.getValueAsString());
                    record.setAttribute(Constants.CONTACT_STATE, stateField.getValueAsString());
                    record.setAttribute(Constants.CONTACT_ZIP, zipField.getValueAsString());

                    record.setAttribute(Constants.CONTACT_ENTERED_BY, userId);
                    record.setAttribute(Constants.CONTACT_EDITED_BY, userId);
                    Date today = new Date();
                    record.setAttribute(Constants.CONTACT_ENTERED_DATE, today);
                    record.setAttribute(Constants.CONTACT_EDITED_DATE, today);

                    record.setAttribute(Constants.CONTACT_BIRTHDATE, birthdateField.getValueAsDate());
                    record.setAttribute(Constants.CONTACT_ADMIN, false);
                    record.setAttribute(Constants.CONTACT_COMPANY_ID, 0);

                    if (contactId == 0)
                    {
                        contactDS.addData(record);
                    }
                    else
                    {
                        contactDS.updateData(record);
                    }
                }
                else
                {
                    SC.say("Update Contact", contactValidationMessage);
                }
            }

        });

        contactForm.setFields(IdField, userIdField, prefixField, firstnameField, middlenameField, lastnameField,
            suffixField, address1Field, address2Field, cityField, stateField, zipField, birthdateField, enteredByField,
            enteredDateField, editedByField, editedDateField);

        IButton addnewButton = new IButton("New");
        addnewButton.setAlign(Alignment.CENTER);
        addnewButton.setValign(VerticalAlignment.CENTER);
        addnewButton.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                contactForm.editNewRecord();
            }
        });

        IButton deleteButton = new IButton("Delete");
        deleteButton.setAlign(Alignment.CENTER);
        deleteButton.setValign(VerticalAlignment.CENTER);
        deleteButton.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                long contactId = Long.parseLong(IdField.getValueAsString());
                long userId = Long.parseLong(userIdField.getValueAsString());

                Record record = new ListGridRecord();
                record.setAttribute(Constants.CONTACT_ID, contactId);
                record.setAttribute(Constants.CONTACT_USER_ID, userId);

                if (contactId != 0)
                {
                    contactDS.removeData(record);
                }

            }
        });

        buttonLayout.addMember(addnewButton);
        buttonLayout.addMember(spacerLayout);
        buttonLayout.addMember(saveButton);
        buttonLayout.addMember(spacerLayout);
        buttonLayout.addMember(deleteButton);

        contactFormLayout.addMember(contactForm);
        contactFormLayout.addMember(buttonLayout);

        return contactFormLayout;
    }

    private String getContactValidation(DynamicForm contactForm)
    {
        String passwordValidationMessage = null;
        StringBuffer sb = new StringBuffer();
        if (firstnameField.getValue() == null)
        {
            sb.append("Contact First Name cannot be left blank!<br/>");
        }
        if (lastnameField.getValue() == null)
        {
            sb.append("Contact Last Name cannot be left blank!<br/>");
        }
        if (address1Field.getValue() == null)
        {
            sb.append("Contact Address cannot be left blank!<br/>");
        }
        if (cityField.getValue() == null)
        {
            sb.append("Contact City cannot be left blank!<br/>");
        }
        if (stateField.getValue() == null)
        {
            sb.append("Contact State cannot be left blank!<br/>");
        }
        if (zipField.getValue() == null)
        {
            sb.append("Contact Zip cannot be left blank!<br/>");
        }
        if (birthdateField.getValue() == null)
        {
            sb.append("Contact Birth Date cannot be left blank!<br/>");
        }
        passwordValidationMessage = sb.toString();
        return passwordValidationMessage;
    }
}
