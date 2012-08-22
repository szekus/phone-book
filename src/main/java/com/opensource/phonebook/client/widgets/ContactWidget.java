package com.opensource.phonebook.client.widgets;

import java.util.Date;

import com.opensource.phonebook.client.datasource.ContactDS;
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

    private final ContactDS contactPhoneDS = null;
    private final ContactDS contactEmailDS = null;
    private final ContactDS contactLinkDS = null;

    private UserDTO userDto;
    private String userId;
    private String contactId;

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

        final ListGrid phonesListGrid = new ListGrid();
        phonesListGrid.setWidth100();
        phonesListGrid.setHeight(150);
        phonesListGrid.setInitialCriteria(new Criteria(Constants.CONTACT_ID, contactId));
        // phonesListGrid.setDataSource(contactPhoneDS);
        phonesListGrid.setAutoFetchData(true);
        phonesListGrid.setPadding(GRID_PADDING);

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
        final ListGrid phonesListGrid = new ListGrid();
        phonesListGrid.setWidth100();
        phonesListGrid.setHeight(150);
        phonesListGrid.setInitialCriteria(new Criteria(Constants.CONTACT_ID, contactId));
        // phonesListGrid.setDataSource(contactPhoneDS);
        phonesListGrid.setAutoFetchData(true);
        phonesListGrid.setPadding(GRID_PADDING);

        return phonesListGrid;
    }

    private ListGrid gridEmails()
    {
        final ListGrid emailsListGrid = new ListGrid();
        emailsListGrid.setWidth100();
        emailsListGrid.setHeight(150);
        emailsListGrid.setInitialCriteria(new Criteria(Constants.CONTACT_ID, contactId));
        // emailsListGrid.setDataSource(contactEmailDS);
        emailsListGrid.setAutoFetchData(true);
        emailsListGrid.setPadding(GRID_PADDING);

        return emailsListGrid;
    }

    private ListGrid gridLinks()
    {
        final ListGrid linksListGrid = new ListGrid();
        linksListGrid.setWidth100();
        linksListGrid.setHeight(150);
        linksListGrid.setInitialCriteria(new Criteria(Constants.CONTACT_ID, contactId));
        // linksListGrid.setDataSource(contactLinkDS);
        linksListGrid.setAutoFetchData(true);
        linksListGrid.setPadding(GRID_PADDING);

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
        IdField.setTabIndex(0);
        IdField.setRequired(true);
        IdField.setSelectOnFocus(true);
        IdField.setDefaultValue(0);
        IdField.setVisible(false);

        final TextItem prefixField = new TextItem(Constants.CONTACT_PREFIX);
        prefixField.setTitle(Constants.TITLE_CONTACT_PREFIX);
        prefixField.setIconVAlign(VerticalAlignment.CENTER);
        prefixField.setRequired(true);

        final TextItem firstnameField = new TextItem(Constants.CONTACT_FIRST_NAME);
        firstnameField.setTitle(Constants.TITLE_CONTACT_FIRST_NAME);
        firstnameField.setIconVAlign(VerticalAlignment.CENTER);
        firstnameField.setRequired(true);

        final TextItem middlenameField = new TextItem(Constants.CONTACT_MIDDLE_NAME);
        middlenameField.setTitle(Constants.TITLE_CONTACT_MIDDLE_NAME);
        middlenameField.setIconVAlign(VerticalAlignment.CENTER);
        middlenameField.setRequired(true);

        final TextItem lastnameField = new TextItem(Constants.CONTACT_LAST_NAME);
        lastnameField.setTitle(Constants.TITLE_CONTACT_FIRST_NAME);
        lastnameField.setIconVAlign(VerticalAlignment.CENTER);
        lastnameField.setRequired(true);

        final TextItem suffixField = new TextItem(Constants.CONTACT_SUFFIX);
        suffixField.setTitle(Constants.TITLE_CONTACT_SUFFIX);
        suffixField.setIconVAlign(VerticalAlignment.CENTER);
        suffixField.setRequired(true);

        final TextItem address1Field = new TextItem(Constants.CONTACT_ADDRESS1);
        address1Field.setTitle(Constants.TITLE_CONTACT_ADDRESS1);
        address1Field.setIconVAlign(VerticalAlignment.CENTER);
        address1Field.setRequired(true);

        final TextItem address2Field = new TextItem(Constants.CONTACT_ADDRESS2);
        address2Field.setTitle(Constants.TITLE_CONTACT_ADDRESS2);
        address2Field.setIconVAlign(VerticalAlignment.CENTER);
        address2Field.setRequired(true);

        final TextItem cityField = new TextItem(Constants.CONTACT_CITY);
        cityField.setTitle(Constants.TITLE_CONTACT_CITY);
        cityField.setIconVAlign(VerticalAlignment.CENTER);
        cityField.setRequired(true);

        final TextItem stateField = new TextItem(Constants.CONTACT_STATE);
        stateField.setTitle(Constants.TITLE_CONTACT_STATE);
        stateField.setIconVAlign(VerticalAlignment.CENTER);
        stateField.setRequired(true);

        final TextItem zipField = new TextItem(Constants.CONTACT_ZIP);
        zipField.setTitle(Constants.TITLE_CONTACT_ZIP);
        zipField.setIconVAlign(VerticalAlignment.CENTER);
        zipField.setRequired(true);

        final DateItem birthdateField = new DateItem(Constants.CONTACT_BIRTHDATE);
        birthdateField.setTitle(Constants.TITLE_CONTACT_BIRTHDATE);
        birthdateField.setIconVAlign(VerticalAlignment.CENTER);
        birthdateField.setRequired(true);

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
