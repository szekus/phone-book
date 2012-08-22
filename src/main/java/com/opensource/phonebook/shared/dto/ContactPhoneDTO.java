package com.opensource.phonebook.shared.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ContactPhoneDTO implements Serializable
{

    private long phoneId;
    private long contactId;
    private PhoneTypeDTO phoneType;
    private String phone;
    private Date enteredDate;

    public long getPhoneId()
    {
        return phoneId;
    }

    public void setPhoneId(long phoneId)
    {
        this.phoneId = phoneId;
    }

    public long getContactId()
    {
        return contactId;
    }

    public void setContactId(long contactId)
    {
        this.contactId = contactId;
    }

    public PhoneTypeDTO getPhoneType()
    {
        return phoneType;
    }

    public void setPhoneType(PhoneTypeDTO phoneType)
    {
        this.phoneType = phoneType;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Date getEnteredDate()
    {
        return enteredDate;
    }

    public void setEnteredDate(Date enteredDate)
    {
        this.enteredDate = enteredDate;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + (int) (phoneId ^ (phoneId >>> 32));
        result = prime * result + ((phoneType == null) ? 0 : phoneType.hashCode());
        result = prime * result + ((enteredDate == null) ? 0 : enteredDate.hashCode());
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
        ContactPhoneDTO other = (ContactPhoneDTO) obj;
        if (phone == null)
        {
            if (other.phone != null)
                return false;
        }
        else if (!phone.equals(other.phone))
            return false;
        if (phoneId != other.phoneId)
            return false;
        if (phoneType == null)
        {
            if (other.phoneType != null)
                return false;
        }
        else if (!phoneType.equals(other.phoneType))
            return false;
        if (enteredDate == null)
        {
            if (other.enteredDate != null)
                return false;
        }
        else if (!enteredDate.equals(other.enteredDate))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ContactPhoneDTO [phoneId=" + phoneId + ", phoneType=" + phoneType + ", phone=" + phone
            + ", enteredDate=" + enteredDate + "]";
    }

}
