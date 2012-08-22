package com.opensource.phonebook.shared.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ContactEmailDTO implements Serializable
{
    private long emailId;
    private long contactId;
    private EmailTypeDTO emailType;
    private String email;
    private Date enteredDate;

    public long getEmailId()
    {
        return emailId;
    }

    public void setEmailId(long emailId)
    {
        this.emailId = emailId;
    }

    public long getContactId()
    {
        return contactId;
    }

    public void setContactId(long contactId)
    {
        this.contactId = contactId;
    }

    public EmailTypeDTO getEmailType()
    {
        return emailType;
    }

    public void setEmailType(EmailTypeDTO emailType)
    {
        this.emailType = emailType;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + (int) (emailId ^ (emailId >>> 32));
        result = prime * result + ((emailType == null) ? 0 : emailType.hashCode());
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
        ContactEmailDTO other = (ContactEmailDTO) obj;
        if (email == null)
        {
            if (other.email != null)
                return false;
        }
        else if (!email.equals(other.email))
            return false;
        if (emailId != other.emailId)
            return false;
        if (emailType == null)
        {
            if (other.emailType != null)
                return false;
        }
        else if (!emailType.equals(other.emailType))
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
        return "ContactEmailDTO [emailId=" + emailId + ", emailType=" + emailType + ", email=" + email
            + ", enteredDate=" + enteredDate + "]";
    }

}
