package com.opensource.phonebook.shared.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

@SuppressWarnings("serial")
public class ContactDTO implements Serializable
{
    private long id;
    private long userId;
    private String prefix;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private long companyId;
    private long enteredBy;
    private Date enteredDate;
    private long editedBy;
    private Date editedDate;
    private Date birthDate;
    private boolean admin;
    private HashSet<ContactEmailDTO> emails;
    private HashSet<ContactPhoneDTO> phones;
    private HashSet<ContactLinkDTO> links;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getSuffix()
    {
        return suffix;
    }

    public void setSuffix(String suffix)
    {
        this.suffix = suffix;
    }

    public String getAddress1()
    {
        return address1;
    }

    public void setAddress1(String address1)
    {
        this.address1 = address1;
    }

    public String getAddress2()
    {
        return address2;
    }

    public void setAddress2(String address2)
    {
        this.address2 = address2;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

    public long getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(long companyId)
    {
        this.companyId = companyId;
    }

    public long getEnteredBy()
    {
        return enteredBy;
    }

    public void setEnteredBy(long enteredBy)
    {
        this.enteredBy = enteredBy;
    }

    public Date getEnteredDate()
    {
        return enteredDate;
    }

    public void setEnteredDate(Date enteredDate)
    {
        this.enteredDate = enteredDate;
    }

    public long getEditedBy()
    {
        return editedBy;
    }

    public void setEditedBy(long editedBy)
    {
        this.editedBy = editedBy;
    }

    public Date getEditedDate()
    {
        return editedDate;
    }

    public void setEditedDate(Date editedDate)
    {
        this.editedDate = editedDate;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    public boolean isAdmin()
    {
        return admin;
    }

    public void setAdmin(boolean admin)
    {
        this.admin = admin;
    }

    public HashSet<ContactEmailDTO> getEmails()
    {
        return emails;
    }

    public void setEmails(HashSet<ContactEmailDTO> emails)
    {
        this.emails = emails;
    }

    public HashSet<ContactPhoneDTO> getPhones()
    {
        return phones;
    }

    public void setPhones(HashSet<ContactPhoneDTO> phones)
    {
        this.phones = phones;
    }

    public HashSet<ContactLinkDTO> getLinks()
    {
        return links;
    }

    public void setLinks(HashSet<ContactLinkDTO> links)
    {
        this.links = links;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address1 == null) ? 0 : address1.hashCode());
        result = prime * result + ((address2 == null) ? 0 : address2.hashCode());
        result = prime * result + (admin ? 1231 : 1237);
        result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + (int) (companyId ^ (companyId >>> 32));
        result = prime * result + (int) (editedBy ^ (editedBy >>> 32));
        result = prime * result + ((editedDate == null) ? 0 : editedDate.hashCode());
        result = prime * result + ((emails == null) ? 0 : emails.hashCode());
        result = prime * result + (int) (enteredBy ^ (enteredBy >>> 32));
        result = prime * result + ((enteredDate == null) ? 0 : enteredDate.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((links == null) ? 0 : links.hashCode());
        result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
        result = prime * result + ((phones == null) ? 0 : phones.hashCode());
        result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
        result = prime * result + (int) (userId ^ (userId >>> 32));
        result = prime * result + ((zip == null) ? 0 : zip.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContactDTO other = (ContactDTO) obj;
        if (address1 == null)
        {
            if (other.address1 != null)
                return false;
        }
        else if (!address1.equals(other.address1))
            return false;
        if (address2 == null)
        {
            if (other.address2 != null)
                return false;
        }
        else if (!address2.equals(other.address2))
            return false;
        if (admin != other.admin)
            return false;
        if (birthDate == null)
        {
            if (other.birthDate != null)
                return false;
        }
        else if (!birthDate.equals(other.birthDate))
            return false;
        if (city == null)
        {
            if (other.city != null)
                return false;
        }
        else if (!city.equals(other.city))
            return false;
        if (companyId != other.companyId)
            return false;
        if (editedBy != other.editedBy)
            return false;
        if (editedDate == null)
        {
            if (other.editedDate != null)
                return false;
        }
        else if (!editedDate.equals(other.editedDate))
            return false;
        if (emails == null)
        {
            if (other.emails != null)
                return false;
        }
        else if (!emails.equals(other.emails))
            return false;
        if (enteredBy != other.enteredBy)
            return false;
        if (enteredDate == null)
        {
            if (other.enteredDate != null)
                return false;
        }
        else if (!enteredDate.equals(other.enteredDate))
            return false;
        if (firstName == null)
        {
            if (other.firstName != null)
                return false;
        }
        else if (!firstName.equals(other.firstName))
            return false;
        if (id != other.id)
            return false;
        if (lastName == null)
        {
            if (other.lastName != null)
                return false;
        }
        else if (!lastName.equals(other.lastName))
            return false;
        if (links == null)
        {
            if (other.links != null)
                return false;
        }
        else if (!links.equals(other.links))
            return false;
        if (middleName == null)
        {
            if (other.middleName != null)
                return false;
        }
        else if (!middleName.equals(other.middleName))
            return false;
        if (phones == null)
        {
            if (other.phones != null)
                return false;
        }
        else if (!phones.equals(other.phones))
            return false;
        if (prefix == null)
        {
            if (other.prefix != null)
                return false;
        }
        else if (!prefix.equals(other.prefix))
            return false;
        if (state == null)
        {
            if (other.state != null)
                return false;
        }
        else if (!state.equals(other.state))
            return false;
        if (suffix == null)
        {
            if (other.suffix != null)
                return false;
        }
        else if (!suffix.equals(other.suffix))
            return false;
        if (userId != other.userId)
            return false;
        if (zip == null)
        {
            if (other.zip != null)
                return false;
        }
        else if (!zip.equals(other.zip))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ContactDTO [id=" + id + ", userId=" + userId + ", prefix=" + prefix + ", firstName=" + firstName
            + ", middleName=" + middleName + ", lastName=" + lastName + ", suffix=" + suffix + ", address1=" + address1
            + ", address2=" + address2 + ", city=" + city + ", state=" + state + ", zip=" + zip + ", companyId="
            + companyId + ", enteredBy=" + enteredBy + ", enteredDate=" + enteredDate + ", editedBy=" + editedBy
            + ", editedDate=" + editedDate + ", birthDate=" + birthDate + ", admin=" + admin + ", emails=" + emails
            + ", phones=" + phones + ", links=" + links + "]";
    }

}
