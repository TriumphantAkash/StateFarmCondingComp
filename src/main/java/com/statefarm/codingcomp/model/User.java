package com.statefarm.codingcomp.model;

/**
 * A class representing a registered user. Email address is used for uniqueness.
 */
public class User {

    private String name;
    private String email;

    public User( String name, String email ) {
        super();
        this.name = name;
        this.email = email;
    }

    public User( String email ) {
        super();
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( email == null ) ? 0 : email.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        User other = (User) obj;
        if ( email == null ) {
            if ( other.email != null )
                return false;
        } else if ( !email.equals( other.email ) )
            return false;
        return true;
    }

}
