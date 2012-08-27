package com.opensource.phonebook.shared.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

@SuppressWarnings("serial")
public class UserDTO implements Serializable
{
    private long id;
    private boolean active;
    private PositionDTO position;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String securityQuestion1;
    private String securityAnswer1;
    private String securityQuestion2;
    private String securityAnswer2;
    private Date birthdate;
    private HashSet<ContactDTO> contacts;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public PositionDTO getPosition()
    {
        return position;
    }

    public void setPosition(PositionDTO position)
    {
        this.position = position;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public HashSet<ContactDTO> getContacts()
    {
        return contacts;
    }

    public void setContacts(HashSet<ContactDTO> contacts)
    {
        this.contacts = contacts;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSecurityQuestion1()
    {
        return securityQuestion1;
    }

    public void setSecurityQuestion1(String securityQuestion1)
    {
        this.securityQuestion1 = securityQuestion1;
    }

    public String getSecurityAnswer1()
    {
        return securityAnswer1;
    }

    public void setSecurityAnswer1(String securityAnswer1)
    {
        this.securityAnswer1 = securityAnswer1;
    }

    public String getSecurityQuestion2()
    {
        return securityQuestion2;
    }

    public void setSecurityQuestion2(String securityQuestion2)
    {
        this.securityQuestion2 = securityQuestion2;
    }

    public String getSecurityAnswer2()
    {
        return securityAnswer2;
    }

    public void setSecurityAnswer2(String securityAnswer2)
    {
        this.securityAnswer2 = securityAnswer2;
    }

    public Date getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(Date birthdate)
    {
        this.birthdate = birthdate;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
        result = prime * result + ((contacts == null) ? 0 : contacts.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((securityAnswer1 == null) ? 0 : securityAnswer1.hashCode());
        result = prime * result + ((securityAnswer2 == null) ? 0 : securityAnswer2.hashCode());
        result = prime * result + ((securityQuestion1 == null) ? 0 : securityQuestion1.hashCode());
        result = prime * result + ((securityQuestion2 == null) ? 0 : securityQuestion2.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
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
        UserDTO other = (UserDTO) obj;
        if (active != other.active)
            return false;
        if (birthdate == null)
        {
            if (other.birthdate != null)
                return false;
        }
        else if (!birthdate.equals(other.birthdate))
            return false;
        if (contacts == null)
        {
            if (other.contacts != null)
                return false;
        }
        else if (!contacts.equals(other.contacts))
            return false;
        if (email == null)
        {
            if (other.email != null)
                return false;
        }
        else if (!email.equals(other.email))
            return false;
        if (firstname == null)
        {
            if (other.firstname != null)
                return false;
        }
        else if (!firstname.equals(other.firstname))
            return false;
        if (id != other.id)
            return false;
        if (lastname == null)
        {
            if (other.lastname != null)
                return false;
        }
        else if (!lastname.equals(other.lastname))
            return false;
        if (password == null)
        {
            if (other.password != null)
                return false;
        }
        else if (!password.equals(other.password))
            return false;
        if (position == null)
        {
            if (other.position != null)
                return false;
        }
        else if (!position.equals(other.position))
            return false;
        if (securityAnswer1 == null)
        {
            if (other.securityAnswer1 != null)
                return false;
        }
        else if (!securityAnswer1.equals(other.securityAnswer1))
            return false;
        if (securityAnswer2 == null)
        {
            if (other.securityAnswer2 != null)
                return false;
        }
        else if (!securityAnswer2.equals(other.securityAnswer2))
            return false;
        if (securityQuestion1 == null)
        {
            if (other.securityQuestion1 != null)
                return false;
        }
        else if (!securityQuestion1.equals(other.securityQuestion1))
            return false;
        if (securityQuestion2 == null)
        {
            if (other.securityQuestion2 != null)
                return false;
        }
        else if (!securityQuestion2.equals(other.securityQuestion2))
            return false;
        if (username == null)
        {
            if (other.username != null)
                return false;
        }
        else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "UserDTO [id=" + id + ", active=" + active + ", position=" + position + ", username=" + username
            + ", password=" + password + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
            + ", securityQuestion1=" + securityQuestion1 + ", securityAnswer1=" + securityAnswer1
            + ", securityQuestion2=" + securityQuestion2 + ", securityAnswer2=" + securityAnswer2 + ", birthdate="
            + birthdate + ", contacts=" + contacts + "]";
    }

}
