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
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.BooleanCallback;
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

    private IButton addPhoneButton = new IButton("Add Phone");
    private IButton addEmailButton = new IButton("Add Email");
    private IButton addLinkButton = new IButton("Add Link");

    private ListGrid contactListGrid = new ListGrid();

    private ListGridField phoneContactIdField = new ListGridField(Constants.C_PHONE_CONTACT_ID,
        Constants.TITLE_C_PHONE_CONTACT_ID);
    private ListGridField phoneIdField = new ListGridField(Constants.C_PHONE_ID, Constants.TITLE_C_PHONE_ID);
    private ListGridField phoneField = new ListGridField(Constants.C_PHONE, Constants.TITLE_C_PHONE);
    private ListGridField phoneTypeField =
        new ListGridField(Constants.C_PHONE_TYPE_ID, Constants.TITLE_C_PHONE_TYPE_ID);
    private ListGridField phoneEnteredDateField = new ListGridField(Constants.C_PHONE_ENTERED_DATE,
        Constants.TITLE_C_PHONE_ENTERED_DATE);

    private ListGridField emailContactIdField = new ListGridField(Constants.C_EMAIL_CONTACT_ID,
        Constants.TITLE_C_EMAIL_CONTACT_ID);
    private ListGridField emailIdField = new ListGridField(Constants.C_EMAIL_ID, Constants.TITLE_C_EMAIL_ID);
    private ListGridField emailField = new ListGridField(Constants.C_EMAIL, Constants.TITLE_C_EMAIL);
    private ListGridField emailTypeField =
        new ListGridField(Constants.C_EMAIL_TYPE_ID, Constants.TITLE_C_EMAIL_TYPE_ID);
    private ListGridField emailEnteredDateField = new ListGridField(Constants.C_EMAIL_ENTERED_DATE,
        Constants.TITLE_C_EMAIL_ENTERED_DATE);

    private ListGridField linkContactIdField = new ListGridField(Constants.C_LINK_CONTACT_ID,
        Constants.TITLE_C_LINK_CONTACT_ID);
    private ListGridField linkIdField = new ListGridField(Constants.C_LINK_ID, Constants.TITLE_C_LINK_ID);
    private ListGridField linkField = new ListGridField(Constants.C_LINK, Constants.TITLE_C_LINK);
    private ListGridField linkDescriptionField = new ListGridField(Constants.C_LINK_DESCRIPTION,
        Constants.TITLE_C_LINK_DESCRIPTION);
    private ListGridField linkTypeField = new ListGridField(Constants.C_LINK_TYPE_ID, Constants.TITLE_C_LINK_TYPE_ID);
    private ListGridField linkEnteredDateField = new ListGridField(Constants.C_LINK_ENTERED_DATE,
        Constants.TITLE_C_LINK_ENTERED_DATE);

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

    private HLayout getSpacerLayout()
    {
        HLayout spacerLayout = new HLayout();
        // spacerLayout.setBorder("5px solid black");
        Label spacerLabel = new Label();
        spacerLabel.setContents("&nbsp;");
        spacerLayout.addMember(spacerLabel);
        return spacerLayout;
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
        contactGridsLayout.addMember(getGridButtonLayout());

        return contactGridsLayout;
    }

    private HLayout getGridButtonLayout()
    {
        HLayout gridButtonLayout = new HLayout();
        gridButtonLayout.setAlign(Alignment.CENTER);
        gridButtonLayout.setLayoutAlign(VerticalAlignment.CENTER);
        // gridButtonLayout.setBorder("5px solid red");

        addPhoneButton.setDisabled(true);
        addPhoneButton.setAlign(Alignment.CENTER);
        addPhoneButton.setValign(VerticalAlignment.CENTER);
        addPhoneButton.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                phonesListGrid.startEditingNew();
            }
        });

        addEmailButton.setDisabled(true);
        addEmailButton.setAlign(Alignment.CENTER);
        addEmailButton.setValign(VerticalAlignment.CENTER);
        addEmailButton.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                emailsListGrid.startEditingNew();
            }
        });

        addLinkButton.setDisabled(true);
        addLinkButton.setAlign(Alignment.CENTER);
        addLinkButton.setValign(VerticalAlignment.CENTER);
        addLinkButton.addClickHandler(new ClickHandler()
        {
            @Override
            public void onClick(ClickEvent event)
            {
                linksListGrid.startEditingNew();
            }
        });

        gridButtonLayout.addMember(getSpacerLayout());
        gridButtonLayout.addMember(addPhoneButton);
        gridButtonLayout.addMember(getSpacerLayout());
        gridButtonLayout.addMember(addEmailButton);
        gridButtonLayout.addMember(getSpacerLayout());
        gridButtonLayout.addMember(addLinkButton);
        gridButtonLayout.addMember(getSpacerLayout());

        return gridButtonLayout;
    }

    private ListGrid gridPhones()
    {
        phonesListGrid.setWidth100();
        phonesListGrid.setHeight(150);
        phonesListGrid.setInitialCriteria(new Criteria(Constants.C_PHONE_CONTACT_ID, contactId));
        phonesListGrid.setDataSource(contactPhoneDS);
        phonesListGrid.setPadding(GRID_PADDING);
        // phonesListGrid.setAlwaysShowEditors(true);

        phoneContactIdField.setCanEdit(false);
        phoneContactIdField.setHidden(true);
        phoneContactIdField.setType(ListGridFieldType.INTEGER);

        phoneIdField.setCanEdit(false);
        phoneIdField.setHidden(true);
        phoneIdField.setDefaultValue(0);

        phoneField.setCanEdit(true);
        phoneField.setWidth(100);
        phoneField.setRequired(true);

        SelectItem phoneTypeList = new SelectItem();
        phoneTypeList.setOptionDataSource(phoneTypesDS);
        phoneTypeList.setValueField(Constants.PHONE_TYPE_ID);
        phoneTypeList.setDisplayField(Constants.PHONE_TYPE_DESCRIPTION);

        phoneTypeField.setOptionDataSource(phoneTypesDS);
        phoneTypeField.setEditorType(phoneTypeList);
        phoneTypeField.setValueField(Constants.PHONE_TYPE_ID);
        phoneTypeField.setDisplayField(Constants.PHONE_TYPE_DESCRIPTION);
        phoneTypeField.setAutoFetchDisplayMap(true);
        phoneTypeField.setCanEdit(true);
        phoneTypeField.setWidth(100);
        phoneTypeField.setType(ListGridFieldType.INTEGER);
        phoneTypeField.setRequired(true);

        phoneEnteredDateField.setCanEdit(false);
        phoneEnteredDateField.setWidth(150);
        phoneEnteredDateField.setDefaultValue(new Date());
        phoneEnteredDateField.setType(ListGridFieldType.DATE);

        ListGridField phoneDeleteField = new ListGridField("deletePhoneField", 50);
        phoneDeleteField.setAlign(Alignment.CENTER);
        phoneDeleteField.setType(ListGridFieldType.ICON);
        phoneDeleteField.setIcon("silk/delete.png");
        phoneDeleteField.setCanEdit(false);

        phoneDeleteField.addRecordClickHandler(new RecordClickHandler()
        {
            public void onRecordClick(RecordClickEvent event)
            {
                // SC.say("Remove Facet");
                final ListGridRecord selectedRecord = phonesListGrid.getSelectedRecord();
                if (selectedRecord != null)
                {
                    SC.confirm("Are you sure that you want to delete this record?", new BooleanCallback()
                    {
                        public void execute(Boolean value)
                        {
                            if (value != null && value)
                            {
                                // *** proceed with delete
                                phonesListGrid.removeData(selectedRecord);
                            }
                            else
                            {
                                // Cancel
                            }
                        }
                    });
                }
                else
                {
                    SC.say("Select a record before performing this action");
                }
            }
        });

        phonesListGrid.setFields(phoneContactIdField, phoneIdField, phoneField, phoneTypeField, phoneEnteredDateField,
            phoneDeleteField);
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
        // emailsListGrid.setAlwaysShowEditors(true);

        emailContactIdField.setCanEdit(false);
        emailContactIdField.setHidden(true);
        emailContactIdField.setType(ListGridFieldType.INTEGER);

        emailIdField.setCanEdit(false);
        emailIdField.setHidden(true);
        emailIdField.setDefaultValue(0);

        emailField.setCanEdit(true);
        emailField.setWidth(100);
        emailField.setRequired(true);

        SelectItem emailTypeList = new SelectItem();
        emailTypeList.setOptionDataSource(emailTypesDS);
        emailTypeList.setValueField(Constants.EMAIL_TYPE_ID);
        emailTypeList.setDisplayField(Constants.EMAIL_TYPE_DESCRIPTION);

        emailTypeField.setOptionDataSource(emailTypesDS);
        emailTypeField.setEditorType(emailTypeList);
        emailTypeField.setValueField(Constants.EMAIL_TYPE_ID);
        emailTypeField.setDisplayField(Constants.EMAIL_TYPE_DESCRIPTION);
        emailTypeField.setAutoFetchDisplayMap(true);
        emailTypeField.setCanEdit(true);
        emailTypeField.setWidth(100);
        emailTypeField.setType(ListGridFieldType.INTEGER);
        emailTypeField.setRequired(true);

        emailEnteredDateField.setCanEdit(false);
        emailEnteredDateField.setWidth(150);
        emailEnteredDateField.setDefaultValue(new Date());
        emailEnteredDateField.setType(ListGridFieldType.DATE);

        ListGridField emailDeleteField = new ListGridField("deleteEmailField", 50);
        emailDeleteField.setAlign(Alignment.CENTER);
        emailDeleteField.setType(ListGridFieldType.ICON);
        emailDeleteField.setIcon("silk/delete.png");
        emailDeleteField.setCanEdit(false);

        emailDeleteField.addRecordClickHandler(new RecordClickHandler()
        {
            public void onRecordClick(RecordClickEvent event)
            {
                // SC.say("Remove Facet");
                final ListGridRecord selectedRecord = emailsListGrid.getSelectedRecord();
                if (selectedRecord != null)
                {
                    SC.confirm("Are you sure that you want to delete this record?", new BooleanCallback()
                    {
                        public void execute(Boolean value)
                        {
                            if (value != null && value)
                            {
                                // *** proceed with delete
                                emailsListGrid.removeData(selectedRecord);
                            }
                            else
                            {
                                // Cancel
                            }
                        }
                    });
                }
                else
                {
                    SC.say("Select a record before performing this action");
                }
            }
        });

        emailsListGrid.setFields(emailContactIdField, emailIdField, emailField, emailTypeField, emailEnteredDateField,
            emailDeleteField);
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
        // linksListGrid.setAlwaysShowEditors(true);

        linkContactIdField.setCanEdit(false);
        linkContactIdField.setHidden(true);
        linkContactIdField.setType(ListGridFieldType.INTEGER);

        linkIdField.setCanEdit(false);
        linkIdField.setHidden(true);
        linkIdField.setDefaultValue(0);

        linkField.setCanEdit(true);
        linkField.setWidth(150);
        linkField.setRequired(true);

        linkDescriptionField.setCanEdit(true);
        linkDescriptionField.setWidth(250);
        linkDescriptionField.setRequired(true);

        SelectItem linkTypeList = new SelectItem();
        linkTypeList.setOptionDataSource(linkTypesDS);
        linkTypeList.setValueField(Constants.LINK_TYPE_ID);
        linkTypeList.setDisplayField(Constants.LINK_TYPE_DESCRIPTION);

        linkTypeField.setOptionDataSource(linkTypesDS);
        linkTypeField.setEditorType(linkTypeList);
        linkTypeField.setValueField(Constants.LINK_TYPE_ID);
        linkTypeField.setDisplayField(Constants.LINK_TYPE_DESCRIPTION);
        linkTypeField.setAutoFetchDisplayMap(true);
        linkTypeField.setCanEdit(true);
        linkTypeField.setWidth(100);
        linkTypeField.setType(ListGridFieldType.INTEGER);
        linkTypeField.setRequired(true);

        linkEnteredDateField.setCanEdit(false);
        linkEnteredDateField.setWidth(150);
        linkEnteredDateField.setDefaultValue(new Date());
        linkEnteredDateField.setType(ListGridFieldType.DATE);

        ListGridField linkDeleteField = new ListGridField("deleteLinkField", 50);
        linkDeleteField.setAlign(Alignment.CENTER);
        linkDeleteField.setType(ListGridFieldType.ICON);
        linkDeleteField.setIcon("silk/delete.png");
        linkDeleteField.setCanEdit(false);

        linkDeleteField.addRecordClickHandler(new RecordClickHandler()
        {
            public void onRecordClick(RecordClickEvent event)
            {
                // SC.say("Remove Facet");
                final ListGridRecord selectedRecord = linksListGrid.getSelectedRecord();
                if (selectedRecord != null)
                {
                    SC.confirm("Are you sure that you want to delete this record?", new BooleanCallback()
                    {
                        public void execute(Boolean value)
                        {
                            if (value != null && value)
                            {
                                // *** proceed with delete
                                linksListGrid.removeData(selectedRecord);
                            }
                            else
                            {
                                // Cancel
                            }
                        }
                    });
                }
                else
                {
                    SC.say("Select a record before performing this action");
                }
            }
        });

        linksListGrid.setFields(linkContactIdField, linkIdField, linkField, linkDescriptionField, linkTypeField,
            linkEnteredDateField, linkDeleteField);
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
                phoneContactIdField.setDefaultValue(contactId);
                addPhoneButton.setDisabled(false);

                emailsListGrid.setCriteria(new Criteria(Constants.C_EMAIL_CONTACT_ID, contactId));
                emailsListGrid.invalidateCache();
                emailContactIdField.setDefaultValue(contactId);
                addEmailButton.setDisabled(false);

                linksListGrid.setCriteria(new Criteria(Constants.C_LINK_CONTACT_ID, contactId));
                linksListGrid.invalidateCache();
                linkContactIdField.setDefaultValue(contactId);
                addLinkButton.setDisabled(false);

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
                    contactForm.clearValues();
                }

            }
        });

        buttonLayout.addMember(addnewButton);
        buttonLayout.addMember(getSpacerLayout());
        buttonLayout.addMember(saveButton);
        buttonLayout.addMember(getSpacerLayout());
        buttonLayout.addMember(deleteButton);

        contactFormLayout.addMember(contactForm);
        contactFormLayout.addMember(buttonLayout);

        return contactFormLayout;
    }

    private String getContactValidation(DynamicForm contactForm)
    {
        String contactValidationMessage = null;
        StringBuffer sb = new StringBuffer();
        if (firstnameField.getValue() == null && !"".equals(firstnameField.getValue()))
        {
            sb.append("Contact First Name cannot be left blank!<br/>");
        }
        if (lastnameField.getValue() == null && !"".equals(lastnameField.getValue()))
        {
            sb.append("Contact Last Name cannot be left blank!<br/>");
        }
        if (address1Field.getValue() == null && !"".equals(address1Field.getValue()))
        {
            sb.append("Contact Address cannot be left blank!<br/>");
        }
        if (cityField.getValue() == null && !"".equals(cityField.getValue()))
        {
            sb.append("Contact City cannot be left blank!<br/>");
        }
        if (stateField.getValue() == null && !"".equals(stateField.getValue()))
        {
            sb.append("Contact State cannot be left blank!<br/>");
        }
        if (zipField.getValue() == null && !"".equals(zipField.getValue()))
        {
            sb.append("Contact Zip cannot be left blank!<br/>");
        }
        if (birthdateField.getValue() == null && !"".equals(birthdateField.getValue()))
        {
            sb.append("Contact Birth Date cannot be left blank!<br/>");
        }
        contactValidationMessage = sb.toString();
        return contactValidationMessage;
    }
}
