package com.statefarm.codingcomp;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.statefarm.codingcomp.model.Email;
import com.statefarm.codingcomp.model.User;

public interface QueryService {
    /**************************** SET ONE ****************************/
    /**
     * This method should find a list of users with a given domain.
     * 
     * @return List of all users with with given domain
     * @throws Exception
     */

    List<User> usersByDomain( String domain ) throws Exception;

    /**
     * For a given date range, identify the emails that were sent in that range.
     * All times are represented in GMT.
     * 
     * 
     * @throws Exception
     * @returns
     */
    List<Email> emailsInDateRange( Date start, Date end ) throws Exception;

    /**
     * Group emails sent by day. All timestamps are evaluated in GMT.
     * 
     * 
     * @throws Exception
     * @return
     */
    Map<String, List<Email>> emailsByDay() throws Exception;

    /**
     * For a given Date, identify how many emails were sent on that day.
     * 
     * @throws Exception
     * @return
     */
    int emailsOnDay( Date date ) throws Exception;

    /**
     * For all of the users that are in our users.txt file, identify emails that
     * were sent FROM them. A user can send to himself.
     * 
     * @throws Exception
     * @return
     */
    Map<User, List<Email>> emailsFromOurUsers() throws Exception;

    /**
     * For all of the users that are in our users.txt file, identify emails that
     * were sent TO them. An user can send to himself.
     * 
     * @throws Exception
     * @return
     */
    Map<User, List<Email>> emailsToOurUsers() throws Exception;

    /**
     * For all of the users that are in our users.txt file, identify emails that
     * were sent FROM one of our users TO one of our users. An user can send to
     * himself.
     * 
     * @throws Exception
     * @return
     */
    List<Email> emailsToUserFromUser() throws Exception;

    /**************************** SET TWO ****************************/

    /**
     * This method should find the e-mail addresses which are connected by
     * degrees separation to email. TIP: You may want to first implement this
     * for degrees=2 before degrees>2. E.g. Bob emails Jane and Mary emails
     * Jane. Bob -> Jane <- Mary. Bob and Mary are separated by 2 degrees.
     * Degrees=1: Friends; Degrees=2: Friends of Friends; Etc.
     * 
     * @param email
     *            E-mail to use to search
     * @param degrees
     *            Number of degrees of separation to search
     * @return List of all the e-mail addresses that match
     * @throws Exception
     */
    Set<String> emailAddressesByDegrees( String email, int degrees ) throws Exception;

    /**
     * List the number of degrees between emailAddressOne and emailAddressTwo.
     * Return -1 if not connected. See above test for more explanation.
     * 
     * @param emailAddressOne
     * @param emailAddressTwo
     * @return Number of degrees between emailAddressOne and emailAddressTwo. -1
     *         if not connected.
     */
    int degreesBetween( String emailAddressOne, String emailAddressTwo ) throws Exception;

    /**************************** SET THREE ****************************/

    /**
     * Finds the e-mail address that has the most number of unique connections
     * to other e-mail addresses.
     * 
     * @return
     */
    String mostConnected() throws Exception;

    /**
     * Finds the e-mail address that has sent the most number of e-mails.
     * 
     * @return
     */
    String mostActiveSender() throws Exception;

    /**
     * Finds the e-mail address that has received the most number of e-mails.
     * 
     * @return
     */
    String mostActiveReceiver() throws Exception;

    /**
     * Finds the e-mail address that has sent the most number of e-mails to a
     * single person.
     * 
     * @return
     */
    String mostSingularSender() throws Exception;

    /**
     * Finds the e-mail address that sends the most e-mails to themselves
     * (sender and receiver are the same).
     * 
     * @return
     */
    String mostSelfEmails() throws Exception;

    /**************************** SET FOUR ****************************/

    /**
     * Finds the most popular hour of the day for e-mail.
     * 
     * @return
     */
    int mostPopularHour() throws Exception;

    /**
     * Finds the most popular hour of the day for e-mail for a given e-mail
     * address.
     * 
     * @param email
     * @return
     */
    int mostPopularHour( String email ) throws Exception;

    /**
     * Finds the least popular hour of the day for e-mail.
     * 
     * @return
     */
    int leastPopularHour() throws Exception;

    /**
     * Finds the least popular hour of the day for e-mail for a given e-mail
     * address.
     * 
     * @param email
     * @return
     */
    int leastPopularHour( String email ) throws Exception;

    /**
     * Finds the most popular date for e-mail.
     * 
     * @return
     */
    String mostPopularDate() throws Exception;

    /**
     * Finds the most popular date for e-mail for a given e-mail address.
     * 
     * @param email
     * @return
     */
    String mostPopularDate( String email ) throws Exception;

    /**
     * Finds the least popular date for e-mail.
     * 
     * @return
     */
    String leastPopularDate() throws Exception;

    /**
     * Finds the least popular date for e-mail for a given e-mail address.
     * 
     * @param email
     * @return
     */
    String leastPopularDate( String email ) throws Exception;
}
