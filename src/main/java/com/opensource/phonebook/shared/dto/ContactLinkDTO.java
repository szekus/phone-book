package com.opensource.phonebook.shared.dto;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ContactLinkDTO implements Serializable
{

    private long linkId;
    private long contactId;
    private LinkTypeDTO linkType;
    private String link;
    private String description;
    private Date enteredDate;

    public long getLinkId()
    {
        return linkId;
    }

    public void setLinkId(long linkId)
    {
        this.linkId = linkId;
    }

    public long getContactId()
    {
        return contactId;
    }

    public void setContactId(long contactId)
    {
        this.contactId = contactId;
    }

    public LinkTypeDTO getLinkType()
    {
        return linkType;
    }

    public void setLinkType(LinkTypeDTO linkType)
    {
        this.linkType = linkType;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public Date getEnteredDate()
    {
        return enteredDate;
    }

    public void setEnteredDate(Date enteredDate)
    {
        this.enteredDate = enteredDate;
    }

    public String getLinkDescription()
    {
        return description;
    }

    public void setLinkDescription(String description)
    {
        this.description = description;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (contactId ^ (contactId >>> 32));
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((enteredDate == null) ? 0 : enteredDate.hashCode());
        result = prime * result + ((link == null) ? 0 : link.hashCode());
        result = prime * result + (int) (linkId ^ (linkId >>> 32));
        result = prime * result + ((linkType == null) ? 0 : linkType.hashCode());
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
        ContactLinkDTO other = (ContactLinkDTO) obj;
        if (contactId != other.contactId)
            return false;
        if (description == null)
        {
            if (other.description != null)
                return false;
        }
        else if (!description.equals(other.description))
            return false;
        if (enteredDate == null)
        {
            if (other.enteredDate != null)
                return false;
        }
        else if (!enteredDate.equals(other.enteredDate))
            return false;
        if (link == null)
        {
            if (other.link != null)
                return false;
        }
        else if (!link.equals(other.link))
            return false;
        if (linkId != other.linkId)
            return false;
        if (linkType == null)
        {
            if (other.linkType != null)
                return false;
        }
        else if (!linkType.equals(other.linkType))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ContactLinkDTO [linkId=" + linkId + ", contactId=" + contactId + ", linkType=" + linkType + ", link="
            + link + ", description=" + description + ", enteredDate=" + enteredDate + "]";
    }

}
