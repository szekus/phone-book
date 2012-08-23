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
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.IButton;
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

    private final static String SECTION_TITLE_PHONES = "Phones";
    private final static String SECTION_TITLE_EMAILS = "Emails";
    private final static String SECTION_TITLE_LINKS = "Links";

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
        sectionPhones.setTitle(SECTION_TITLE_PHONES);
        sectionPhones.setCanCollapse(true);
        sectionPhones.setExpanded(true);
        sectionPhones.addItem(gridPhones());

        SectionStackSection sectionEmails = new SectionStackSection();
        sectionEmails.setTitle(SECTION_TITLE_EMAILS);
        sectionEmails.setCanCollapse(true);
        sectionEmails.setExpanded(true);
        sectionEmails.addItem(gridEmails());

        SectionStackSection sectionLinks = new SectionStackSection();
        sectionLinks.setTitle(SECTION_TITLE_LINKS);
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
        phonesListGrid.setInitialCriteria(new Criteria(Constants.PHONE_CONTACT_ID, contactId));
        phonesListGrid.setDataSource(contactPhoneDS);
        phonesListGrid.setAutoFetchData(true);
        phonesListGrid.setPadding(GRID_PADDING);

        SelectItem phoneTypeList = new SelectItem();
        phoneTypeList.setOptionDataSource(phoneTypesDS);
        phoneTypeList.setValueField(Constants.ID);
        phoneTypeList.setDisplayField(Constants.DESCRIPTION);

        ListGridField phoneField = new ListGridField(Constants.PHONE_NUMBER, Constants.TITLE_PHONE_NUMBER);
        phoneField.setCanEdit(true);

        ListGridField phoneTypeField = new ListGridField(Constants.PHONE_TYPE_ID, Constants.TITLE_PHONE_TYPE_ID);
        phoneTypeField.setCanEdit(true);
        phoneTypeField.setEditorType(phoneTypeList);
        phoneTypeField.setAutoFetchDisplayMap(true);
        phoneTypeField.setValueField(Constants.ID);
        phoneTypeField.setDisplayField(Constants.DESCRIPTION);

        ListGridField enteredDateField =
            new ListGridField(Constants.PHONE_ENTERED_DATE, Constants.TITLE_PHONE_ENTERED_DATE);
        enteredDateField.setCanEdit(false);

        phonesListGrid.setFields(phoneField, phoneTypeField, enteredDateField);
        phonesListGrid.setAutoFetchDisplayMap(true);

        return phonesListGrid;
    }

    private ListGrid gridEmails()
    {
        emailsListGrid.setWidth100();
        emailsListGrid.setHeight(150);
        emailsListGrid.setInitialCriteria(new Criteria(Constants.EMAIL_CONTACT_ID, contactId));
        emailsListGrid.setDataSource(contactEmailDS);
        emailsListGrid.setAutoFetchData(true);
        emailsListGrid.setPadding(GRID_PADDING);

        SelectItem emailTypeList = new SelectItem();
        emailTypeList.setOptionDataSource(emailTypesDS);
        emailTypeList.setValueField(Constants.ID);
        emailTypeList.setDisplayField(Constants.DESCRIPTION);

        ListGridField emailField = new ListGridField(Constants.EMAIL_ADDRESS, Constants.TITLE_EMAIL_ADDRESS);
        emailField.setCanEdit(true);

        ListGridField emailTypeField = new ListGridField(Constants.EMAIL_TYPE_ID, Constants.TITLE_EMAIL_TYPE_ID);
        emailTypeField.setCanEdit(true);
        emailTypeField.setEditorType(emailTypeList);
        emailTypeField.setAutoFetchDisplayMap(true);

        ListGridField enteredDateField =
            new ListGridField(Constants.EMAIL_ENTERED_DATE, Constants.TITLE_EMAIL_ENTERED_DATE);
        enteredDateField.setCanEdit(false);

        emailsListGrid.setFields(emailField, emailTypeField, enteredDateField);

        return emailsListGrid;
    }

    private ListGrid gridLinks()
    {
        linksListGrid.setWidth100();
        linksListGrid.setHeight(150);
        linksListGrid.setInitialCriteria(new Criteria(Constants.EMAIL_CONTACT_ID, contactId));
        linksListGrid.setDataSource(contactLinkDS);
        linksListGrid.setAutoFetchData(true);
        linksListGrid.setPadding(GRID_PADDING);

        SelectItem linkTypeList = new SelectItem();
        linkTypeList.setOptionDataSource(linkTypesDS);
        linkTypeList.setValueField(Constants.ID);
        linkTypeList.setDisplayField(Constants.DESCRIPTION);

        ListGridField linkField = new ListGridField(Constants.LINK_URL, Constants.TITLE_LINK_URL);
        linkField.setCanEdit(true);

        ListGridField linkTypeField = new ListGridField(Constants.LINK_TYPE_ID, Constants.TITLE_LINK_TYPE_ID);
        linkTypeField.setCanEdit(true);
        linkTypeField.setEditorType(linkTypeList);
        linkTypeField.setAutoFetchDisplayMap(true);

        ListGridField enteredDateField =
            new ListGridField(Constants.LINK_ENTERED_DATE, Constants.TITLE_LINK_ENTERED_DATE);
        enteredDateField.setCanEdit(false);

        linksListGrid.setFields(linkField, linkTypeField, enteredDateField);

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

        final ListGrid contactListGrid = new ListGrid();
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
                contactId = event.getRecord().getAttribute(Constants.PHONE_CONTACT_ID);

                phonesListGrid.setCriteria(new Criteria(Constants.PHONE_CONTACT_ID, contactId));
                phonesListGrid.invalidateCache();

                emailsListGrid.setCriteria(new Criteria(Constants.EMAIL_CONTACT_ID, contactId));
                emailsListGrid.invalidateCache();

                linksListGrid.setCriteria(new Criteria(Constants.LINK_CONTACT_ID, contactId));
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

        contactForm.setPadding(GRID_PADDING);
        contactForm.setDataSource(contactDS);
        contactForm.setTitleWidth(200);
        contactForm.setWidth(400);

        final TextItem userIdField;
        userIdField = new TextItem(Constants.CONTACT_USER_ID);
        userIdField.setIconVAlign(VerticalAlignment.CENTER);
        userIdField.setTabIndex(0);
        userIdField.setRequired(true);
        userIdField.setSelectOnFocus(true);
        userIdField.setDefaultValue(0);
        userIdField.setVisible(false);

        final TextItem IdField;
        IdField = new TextItem(Constants.CONTACT_ID);
        IdField.setIconVAlign(VerticalAlignment.CENTER);
        IdField.setTabIndex(1);
        IdField.setRequired(true);
        IdField.setSelectOnFocus(true);
        IdField.setDefaultValue(0);
        IdField.setVisible(false);

        final TextItem prefixField = new TextItem(Constants.CONTACT_PREFIX);
        prefixField.setTitle(Constants.TITLE_CONTACT_PREFIX);
        prefixField.setIconVAlign(VerticalAlignment.CENTER);
        prefixField.setRequired(true);
        prefixField.setTabIndex(2);

        final TextItem firstnameField = new TextItem(Constants.CONTACT_FIRST_NAME);
        firstnameField.setTitle(Constants.TITLE_CONTACT_FIRST_NAME);
        firstnameField.setIconVAlign(VerticalAlignment.CENTER);
        firstnameField.setRequired(true);
        firstnameField.setTabIndex(3);

        final TextItem middlenameField = new TextItem(Constants.CONTACT_MIDDLE_NAME);
        middlenameField.setTitle(Constants.TITLE_CONTACT_MIDDLE_NAME);
        middlenameField.setIconVAlign(VerticalAlignment.CENTER);
        middlenameField.setRequired(true);
        middlenameField.setTabIndex(4);

        final TextItem lastnameField = new TextItem(Constants.CONTACT_LAST_NAME);
        lastnameField.setTitle(Constants.TITLE_CONTACT_FIRST_NAME);
        lastnameField.setIconVAlign(VerticalAlignment.CENTER);
        lastnameField.setRequired(true);
        lastnameField.setTabIndex(5);

        final TextItem suffixField = new TextItem(Constants.CONTACT_SUFFIX);
        suffixField.setTitle(Constants.TITLE_CONTACT_SUFFIX);
        suffixField.setIconVAlign(VerticalAlignment.CENTER);
        suffixField.setRequired(true);
        suffixField.setTabIndex(6);

        final TextItem address1Field = new TextItem(Constants.CONTACT_ADDRESS1);
        address1Field.setTitle(Constants.TITLE_CONTACT_ADDRESS1);
        address1Field.setIconVAlign(VerticalAlignment.CENTER);
        address1Field.setRequired(true);
        address1Field.setTabIndex(7);

        final TextItem address2Field = new TextItem(Constants.CONTACT_ADDRESS2);
        address2Field.setTitle(Constants.TITLE_CONTACT_ADDRESS2);
        address2Field.setIconVAlign(VerticalAlignment.CENTER);
        address2Field.setRequired(true);
        address2Field.setTabIndex(8);

        final TextItem cityField = new TextItem(Constants.CONTACT_CITY);
        cityField.setTitle(Constants.TITLE_CONTACT_CITY);
        cityField.setIconVAlign(VerticalAlignment.CENTER);
        cityField.setRequired(true);
        cityField.setTabIndex(9);

        final TextItem stateField = new TextItem(Constants.CONTACT_STATE);
        stateField.setTitle(Constants.TITLE_CONTACT_STATE);
        stateField.setIconVAlign(VerticalAlignment.CENTER);
        stateField.setRequired(true);
        stateField.setTabIndex(10);

        final TextItem zipField = new TextItem(Constants.CONTACT_ZIP);
        zipField.setTitle(Constants.TITLE_CONTACT_ZIP);
        zipField.setIconVAlign(VerticalAlignment.CENTER);
        zipField.setRequired(true);
        zipField.setTabIndex(11);

        final DateItem birthdateField = new DateItem(Constants.CONTACT_BIRTHDATE);
        birthdateField.setTitle(Constants.TITLE_CONTACT_BIRTHDATE);
        birthdateField.setIconVAlign(VerticalAlignment.CENTER);
        birthdateField.setRequired(true);
        birthdateField.setTabIndex(12);

        IButton saveBtn = new IButton("Save");
        saveBtn.addClickHandler(new ClickHandler()
        {

            @Override
            public void onClick(ClickEvent event)
            {
                Record record = new ListGridRecord();
                record.setAttribute(Constants.CONTACT_ID, IdField.getValue());
                record.setAttribute(Constants.CONTACT_USER_ID, userIdField.getValue());

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

                record.setAttribute(Constants.CONTACT_ENTERED_BY, userIdField.getValue());
                record.setAttribute(Constants.CONTACT_EDITED_BY, userIdField.getValue());
                Date today = new Date();
                record.setAttribute(Constants.CONTACT_ENTERED_DATE, today);
                record.setAttribute(Constants.CONTACT_EDITED_DATE, today);

                record.setAttribute(Constants.CONTACT_BIRTHDATE, birthdateField.getValueAsDate());
                record.setAttribute(Constants.CONTACT_ADMIN, false);
                record.setAttribute(Constants.CONTACT_COMPANY_ID, 0);

                contactDS.addData(record);
            }

        });

        contactForm.setFields(IdField, userIdField, prefixField, firstnameField, middlenameField, lastnameField,
            address1Field, address2Field, cityField, stateField, zipField, birthdateField);

        contactForm.setBorder("1px solid blue");

        contactFormLayout.addMember(contactForm);
        contactFormLayout.addMember(saveBtn);

        return contactFormLayout;
    }
}
